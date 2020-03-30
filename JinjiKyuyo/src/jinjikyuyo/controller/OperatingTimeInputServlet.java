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
import jinjikyuyo.logic.EmploymentInfoLogic;
import jinjikyuyo.logic.IncomeTaxMasterLogic;
import jinjikyuyo.util.JinjikyuyoUtil;
import jinjikyuyo.validator.CommonValidator;

/**
 * Servlet implementation class OperatingTimeInputServlet
 */
@WebServlet("/operatingTime/input")
public class OperatingTimeInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CommonValidator validator = new CommonValidator();
	private EmploymentInfoLogic eLogic = new EmploymentInfoLogic();
	private IncomeTaxMasterLogic iLogic = new IncomeTaxMasterLogic();
	private List<String> message = new ArrayList<>();
	
	private JinjikyuyoUtil util = new JinjikyuyoUtil();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperatingTimeInputServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String employeeName = request.getParameter("fullName_" + employeeId);
		String birthday = request.getParameter("birthday_" + employeeId);
		String dependents = request.getParameter("dependents_" + employeeId);
		
		if (request.getAttribute("employeeId") == null || request.getAttribute("employeeId").equals("")) {
			request.setAttribute("employeeId", employeeId);
		}
		if (request.getAttribute("employeeName") == null || request.getAttribute("employeeName").equals("") ) {
			request.setAttribute("employeeName", employeeName);
		}
		if (request.getAttribute("birthday") == null || request.getAttribute("birthday").equals("") ) {
			request.setAttribute("birthday", birthday);
		}
		if (request.getAttribute("dependents") == null || request.getAttribute("dependents").equals("") ) {
			request.setAttribute("dependents", dependents);
		}
		
		request.setAttribute("message", message);

		String view = "/WEB-INF/view/operatingtime/input/input.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		
		String employeeName = request.getParameter("employeeName");
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String birthday = request.getParameter("birthday");
		String dependents = request.getParameter("dependents");
		
		// 社員名
		request.setAttribute("employeeName", employeeName);
		// 社員ID
		request.setAttribute("employeeId", employeeId);
		// 生年月日
		request.setAttribute("birthday", birthday);
		// 扶養家族
		request.setAttribute("dependents", dependents);
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if(!validator.isRequired(year) || !validator.isRequired(month)) {
			message.add("稼働月を選択してください。");
		} else if("12".equals(month)) {
			year = String.valueOf(Integer.parseInt(year) + 1);
			month = "01";
		} else {
			String nextMonth = "0" + String.valueOf(Integer.parseInt(month) + 1);
			month = nextMonth.substring(nextMonth.length() - 2);
		}
		String operatingTime1 = request.getParameter("operatingTime1");
		String operatingTime2 = request.getParameter("operatingTime2");
		if(!validator.isRequired(operatingTime1) || !validator.isRequired(operatingTime2)) {
			message.add("稼働時間を入力してください。");
		} else if (!validator.isNumber(operatingTime1) || !validator.isNumber(operatingTime2)) {
			message.add("稼働時間は半角数字で入力してください。");
		} else if(operatingTime1.equals("0")) {
			message.add("稼働時間を入力してください。");
		}
		
		if (message.size() == 0) {
			double operatingTime = Double.parseDouble(operatingTime1 + "." + operatingTime2);
			EmploymentInfoBean employment = eLogic.getEmploymentInfo(employeeId);
			
			if (employment != null && employment.getEmployeeId() != 0) {
				int basicSalary = employment.getBasicSalary();
				int dutiesAllowance = employment.getDutiesAllowance();
				int commutingAllowance = employment.getCommutingAllowance();
				int overtimeAllowance = 0;
				int otherAllowance = employment.getOtherAllowance();
				int excessMoney = employment.getExcessMoney();
				int eductionMoney = employment.getEductionMoney();
				int lowerLimit = employment.getLowerLimit();
				int upperLimit = employment.getUpperLimit();
				int shortageDeduction = 0;
				double lowarTime = 0;
				double upperTime = 0;
				
				if(lowerLimit > operatingTime) {
					lowarTime = lowerLimit - operatingTime;
					shortageDeduction = (int)Math.round(lowarTime * eductionMoney);
				} else if(upperLimit < operatingTime) {
					upperTime = operatingTime - upperLimit;
					overtimeAllowance = (int)Math.round(upperTime * excessMoney);
				}
				
				int totalPayment = basicSalary + dutiesAllowance + commutingAllowance + overtimeAllowance + otherAllowance;
				int monthryRemuneration = 0;
				
				int healthInsurance = 0;
				int employeePension = 0;
				int employmentInsurance = 0;
				int totalDeduction = 0;
				int otherPayment = 0;
				int payment = 0;
				int incomeTax = 0;
				int totalInsurance = 0;
				int age = 0;
		
				if(month.compareTo(birthday.substring(4,6)) > 0) {
					age = Integer.parseInt(year) - Integer.parseInt(birthday.substring(0,4)) - 1;
				} else {
					age = Integer.parseInt(year) - Integer.parseInt(birthday.substring(0,4));
				}
				
				monthryRemuneration = util.searchMonthryRemuneration(totalPayment);
				healthInsurance = util.getHealthInsurance(monthryRemuneration, year + month, age);
				employeePension = util.getEmployeePension(monthryRemuneration, year + month);
				employmentInsurance = util.getEmploymentInsurance(monthryRemuneration, year + month);
				totalInsurance = healthInsurance + employeePension + employmentInsurance;
				otherPayment = totalPayment - totalInsurance - shortageDeduction;
				incomeTax = iLogic.getIncomeTax(otherPayment, dependents);
				totalDeduction = totalInsurance + shortageDeduction + incomeTax;
				payment = otherPayment - incomeTax;
				
				// 稼働月
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				// 基準時間
				request.setAttribute("lowerLimit", String.valueOf(lowerLimit));
				request.setAttribute("upperLimit", String.valueOf(upperLimit));
				// 実働時間
				request.setAttribute("operatingTime", String.valueOf(operatingTime));
				// 超過時間
				request.setAttribute("upperTime", String.valueOf(upperTime));
				// 不足時間
				request.setAttribute("lowarTime", String.valueOf(lowarTime));
				// 基本給
				request.setAttribute("basicSalary", String.valueOf(basicSalary));
				// 職務手当
				request.setAttribute("dutiesAllowance", String.valueOf(dutiesAllowance));
				// 通勤手当
				request.setAttribute("commutingAllowance", String.valueOf(commutingAllowance));
				// 時間外手当
				request.setAttribute("overtimeAllowance", String.valueOf(overtimeAllowance));
				// その他手当
				request.setAttribute("otherAllowance", String.valueOf(otherAllowance));
				// 総支給額
				request.setAttribute("totalPayment", String.valueOf(totalPayment));
				// 健康保険
				request.setAttribute("healthInsurance", String.valueOf(healthInsurance));
				// 厚生年金
				request.setAttribute("employeePension", String.valueOf(employeePension));
				// 雇用保険
				request.setAttribute("employmentInsurance", String.valueOf(employmentInsurance));
				// 保険料合計額
				request.setAttribute("totalInsurance", String.valueOf(totalInsurance));
				// 所得税
				request.setAttribute("incomeTax", String.valueOf(incomeTax));
				// 時間不足控除
				request.setAttribute("shortageDeduction", String.valueOf(shortageDeduction));
				// 控除合計額
				request.setAttribute("totalDeduction", String.valueOf(totalDeduction));
				// 差引支給額
				request.setAttribute("payment", String.valueOf(payment));
				// 超過
				request.setAttribute("excessMoney", String.valueOf(excessMoney));
				// 控除
				request.setAttribute("eductionMoney", String.valueOf(eductionMoney));
			} else {
				message.add("雇用情報がありません。");
			}
		}
		
		doGet(request, response);
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}
}
