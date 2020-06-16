package jinjikyuyo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jinjikyuyo.bean.SalaryBean;
import jinjikyuyo.logic.SalaryLogic;

/**
 * Servlet implementation class SalaryInfoRegistServlet
 */
@WebServlet("/salaryInfo/regist")
public class SalaryInfoRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private List<String> message = new ArrayList<>();
	private SalaryLogic logic = new SalaryLogic();
	private OperatingTimeInputServlet oSerblet = new OperatingTimeInputServlet();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryInfoRegistServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		// 社員名
		request.setAttribute("employeeName", employeeName);
		// 社員ID
		request.setAttribute("employeeId", employeeId);
		// 生年月日
		request.setAttribute("birthday", birthday);
		
		// 稼働月
		String operatingMonth =  request.getParameter("year") + request.getParameter("month");
		// 基準時間
		int lowerLimit = Integer.parseInt(request.getParameter("lowerLimit"));
		int upperLimit = Integer.parseInt(request.getParameter("upperLimit"));
		// 実働時間
		double operatingTime = Double.valueOf(request.getParameter("operatingTime"));
		// 超過時間
		double upperTime = Double.valueOf(request.getParameter("upperTime"));
		// 不足時間
		double lowarTime = Double.valueOf(request.getParameter("lowarTime"));
		// 基本給
		int basicSalary = Integer.parseInt(request.getParameter("basicSalary"));
		// 職務手当
		int dutiesAllowance = Integer.parseInt(request.getParameter("dutiesAllowance"));
		// 通勤手当
		int commutingAllowance = Integer.parseInt(request.getParameter("commutingAllowance"));
		// 時間外手当
		int overtimeAllowance = Integer.parseInt(request.getParameter("overtimeAllowance"));
		// その他手当
		int otherAllowance = Integer.parseInt(request.getParameter("otherAllowance"));
		// 総支給額
		int totalPayment = Integer.parseInt(request.getParameter("totalPayment"));
		// 健康保険
		int healthInsurance = Integer.parseInt(request.getParameter("healthInsurance"));
		// 厚生年金
		int employeePension = Integer.parseInt(request.getParameter("employeePension"));
		// 雇用保険
		int employmentInsurance = Integer.parseInt(request.getParameter("employmentInsurance"));
		// 所得税
		int incomeTax = Integer.parseInt(request.getParameter("incomeTax"));
		// 時間不足控除
		int shortageDeduction = Integer.parseInt(request.getParameter("shortageDeduction"));
		// 控除合計額
		int totalDeduction = Integer.parseInt(request.getParameter("totalDeduction"));
		// 差引支給額
		int payment = Integer.parseInt(request.getParameter("payment"));
		// 超過
		int excessMoney = Integer.parseInt(request.getParameter("excessMoney"));
		// 控除
		int eductionMoney = Integer.parseInt(request.getParameter("eductionMoney"));
		// プールフラグ
		String poolFlag = request.getParameter("poolFlag");
		if (poolFlag == null) {
			poolFlag = "0";
		}
		// 住民税
		int residentTax = Integer.parseInt(request.getParameter("residentTax"));
		
		SalaryBean salary = new SalaryBean();
		salary.setEmployeeId(employeeId);
		salary.setOperatingMonth(operatingMonth);
		salary.setOperatingTime(operatingTime);
		salary.setUpperTime(upperTime);
		salary.setLowarTime(lowarTime);
		salary.setBasicSalary(basicSalary);
		salary.setDutiesAllowance(dutiesAllowance);
		salary.setCommutingAllowance(commutingAllowance);
		salary.setOvertimeAllowance(overtimeAllowance);
		salary.setOtherAllowance(otherAllowance);
		salary.setExcessMoney(excessMoney);
		salary.setEductionMoney(eductionMoney);
		salary.setLowerLimit(lowerLimit);
		salary.setUpperLimit(upperLimit);
		salary.setHealthInsurance(healthInsurance);
		salary.setEmployeePension(employeePension);
		salary.setEmploymentInsurance(employmentInsurance);
		salary.setIncomeTax(incomeTax);
		salary.setShortageDeduction(shortageDeduction);
		salary.setTotalPayment(totalPayment);
		salary.setTotalDeduction(totalDeduction);
		salary.setPayment(payment);
		salary.setPoolFlag(poolFlag);
		salary.setResidentTax(residentTax);
		
		int reslt = logic.registSalaryInfo(salary);
		
		if(reslt == 0) {
			message.add("登録に失敗しました。");
		} else {
			message.add("登録完了しました。");
		}
		
		oSerblet.setMessage(message);
		
		oSerblet.doGet(request, response);
	}

}
