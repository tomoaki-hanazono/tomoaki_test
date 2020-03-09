package taijukiroku.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import taijukiroku.bean.TaijuInfo;
import taijukiroku.logic.TaijukirokuLogic;
import taijukiroku.controller.LoginServlet;
import taijukiroku.validator.CommonValidator;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommonValidator validator = new CommonValidator();
	private LoginServlet lServlet = new LoginServlet();
	private TaijukirokuLogic tLogic = new TaijukirokuLogic();
	private List<String> message = new ArrayList<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		TaijuInfo taijuInfo = new TaijuInfo();
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String date = year + month + day;
		String inputWeight = request.getParameter("weight");
		double bodyWeight = 0;
		double bmi = 0;
		String result = null;
		
		if (!validator.isRequired(inputWeight) || !validator.isRequired(year)
				|| !validator.isRequired(month)|| !validator.isRequired(day)) {
			message.add("未入力の項目があります。");
		} else {
			if (!validator.isDate(date)) {
				message.add("日付が不正です。");
			} else if (!validator.isFuture(date)) {
				message.add("未来の日付が選択されています。");
			}
			if (!validator.isBodyInfo(inputWeight)) {
				message.add("体重は整数3桁、小数点1桁以内の半角数値で入力してください。");
			} else {
				bodyWeight = Double.parseDouble(inputWeight);
				double height = Double.parseDouble(request.getParameter("height")) / 100;
				BigDecimal bd;
				double cal = bodyWeight / Math.pow(height, 2);
				bd = new BigDecimal(cal);
				bd = bd.setScale(1, RoundingMode.HALF_UP);
				bmi = bd.doubleValue();
				
				if (18.5 > bmi) {
					result = "低体重";
				} else if (25 > bmi) {
					result = "普通体重";
				} else if (30 > bmi) {
					result = "肥満1度";
				} else if (35 > bmi) {
					result = "肥満2度";
				} else if (40 > bmi) {
					result = "肥満3度";
				} else {
					result = "肥満4度";
				}
			}
		}
		
		if(message.size() == 0) {
			if (tLogic.countTaijuInfo(userNo, date) > 0) {
				message.add("登録済の日付です。");
			} else {
				taijuInfo.setDate(date);
				taijuInfo.setUserNo(userNo);
				taijuInfo.setBodyWeight(bodyWeight);
				taijuInfo.setBmi(bmi);
				taijuInfo.setResult(result);
				
				tLogic.registTaijuInfo(taijuInfo);
			}
		}

		request.setAttribute("message", message);
		
		lServlet.doPost(request, response);
	}

}
