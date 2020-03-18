package jinjikyuyo.bean;

public class EmploymentBean implements java.io.Serializable {
	private int employmentId;
	private int employeeId;
	private String employeeName;
	private String employmentPeriodStart;
	private String employmentPeriodEnd;
	private int basicSalary;
	private int dutiesAllowance;
	private int commutingAllowance;
	private int overtimeAllowance;
	private int otherAllowance;
	private int excessMoney;
	private int eductionMoney;
	private int lowerLimit;
	private int upperLimit;
	private String jobDescription;
	private String remarks;
	public int getEmploymentId() {
		return employmentId;
	}
	public void setEmploymentId(int employmentId) {
		this.employmentId = employmentId;
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
}
