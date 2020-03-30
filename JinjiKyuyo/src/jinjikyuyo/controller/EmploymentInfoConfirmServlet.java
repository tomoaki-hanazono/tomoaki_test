package jinjikyuyo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jinjikyuyo.bean.EmploymentInfoBean;
import jinjikyuyo.bean.EmploymentInfoHistoryBean;
import jinjikyuyo.logic.EmploymentInfoLogic;
import jinjikyuyo.validator.CommonValidator;

/**
 * Servlet implementation class EmploymentInfoConfirmServlet
 */
@WebServlet("/employmentInfo/confirm")
public class EmploymentInfoConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmploymentInfoLogic logic = new EmploymentInfoLogic();
	private CommonValidator validator = new CommonValidator();
	private List<String> message = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmploymentInfoConfirmServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String employeeName = request.getParameter("fullName_" + employeeId);
		
		EmploymentInfoBean employmentInfo = logic.getEmploymentInfo(employeeId);
		List<EmploymentInfoHistoryBean> employmentHistoryList = logic.getEmploymentInfoList(employeeId);
		
		request.setAttribute("employmentInfo", employmentInfo);
		request.setAttribute("employmentHistoryList", employmentHistoryList);
		if (request.getAttribute("employeeId") == null || request.getAttribute("employeeId").equals("")) {
			request.setAttribute("employeeId", employeeId);
		}
		if (request.getAttribute("employeeName") == null || request.getAttribute("employeeName").equals("") ) {
			request.setAttribute("employeeName", employeeName);
		}
		
		request.setAttribute("message", message);
		
		String view = "/WEB-INF/view/employmentinfo/confirm/confirm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		message = new ArrayList<>();
		EmploymentInfoBean employmentInfo = new EmploymentInfoBean();
		String type = request.getParameter("type");
		
		if (type.equals("add")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName = request.getParameter("employeeName");
			String startYear = request.getParameter("startYear");
			String startMonth = request.getParameter("startMonth");
			String startDay = request.getParameter("startDay");
			String employmentInfoPeriodStart = startYear + startMonth + startDay;
			String endYear = request.getParameter("endYear");
			String endMonth = request.getParameter("endMonth");
			String endDay = request.getParameter("endDay");
			String employmentInfoPeriodEnd = endYear + endMonth + endDay;
			String basicSalary = request.getParameter("basicSalary");
			String dutiesAllowance = request.getParameter("dutiesAllowance");
			String commutingAllowance = request.getParameter("commutingAllowance");
			String overtimeAllowance = request.getParameter("overtimeAllowance");
			String otherAllowance = request.getParameter("otherAllowance");
			String lowerLimit = request.getParameter("lowerLimit");
			String upperLimit = request.getParameter("upperLimit");
			String excessMoney = request.getParameter("excessMoney");
			String eductionMoney = request.getParameter("eductionMoney");
			String jobDescription = request.getParameter("jobDescription");
			String remarks = request.getParameter("remarks");
			
			if (!validator.isRequired(startYear) || !validator.isRequired(startMonth)
					|| !validator.isRequired(startDay) || !validator.isRequired(endYear)
					|| !validator.isRequired(endMonth) || !validator.isRequired(endDay)) {
				message.add("雇用期間は必須です。");
			} else if (!validator.isDate(employmentInfoPeriodStart) || !validator.isDate(employmentInfoPeriodEnd)) {
				message.add("雇用期間の日付が不正です。");
			}
			
			if (!validator.isRequired(basicSalary)) {
				message.add("基本給は必須です。");
			} else if (!validator.isNumber(basicSalary)) {
				message.add("基本給は半角数値で入力してください。");
			}
			
			if (validator.isRequired(dutiesAllowance)
					&& !validator.isNumber(dutiesAllowance)) {
				message.add("職務手当は半角数値で入力してください。");
			}
			
			if (validator.isRequired(commutingAllowance)
					&& !validator.isNumber(commutingAllowance)) {
				message.add("通勤手当は半角数値で入力してください。");
			}
			
			if (validator.isRequired(overtimeAllowance)
					&& !validator.isNumber(overtimeAllowance)) {
				message.add("時間外手当は半角数値で入力してください。");
			}
			
			if (validator.isRequired(otherAllowance)
					&& !validator.isNumber(overtimeAllowance)) {
				message.add("その他手当は半角数値で入力してください。");
			}
			
			if (!validator.isRequired(lowerLimit) || !validator.isRequired(upperLimit)) {
				message.add("基準時間は必須です。");
			} else if (!validator.isNumber(lowerLimit) || !validator.isNumber(upperLimit)) {
				message.add("基準時間は半角数値で入力してください。");
			}
			
			if (!validator.isRequired(excessMoney)) {
				message.add("超過は必須です。");
			} else if (!validator.isNumber(excessMoney)) {
				message.add("超過は半角数値で入力してください。");
			}
			
			if (!validator.isRequired(eductionMoney)) {
				message.add("控除は必須です。");
			} else if (!validator.isNumber(eductionMoney)) {
				message.add("控除は半角数値で入力してください。");
			}
			
			if (message.size() == 0) {
				employmentInfo.setEmployeeId(employeeId);
				employmentInfo.setEmployeeName(employeeName);
				employmentInfo.setEmploymentPeriodStart(employmentInfoPeriodStart);
				employmentInfo.setEmploymentPeriodEnd(employmentInfoPeriodEnd);
				employmentInfo.setBasicSalary(Integer.parseInt(basicSalary));
				employmentInfo.setDutiesAllowance(Integer.parseInt(dutiesAllowance));
				employmentInfo.setCommutingAllowance(Integer.parseInt(commutingAllowance));
				employmentInfo.setOvertimeAllowance(Integer.parseInt(overtimeAllowance));
				employmentInfo.setOtherAllowance(Integer.parseInt(otherAllowance));
				employmentInfo.setLowerLimit(Integer.parseInt(lowerLimit));
				employmentInfo.setUpperLimit(Integer.parseInt(upperLimit));
				employmentInfo.setExcessMoney(Integer.parseInt(excessMoney));
				employmentInfo.setEductionMoney(Integer.parseInt(eductionMoney));
				employmentInfo.setJobDescription(jobDescription);
				employmentInfo.setRemarks(remarks);
				
				int result = logic.registEmploymentInfo(employmentInfo);
				
				if (result == 0) {
					message.add("雇用情報の登録に失敗しました。");
				}
			}
			
			request.setAttribute("employeeId", employeeId);
			request.setAttribute("employeeName", employeeName);
			
		} else if (type.equals("update")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName = request.getParameter("employeeName");

			employmentInfo = new EmploymentInfoBean();
			String startYear = request.getParameter("startYear");
			String startMonth = request.getParameter("startMonth");
			String startDay = request.getParameter("startDay");
			String employmentInfoPeriodStart = startYear + startMonth + startDay;
			String endYear = request.getParameter("endYear");
			String endMonth = request.getParameter("endMonth");
			String endDay = request.getParameter("endDay");
			String employmentInfoPeriodEnd = endYear + endMonth + endDay;
			String basicSalary = request.getParameter("basicSalary");
			String dutiesAllowance = request.getParameter("dutiesAllowance");
			String commutingAllowance = request.getParameter("commutingAllowance");
			String overtimeAllowance = request.getParameter("overtimeAllowance");
			String otherAllowance = request.getParameter("otherAllowance");
			String lowerLimit = request.getParameter("lowerLimit");
			String upperLimit = request.getParameter("upperLimit");
			String excessMoney = request.getParameter("excessMoney");
			String eductionMoney = request.getParameter("eductionMoney");
			
			if (!validator.isRequired(startYear) || !validator.isRequired(startMonth)
					|| !validator.isRequired(startDay) || !validator.isRequired(endYear)
					|| !validator.isRequired(endMonth) || !validator.isRequired(endDay)) {
				message.add("雇用期間は必須です。");
			} else if (!validator.isDate(employmentInfoPeriodStart) || !validator.isDate(employmentInfoPeriodEnd)) {
				message.add("雇用期間の日付が不正です。");
			}
			
			if (validator.isRequired(basicSalary)
					&& !validator.isNumber(basicSalary)) {
				message.add("基本給は半角数値で入力してください。");
			}
			
			if (validator.isRequired(dutiesAllowance)
					&& !validator.isNumber(dutiesAllowance)) {
				message.add("職務手当は半角数値で入力してください。");
			}
			
			if (validator.isRequired(commutingAllowance)
					&& !validator.isNumber(commutingAllowance)) {
				message.add("通勤手当は半角数値で入力してください。");
			}
			
			if (validator.isRequired(overtimeAllowance)
					&& !validator.isNumber(overtimeAllowance)) {
				message.add("時間外手当は半角数値で入力してください。");
			}
			
			if (validator.isRequired(otherAllowance)
					&& !validator.isNumber(overtimeAllowance)) {
				message.add("その他手当は半角数値で入力してください。");
			}
			
			if ((validator.isRequired(lowerLimit) && validator.isRequired(upperLimit))
					&& (!validator.isNumber(lowerLimit) || !validator.isNumber(upperLimit))) {
				message.add("基準時間は半角数値で入力してください。");
			}
			
			if (validator.isRequired(excessMoney)
					&& !validator.isNumber(excessMoney)) {
				message.add("超過は半角数値で入力してください。");
			}
			
			if (validator.isRequired(eductionMoney)
					&& !validator.isNumber(eductionMoney)) {
				message.add("控除は半角数値で入力してください。");
			}
			
			if (message.size() == 0) {
				employmentInfo.setEmployeeId(employeeId);
				employmentInfo.setEmployeeName(employeeName);
				employmentInfo.setEmploymentPeriodStart(employmentInfoPeriodStart);
				employmentInfo.setEmploymentPeriodEnd(employmentInfoPeriodEnd);
				employmentInfo.setBasicSalary(Integer.parseInt(basicSalary));
				employmentInfo.setDutiesAllowance(Integer.parseInt(dutiesAllowance));
				employmentInfo.setCommutingAllowance(Integer.parseInt(commutingAllowance));
				employmentInfo.setOvertimeAllowance(Integer.parseInt(overtimeAllowance));
				employmentInfo.setOtherAllowance(Integer.parseInt(otherAllowance));
				employmentInfo.setLowerLimit(Integer.parseInt(lowerLimit));
				employmentInfo.setUpperLimit(Integer.parseInt(upperLimit));
				employmentInfo.setExcessMoney(Integer.parseInt(excessMoney));
				employmentInfo.setEductionMoney(Integer.parseInt(eductionMoney));
				employmentInfo.setJobDescription(null);
				employmentInfo.setRemarks(null);
					
				int reslt = logic.updateEmploymentInfo(employmentInfo);
				
				if (reslt == 0) {
					message.add("雇用情報の登録に失敗しました。");
				}
			}
			
			request.setAttribute("employeeId", employeeId);
			request.setAttribute("employeeName", employeeName);
		}
		
		this.doGet(request, response);
	}

}
