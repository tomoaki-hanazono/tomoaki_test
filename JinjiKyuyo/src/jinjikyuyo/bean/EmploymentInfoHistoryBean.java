package jinjikyuyo.bean;

/**
 * 
 * 雇用情報履歴Bean.
 *
 */
public class EmploymentInfoHistoryBean implements java.io.Serializable {
	// 履歴番号
	private int historyId;
	// 社員番号
	private int employeeId;
	// 社員名
	private String employeeName;
	// 雇用開始日
	private String employmentPeriodStart;
	// 雇用終了日
	private String employmentPeriodEnd;
	// 基本給
	private int basicSalary;
	// 職務手当
	private int dutiesAllowance;
	// 通勤手当
	private int commutingAllowance;
	// 時間外手当
	private int overtimeAllowance;
	// その他手当
	private int otherAllowance;
	// 超過金額
	private int excessMoney;
	// 控除金額
	private int eductionMoney;
	// 下限基準時間
	private int lowerLimit;
	// 上限基準時間
	private int upperLimit;
	// 職務内容
	private String jobDescription;
	// 備考
	private String remarks;
	// 住民税
	private int residentTax;
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmploymentPeriodStart() {
		return employmentPeriodStart;
	}
	public void setEmploymentPeriodStart(String employmentPeriodStart) {
		this.employmentPeriodStart = employmentPeriodStart;
	}
	public String getEmploymentPeriodEnd() {
		return employmentPeriodEnd;
	}
	public void setEmploymentPeriodEnd(String employmentPeriodEnd) {
		this.employmentPeriodEnd = employmentPeriodEnd;
	}
	public int getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}
	public int getDutiesAllowance() {
		return dutiesAllowance;
	}
	public void setDutiesAllowance(int dutiesAllowance) {
		this.dutiesAllowance = dutiesAllowance;
	}
	public int getCommutingAllowance() {
		return commutingAllowance;
	}
	public void setCommutingAllowance(int commutingAllowance) {
		this.commutingAllowance = commutingAllowance;
	}
	public int getOvertimeAllowance() {
		return overtimeAllowance;
	}
	public void setOvertimeAllowance(int overtimeAllowance) {
		this.overtimeAllowance = overtimeAllowance;
	}
	public int getOtherAllowance() {
		return otherAllowance;
	}
	public void setOtherAllowance(int otherAllowance) {
		this.otherAllowance = otherAllowance;
	}
	public int getExcessMoney() {
		return excessMoney;
	}
	public void setExcessMoney(int excessMoney) {
		this.excessMoney = excessMoney;
	}
	public int getEductionMoney() {
		return eductionMoney;
	}
	public void setEductionMoney(int eductionMoney) {
		this.eductionMoney = eductionMoney;
	}
	public int getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public int getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getResidentTax() {
		return residentTax;
	}
	public void setResidentTax(int residentTax) {
		this.residentTax = residentTax;
	}
	public void set(EmploymentInfoBean before) {
		this.setEmployeeId(before.getEmployeeId());
		this.setEmployeeName(before.getEmployeeName());
		this.setEmploymentPeriodStart(before.getEmploymentPeriodStart());
		this.setEmploymentPeriodEnd(before.getEmploymentPeriodEnd());
		this.setBasicSalary(before.getBasicSalary());
		this.setDutiesAllowance(before.getDutiesAllowance());
		this.setCommutingAllowance(before.getCommutingAllowance());
		this.setOvertimeAllowance(before.getOvertimeAllowance());
		this.setOtherAllowance(before.getOtherAllowance());
		this.setExcessMoney(before.getExcessMoney());
		this.setEductionMoney(before.getEductionMoney());
		this.setLowerLimit(before.getLowerLimit());
		this.setUpperLimit(before.getUpperLimit());
		this.setJobDescription(before.getJobDescription());
		this.setRemarks(before.getRemarks());
		this.setResidentTax(before.getResidentTax());
	}
}
