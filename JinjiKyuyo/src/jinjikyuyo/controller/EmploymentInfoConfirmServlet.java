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

import jinjikyuyo.bean.EmploymentBean;
import jinjikyuyo.logic.EmploymentLogic;
import jinjikyuyo.validator.CommonValidator;

/**
 * Servlet implementation class EmploymentInfoConfirmServlet
 */
@WebServlet("/employmentInfo/confirm")
public class EmploymentInfoConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmploymentLogic logic = new EmploymentLogic();
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
		
		List<EmploymentBean> list = logic.getEmploymentInfoList(employeeId);
		
		request.setAttribute("employmentList", list);
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
		EmploymentBean employment = new EmploymentBean();
		String type = request.getParameter("type");
		
		if (type.equals("add")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName = request.getParameter("employeeName");
			String startYear = request.getParameter("startYear");
			String startMonth = request.getParameter("startMonth");
			String startDay = request.getParameter("startDay");
			String employmentPeriodStart = startYear + startMonth + startDay;
			String endYear = request.getParameter("endYear");
			String endMonth = request.getParameter("endMonth");
			String endDay = request.getParameter("endDay");
			String employmentPeriodEnd = endYear + endMonth + endDay;
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
			} else if (!validator.isDate(employmentPeriodStart) || !validator.isDate(employmentPeriodEnd)) {
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
				employment.setEmployeeId(employeeId);
				employment.setEmployeeName(employeeName);
				employment.setEmploymentPeriodStart(employmentPeriodStart);
				employment.setEmploymentPeriodEnd(employmentPeriodEnd);
				employment.setBasicSalary(Integer.parseInt(basicSalary));
				employment.setDutiesAllowance(Integer.parseInt(dutiesAllowance));
				employment.setCommutingAllowance(Integer.parseInt(commutingAllowance));
				employment.setOvertimeAllowance(Integer.parseInt(overtimeAllowance));
				employment.setOtherAllowance(Integer.parseInt(otherAllowance));
				employment.setLowerLimit(Integer.parseInt(lowerLimit));
				employment.setUpperLimit(Integer.parseInt(upperLimit));
				employment.setExcessMoney(Integer.parseInt(excessMoney));
				employment.setEductionMoney(Integer.parseInt(eductionMoney));
				employment.setJobDescription(jobDescription);
				employment.setRemarks(remarks);
				
				int result = logic.registEmploymentInfo(employment);
				
				if (result == 0) {
					message.add("雇用情報の登録に失敗しました。");
				}
			}
			
			request.setAttribute("employeeId", employeeId);
			request.setAttribute("employeeName", employeeName);
			
		} else if (type.equals("update")) {
			List<EmploymentBean> list = new ArrayList<>();
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName = request.getParameter("employeeName");
			String[] employmentIdList = request.getParameterValues("employmentId");
			
			for (String employmentId : employmentIdList) {
				employment = new EmploymentBean();
				String basicSalary = request.getParameter("basicSalary_" + employmentId);
				String dutiesAllowance = request.getParameter("dutiesAllowance_" + employmentId);
				String commutingAllowance = request.getParameter("commutingAllowance_" + employmentId);
				String overtimeAllowance = request.getParameter("overtimeAllowance_" + employmentId);
				String otherAllowance = request.getParameter("otherAllowance_" + employmentId);
				String lowerLimit = request.getParameter("lowerLimit_" + employmentId);
				String upperLimit = request.getParameter("upperLimit_" + employmentId);
				String excessMoney = request.getParameter("excessMoney_" + employmentId);
				String eductionMoney = request.getParameter("eductionMoney_" + employmentId);
				
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
					employment.setEmploymentId(Integer.parseInt(employmentId));
					employment.setEmployeeId(employeeId);
					employment.setEmployeeName(employeeName);
					employment.setEmploymentPeriodStart(null);
					employment.setEmploymentPeriodEnd(null);
					employment.setBasicSalary(Integer.parseInt(basicSalary));
					employment.setDutiesAllowance(Integer.parseInt(dutiesAllowance));
					employment.setCommutingAllowance(Integer.parseInt(commutingAllowance));
					employment.setOvertimeAllowance(Integer.parseInt(overtimeAllowance));
					employment.setOtherAllowance(Integer.parseInt(otherAllowance));
					employment.setLowerLimit(Integer.parseInt(lowerLimit));
					employment.setUpperLimit(Integer.parseInt(upperLimit));
					employment.setExcessMoney(Integer.parseInt(excessMoney));
					employment.setEductionMoney(Integer.parseInt(eductionMoney));
					employment.setJobDescription(null);
					employment.setRemarks(null);
					
					list.add(employment);
				}
			}
			
			if (list.size() > 0) {
				int reslt = logic.updateEmploymentInfo(list);
				
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
