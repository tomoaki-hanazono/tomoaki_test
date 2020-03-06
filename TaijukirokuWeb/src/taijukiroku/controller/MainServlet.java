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

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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

		LoginServlet lServlet = new LoginServlet();
		TaijukirokuLogic tLogic = new TaijukirokuLogic();
		TaijuInfo taijuInfo = new TaijuInfo();
		
		request.setCharacterEncoding("utf-8");
		
		String date = request.getParameter("yaer") + request.getParameter("month") + request.getParameter("day");
		
		String inputWeight = request.getParameter("weight");
		double bodyWeight = Double.parseDouble(inputWeight);
		double height = Double.parseDouble(request.getParameter("height")) / 100;
		double bmi = 0;
		String result = null;
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
		
		taijuInfo.setDate(date);
		taijuInfo.setUserNo(Integer.parseInt(request.getParameter("userNo")));
		taijuInfo.setBodyWeight(bodyWeight);
		taijuInfo.setBmi(bmi);
		taijuInfo.setResult(result);
		
		tLogic.registTaijuInfo(taijuInfo);
		
		lServlet.doPost(request, response);
	}

}
