package jinjikyuyo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jinjikyuyo.bean.InsurancePremiumRateBean;
import jinjikyuyo.logic.InsurancePremiumRateLogic;

/**
 * Servlet implementation class MaintenanceChangeRateServlet
 */
@WebServlet("/maintenance/changeRate")
public class MaintenanceChangeRateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> message = new ArrayList<>();
	InsurancePremiumRateLogic logic = new InsurancePremiumRateLogic();
	
	private String HEALTH_INSURANCE = "health_insurance";
	private String CARE_INSURANCE = "care_insurance";
	private String EMPLOYEE_PENSION = "employee_pension";
	private String EMPLOYMENT_INSURANCE = "employment_insurance";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaintenanceChangeRateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, InsurancePremiumRateBean> insurancePremiumRateList = logic.getInsurancePremiumRateAll();
		
		request.setAttribute("insurancePremiumRateList", insurancePremiumRateList);
		
		String view = "/WEB-INF/view/maintenance/changeRate.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		
		List<InsurancePremiumRateBean> insurancePremiumRateList = new ArrayList<>();
		InsurancePremiumRateBean insurancePremiumRate = new InsurancePremiumRateBean();
		int reslt = 0;
		
		int healthInsurance1 = Integer.parseInt(request.getParameter("healthInsurance_1"));
		int healthInsurance2 = Integer.parseInt(request.getParameter("healthInsurance_2"));
		int bHealthInsurance1 = Integer.parseInt(request.getParameter("b_healthInsurance_1"));
		int bHealthInsurance2 = Integer.parseInt(request.getParameter("b_healthInsurance_2"));
		int careInsurance1 = Integer.parseInt(request.getParameter("careInsurance_1"));
		int careInsurance2 = Integer.parseInt(request.getParameter("careInsurance_2"));
		int bCareInsurance1 = Integer.parseInt(request.getParameter("b_careInsurance_1"));
		int bCareInsurance2 = Integer.parseInt(request.getParameter("b_careInsurance_2"));
		int employeePension1 = Integer.parseInt(request.getParameter("employeePension_1"));
		int employeePension2 = Integer.parseInt(request.getParameter("employeePension_2"));
		int bEmployeePension1 = Integer.parseInt(request.getParameter("b_employeePension_1"));
		int bEmployeePension2 = Integer.parseInt(request.getParameter("b_employeePension_2"));
		int employmentInsurance1 = Integer.parseInt(request.getParameter("employmentInsurance_1"));
		int employmentInsurance2 = Integer.parseInt(request.getParameter("employmentInsurance_2"));
		int bEmploymentInsurance1 = Integer.parseInt(request.getParameter("b_employmentInsurance_1"));
		int bEmploymentInsurance2 = Integer.parseInt(request.getParameter("b_employmentInsurance_2"));
		
		if(bHealthInsurance1 != healthInsurance1 || bHealthInsurance2 != healthInsurance2) {
			insurancePremiumRate = new InsurancePremiumRateBean();
			insurancePremiumRate.setInsuranceId(HEALTH_INSURANCE);
			insurancePremiumRate.setInsurancePremiumRate1(healthInsurance1);
			insurancePremiumRate.setInsurancePremiumRate2(healthInsurance2);
			insurancePremiumRateList.add(insurancePremiumRate);
		}
		
		if(bCareInsurance1 != careInsurance1 || bCareInsurance2 != careInsurance2) {
			insurancePremiumRate = new InsurancePremiumRateBean();
			insurancePremiumRate.setInsuranceId(CARE_INSURANCE);
			insurancePremiumRate.setInsurancePremiumRate1(careInsurance1);
			insurancePremiumRate.setInsurancePremiumRate2(careInsurance2);
			insurancePremiumRateList.add(insurancePremiumRate);
		}
		
		if(bEmployeePension1 != employmentInsurance1 || bEmployeePension2 != employeePension2) {
			insurancePremiumRate = new InsurancePremiumRateBean();
			insurancePremiumRate.setInsuranceId(EMPLOYEE_PENSION);
			insurancePremiumRate.setInsurancePremiumRate1(employeePension1);
			insurancePremiumRate.setInsurancePremiumRate2(employeePension2);
			insurancePremiumRateList.add(insurancePremiumRate);
		}
		
		if(bEmploymentInsurance1 != employmentInsurance1 || bEmploymentInsurance2 != employmentInsurance2) {
			insurancePremiumRate = new InsurancePremiumRateBean();
			insurancePremiumRate.setInsuranceId(EMPLOYMENT_INSURANCE);
			insurancePremiumRate.setInsurancePremiumRate1(employmentInsurance1);
			insurancePremiumRate.setInsurancePremiumRate2(employmentInsurance2);
			insurancePremiumRateList.add(insurancePremiumRate);
		}
		
		if(insurancePremiumRateList.size() > 0) {
			reslt = logic.updateInsurancePremiumRate(insurancePremiumRateList);
		} else {
			message.add("値が変更されていません。");
		}
		
		if(reslt == 0) {
			message.add("登録に失敗しました。");
		}
		
		request.setAttribute("message", message);
		
		doGet(request, response);
	}

}
