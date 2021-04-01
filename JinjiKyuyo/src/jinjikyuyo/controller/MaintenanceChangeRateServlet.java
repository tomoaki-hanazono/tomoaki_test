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
import jinjikyuyo.validator.CommonValidator;

/**
 * 保険料率変更Servlet
 */
@WebServlet("/maintenance/changeRate")
public class MaintenanceChangeRateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// メッセージ
	private List<String> message = new ArrayList<>();
	// 保険料率Logic
	InsurancePremiumRateLogic logic = new InsurancePremiumRateLogic();
	// バリデータ
	private CommonValidator validator = new CommonValidator();
	
	// 保険ID：健康保険料
	private String HEALTH_INSURANCE = "health_insurance";
	// 保険ID：介護保険料
	private String CARE_INSURANCE = "care_insurance";
	// 保険ID：厚生年金保険料
	private String EMPLOYEE_PENSION = "employee_pension";
	// 保険ID：雇用保険料
	private String EMPLOYMENT_INSURANCE = "employment_insurance";
	// 保険名：健康保険料
	private String HEALTH_INSURANCE_NAME = "健康保険料率";
	// 保険名：介護保険料
	private String CARE_INSURANCE_NAME = "介護保険料率";
	// 保険名：厚生年金保険料
	private String EMPLOYEE_PENSION_NAME = "厚生年金保険料率";
	// 保険名：雇用保険料
	private String EMPLOYMENT_INSURANCE_NAME = "雇用保険料率";
       
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
		// 保険料率一覧取得
		Map<String, InsurancePremiumRateBean> insurancePremiumRateList = logic.getInsurancePremiumRateAll();
		
		// 画面に保険料率一覧を設定
		request.setAttribute("insurancePremiumRateList", insurancePremiumRateList);
		
		// 保険料率変更画面
		String view = "/WEB-INF/view/maintenance/changeRate.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード設定
		request.setCharacterEncoding("utf-8");
		
		// メッセージを初期化
		message = new ArrayList<>();
		
		// リクエストを初期化
		List<InsurancePremiumRateBean> insurancePremiumRateList = new ArrayList<>();
		InsurancePremiumRateBean insurancePremiumRate = new InsurancePremiumRateBean();
		int reslt = 0;
		
		// 画面：適用開始年を取得
		String year = request.getParameter("year");
		// 画面：適用開始月を取得
		String month = request.getParameter("month");
		// 画面：健康保険料率を取得
		int healthInsurance1 = Integer.parseInt(request.getParameter("healthInsurance_1"));
		int healthInsurance2 = Integer.parseInt(request.getParameter("healthInsurance_2"));
		// 画面：健康保険料率（変更前）を取得
		int bHealthInsurance1 = Integer.parseInt(request.getParameter("b_healthInsurance_1"));
		int bHealthInsurance2 = Integer.parseInt(request.getParameter("b_healthInsurance_2"));
		// 画面：介護保険料率を取得
		int careInsurance1 = Integer.parseInt(request.getParameter("careInsurance_1"));
		int careInsurance2 = Integer.parseInt(request.getParameter("careInsurance_2"));
		// 画面：介護保険料率（変更前）を取得
		int bCareInsurance1 = Integer.parseInt(request.getParameter("b_careInsurance_1"));
		int bCareInsurance2 = Integer.parseInt(request.getParameter("b_careInsurance_2"));
		// 画面：厚生年金保険料率を取得
		int employeePension1 = Integer.parseInt(request.getParameter("employeePension_1"));
		int employeePension2 = Integer.parseInt(request.getParameter("employeePension_2"));
		// 画面：厚生年金保険料率（変更前）を取得
		int bEmployeePension1 = Integer.parseInt(request.getParameter("b_employeePension_1"));
		int bEmployeePension2 = Integer.parseInt(request.getParameter("b_employeePension_2"));
		// 画面：雇用保険料率を取得
		int employmentInsurance1 = Integer.parseInt(request.getParameter("employmentInsurance_1"));
		int employmentInsurance2 = Integer.parseInt(request.getParameter("employmentInsurance_2"));
		// 画面：雇用保険料率（変更前）を取得
		int bEmploymentInsurance1 = Integer.parseInt(request.getParameter("b_employmentInsurance_1"));
		int bEmploymentInsurance2 = Integer.parseInt(request.getParameter("b_employmentInsurance_2"));
		
		// 入力チェック
		if(!validator.isRequired(year) || !validator.isRequired(month)) {
			message.add("適用開始月は必須です。");
		}
		
		if(message.size() == 0) {
			// 健康保険料率が変更されている場合、リクエストに健康保険料率を追加
			if(bHealthInsurance1 != healthInsurance1 || bHealthInsurance2 != healthInsurance2) {
				insurancePremiumRate = new InsurancePremiumRateBean();
				insurancePremiumRate.setInsuranceId(HEALTH_INSURANCE);
				insurancePremiumRate.setStartDate(year + month);
				insurancePremiumRate.setInsuranceName(HEALTH_INSURANCE_NAME);
				insurancePremiumRate.setInsurancePremiumRate1(healthInsurance1);
				insurancePremiumRate.setInsurancePremiumRate2(healthInsurance2);
				insurancePremiumRateList.add(insurancePremiumRate);
			}
			
			// 介護保険料率が変更されている場合、リクエストに介護保険料率を追加
			if(bCareInsurance1 != careInsurance1 || bCareInsurance2 != careInsurance2) {
				insurancePremiumRate = new InsurancePremiumRateBean();
				insurancePremiumRate.setInsuranceId(CARE_INSURANCE);
				insurancePremiumRate.setStartDate(year + month);
				insurancePremiumRate.setInsuranceName(CARE_INSURANCE_NAME);
				insurancePremiumRate.setInsurancePremiumRate1(careInsurance1);
				insurancePremiumRate.setInsurancePremiumRate2(careInsurance2);
				insurancePremiumRateList.add(insurancePremiumRate);
			}
			
			// 厚生年金保険料率が変更されている場合、リクエストに厚生年金保険料率を追加
			if(bEmployeePension1 != employeePension1 || bEmployeePension2 != employeePension2) {
				insurancePremiumRate = new InsurancePremiumRateBean();
				insurancePremiumRate.setInsuranceId(EMPLOYEE_PENSION);
				insurancePremiumRate.setStartDate(year + month);
				insurancePremiumRate.setInsuranceName(EMPLOYEE_PENSION_NAME);
				insurancePremiumRate.setInsurancePremiumRate1(employeePension1);
				insurancePremiumRate.setInsurancePremiumRate2(employeePension2);
				insurancePremiumRateList.add(insurancePremiumRate);
			}
			
			// 雇用保険料率が変更されている場合、リクエストに雇用保険料率を追加
			if(bEmploymentInsurance1 != employmentInsurance1 || bEmploymentInsurance2 != employmentInsurance2) {
				insurancePremiumRate = new InsurancePremiumRateBean();
				insurancePremiumRate.setInsuranceId(EMPLOYMENT_INSURANCE);
				insurancePremiumRate.setStartDate(year + month);
				insurancePremiumRate.setInsuranceName(EMPLOYMENT_INSURANCE_NAME);
				insurancePremiumRate.setInsurancePremiumRate1(employmentInsurance1);
				insurancePremiumRate.setInsurancePremiumRate2(employmentInsurance2);
				insurancePremiumRateList.add(insurancePremiumRate);
			}

			// リクエストが1件以上存在する場合
			if(insurancePremiumRateList.size() > 0) {
				// 保険料率登録実施
				reslt = logic.registInsurancePremiumRate(insurancePremiumRateList);
				
				if(reslt == 0) {
					message.add("登録に失敗しました。");
				}
			} else {
				message.add("値が変更されていません。");
			}
		}
		
		request.setAttribute("message", message);
		
		doGet(request, response);
	}

}
