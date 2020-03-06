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
	private TaijuInfo taijuInfo = new TaijuInfo();
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
		
		String date = request.getParameter("yaer") + request.getParameter("month") + request.getParameter("day");
		if (!validator.isDate(date)) {
			message.add("日付が不正です。");
		} else if (!validator.isFuture(date)) {
			message.add("未来の日付が選択されています。");
		}
		String inputWeight = request.getParameter("weight");
		double bodyWeight = 0;
		double bmi = 0;
		String result = null;
		if (!validator.isRequired(inputWeight)) {
			message.add("体重の入力は必須です。");
		} else if (!validator.isBodyInfo(inputWeight)) {
			message.add("体重は半角数値の整数3桁、小数点1桁以内で入力してください。");
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
		if(message.size() == 0) {
			taijuInfo.setDate(date);
			taijuInfo.setUserNo(Integer.parseInt(request.getParameter("userNo")));
			taijuInfo.setBodyWeight(bodyWeight);
			taijuInfo.setBmi(bmi);
			taijuInfo.setResult(result);
			
			tLogic.registTaijuInfo(taijuInfo);
		} else {
			request.setAttribute("message", message);
		}
		
		lServlet.doPost(request, response);
	}

}
