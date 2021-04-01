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
import jinjikyuyo.logic.MonthryRemunerationInfoLogic;
import jinjikyuyo.logic.SalaryLogic;
import jinjikyuyo.util.JinjikyuyoUtil;
import jinjikyuyo.validator.CommonValidator;

/**
 * 作業時間入力Servlet
 */
@WebServlet("/operatingTime/input")
public class OperatingTimeInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// バリデータ
	private CommonValidator validator = new CommonValidator();
	// 雇用情報Logic
	private EmploymentInfoLogic eLogic = new EmploymentInfoLogic();
	// 所得税マスタLogic
	private IncomeTaxMasterLogic iLogic = new IncomeTaxMasterLogic();
	// 標準月額情報Logic
	private MonthryRemunerationInfoLogic mLogic = new MonthryRemunerationInfoLogic();
	// 給与Logic
	private SalaryLogic sLogic = new SalaryLogic();
	// メッセージ
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
		// 文字コードを設定
		request.setCharacterEncoding("utf-8");
		
		// リクエスト：社員IDを取得
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		// リクエスト：社員名を取得
		String employeeName = request.getParameter("fullName_" + employeeId);
		// リクエスト：誕生日を取得
		String birthday = request.getParameter("birthday_" + employeeId);
		// リクエスト：扶養家族を取得
		String dependents = request.getParameter("dependents_" + employeeId);
		
		// 社員IDが存在する場合
		if (request.getAttribute("employeeId") == null || request.getAttribute("employeeId").equals("")) {
			// 画面に社員IDを設定する
			request.setAttribute("employeeId", employeeId);
		}
		if (request.getAttribute("employeeName") == null || request.getAttribute("employeeName").equals("") ) {
			// 画面に社員名を設定する
			request.setAttribute("employeeName", employeeName);
		}
		if (request.getAttribute("birthday") == null || request.getAttribute("birthday").equals("") ) {
			// 画面に誕生日を設定する
			request.setAttribute("birthday", birthday);
		}
		if (request.getAttribute("dependents") == null || request.getAttribute("dependents").equals("") ) {
			// 画面に扶養家族を設定する
			request.setAttribute("dependents", dependents);
		}
		
		// 画面にメッセージを設定
		request.setAttribute("message", message);

		// 作業時間入力画面
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
		// 画面：社員名を取得
		String employeeName = request.getParameter("employeeName");
		// 画面：社員IDを取得
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		// 画面：誕生日を取得
		String birthday = request.getParameter("birthday");
		// 画面：扶養家族を取得
		String dependents = request.getParameter("dependents");
		
		// リクエストに社員名を設定する
		request.setAttribute("employeeName", employeeName);
		// リクエストに社員IDを設定する
		request.setAttribute("employeeId", employeeId);
		// リクエストに生年月日を設定する
		request.setAttribute("birthday", birthday);
		// リクエストに扶養家族を設定する
		request.setAttribute("dependents", dependents);
		
		// 画面：稼働年を取得
		String year = request.getParameter("year");
		// 画面：稼働月を取得
		String month = request.getParameter("month");
		// 入力チェック
		if(!validator.isRequired(year) || !validator.isRequired(month)) {
			message.add("稼働月を選択してください。");
		} else if("12".equals(month)) {
			// 稼働月が12月の場合、翌年1月に変更する
			year = String.valueOf(Integer.parseInt(year) + 1);
			month = "01";
		} else {
			// 稼働月に1ヶ月加算する
			String nextMonth = "0" + String.valueOf(Integer.parseInt(month) + 1);
			month = nextMonth.substring(nextMonth.length() - 2);
		}
		// 画面：稼働時間を取得する
		String operatingTime1 = request.getParameter("operatingTime1");
		String operatingTime2 = request.getParameter("operatingTime2");
		// 入力チェック
		if(!validator.isRequired(operatingTime1) || !validator.isRequired(operatingTime2)) {
			message.add("稼働時間を入力してください。");
		} else if (!validator.isNumber(operatingTime1) || !validator.isNumber(operatingTime2)) {
			message.add("稼働時間は半角数字で入力してください。");
		} else if(operatingTime1.equals("0")) {
			message.add("稼働時間を入力してください。");
		}
		
		// メッセージが0件の場合
		if (message.size() == 0) {
			
			// 基準標準月額
			int judgAmount = 0;
			// 対象年
			String targetYear;
			if(Integer.parseInt(month) < 9) {
				targetYear = String.valueOf(Integer.parseInt(year) - 1);
			} else {
				targetYear = year;
			}
			// 標準月額取得
			judgAmount = mLogic.getMonthryRemuneration(employeeId, targetYear);
			
			// 稼働時間
			double operatingTime = Double.parseDouble(operatingTime1 + "." + operatingTime2);
			// 雇用情報取得
			EmploymentInfoBean employment = eLogic.getEmploymentInfo(employeeId);
			
			if (employment != null && employment.getEmployeeId() != 0) {
				// 基本給
				int basicSalary = employment.getBasicSalary();
				// 職務手当
				int dutiesAllowance = employment.getDutiesAllowance();
				// 通勤手当
				int commutingAllowance = employment.getCommutingAllowance();
				// 時間外手当
				int overtimeAllowance = 0;
				// その他手当
				int otherAllowance = employment.getOtherAllowance();
				// 超過金額
				int excessMoney = employment.getExcessMoney();
				// 控除金額
				int eductionMoney = employment.getEductionMoney();
				// 下限基準時間
				int lowerLimit = employment.getLowerLimit();
				// 上限基準時間
				int upperLimit = employment.getUpperLimit();
				// 時間不足控除
				int shortageDeduction = 0;
				// 下限時間
				double lowarTime = 0;
				// 上限時間
				double upperTime = 0;
				// 市民税
				int hasu = employment.getResidentTax() / 100 % 12 * 100;
				int residentTax = (employment.getResidentTax() - hasu) / 12;
				if("06".equals(month)) {
					residentTax += hasu;
				}
				
				// 前月プールフラグ
				String beforePoolFlag = sLogic.getBeforePoolFlag(employeeId, year + request.getParameter("month"));
				
				// 稼働時間が下限時間を切る場合
				if(lowerLimit > operatingTime) {
					// 時間不足控除
					lowarTime = lowerLimit - operatingTime;
					shortageDeduction = (int)Math.round(lowarTime * eductionMoney);
				}
				// 稼働時間が上限金額を超える場合
				else if(upperLimit < operatingTime) {
					// 時間外手当
					upperTime = operatingTime - upperLimit;
					overtimeAllowance = (int)Math.round(upperTime * excessMoney);
				}
				
				// 7月〜9月の場合
				if("07".equals(month) || "08".equals(month) || "09".equals(month)) {
					// プールしている超過金を超過金に加算する
					String poolMonth = "0" + String.valueOf(Integer.parseInt(month) - 3);
					String poolYm = year + poolMonth.substring(poolMonth.length() - 2);
					int poolOvertimeAllowance = sLogic.getPoolOvertimeAllowance(employeeId, poolYm);
					overtimeAllowance = overtimeAllowance + poolOvertimeAllowance;
				}
				
				// 総支給額
				int totalPayment = basicSalary + dutiesAllowance + commutingAllowance + overtimeAllowance + otherAllowance;
				// 標準月額
				int monthryRemuneration = 0;
				// 健康保険料
				int healthInsurance = 0;
				// 厚生年金保険料
				int employeePension = 0;
				// 雇用保険料
				int employmentInsurance = 0;
				// 控除合計額
				int totalDeduction = 0;
				// 課税所得
				int otherPayment = 0;
				// 差引支給額
				int payment = 0;
				// 所得税
				int incomeTax = 0;
				// 保険料合計
				int totalInsurance = 0;
				// 年齢
				int age = 0;

				if(judgAmount == 0) {
					judgAmount = totalPayment;
				}
		
				if(month.compareTo(birthday.substring(4,6)) > 0) {
					age = Integer.parseInt(year) - Integer.parseInt(birthday.substring(0,4)) - 1;
				} else {
					age = Integer.parseInt(year) - Integer.parseInt(birthday.substring(0,4));
				}
				
				monthryRemuneration = util.searchMonthryRemuneration(judgAmount);
				healthInsurance = util.getHealthInsurance(monthryRemuneration, targetYear + month, age);
				employeePension = util.getEmployeePension(monthryRemuneration, targetYear + month);
				employmentInsurance = util.getEmploymentInsurance(totalPayment, targetYear + month);
				totalInsurance = healthInsurance + employeePension + employmentInsurance;
				otherPayment = totalPayment - totalInsurance - shortageDeduction;
				incomeTax = iLogic.getIncomeTax(otherPayment, dependents);
				totalDeduction = totalInsurance + shortageDeduction + incomeTax + residentTax;
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
				// 住民税
				request.setAttribute("residentTax", String.valueOf(residentTax));
				// 前月プールフラグ
				request.setAttribute("beforePoolFlag", String.valueOf(beforePoolFlag));
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
