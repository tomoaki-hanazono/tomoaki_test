package jinjikyuyo.bean;

/**
 * 
 * 給与Bean.
 *
 */
public class SalaryBean implements java.io.Serializable {
	// 社員番号
	private int employeeId;
	// 稼働月
	private String operatingMonth;
	// 稼働時間
	private double operatingTime;
	// 超過時間
	private double upperTime;
	// 不足時間
	private double lowarTime;
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
	// 健康保険
	private int healthInsurance;
	// 厚生年金
	private int employeePension;
	// 雇用保険
	private int employmentInsurance;
	// 所得税
	private int incomeTax;
	// 時間不足控除
	private int shortageDeduction;
	// 総支給額
	private int totalPayment;
	// 控除合計額
	private int totalDeduction;
	// 差引支給額
	private int payment;
	// プールフラグ
	private String poolFlag;
	// 住民税
	private int residentTax;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getOperatingMonth() {
		return operatingMonth;
	}
	public void setOperatingMonth(String operatingMonth) {
		this.operatingMonth = operatingMonth;
	}
	public double getOperatingTime() {
		return operatingTime;
	}
	public void setOperatingTime(double operatingTime) {
		this.operatingTime = operatingTime;
	}
	public double getUpperTime() {
		return upperTime;
	}
	public void setUpperTime(double upperTime) {
		this.upperTime = upperTime;
	}
	public double getLowarTime() {
		return lowarTime;
	}
	public void setLowarTime(double lowarTime) {
		this.lowarTime = lowarTime;
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
	public int getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(int healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	public int getEmployeePension() {
		return employeePension;
	}
	public void setEmployeePension(int employeePension) {
		this.employeePension = employeePension;
	}
	public int getEmploymentInsurance() {
		return employmentInsurance;
	}
	public void setEmploymentInsurance(int employmentInsurance) {
		this.employmentInsurance = employmentInsurance;
	}
	public int getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(int incomeTax) {
		this.incomeTax = incomeTax;
	}
	public int getShortageDeduction() {
		return shortageDeduction;
	}
	public void setShortageDeduction(int shortageDeduction) {
		this.shortageDeduction = shortageDeduction;
	}
	public int getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}
	public int getTotalDeduction() {
		return totalDeduction;
	}
	public void setTotalDeduction(int totalDeduction) {
		this.totalDeduction = totalDeduction;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public String getPoolFlag() {
		return poolFlag;
	}
	public void setPoolFlag(String poolFlag) {
		this.poolFlag = poolFlag;
	}
	public int getResidentTax() {
		return residentTax;
	}
	public void setResidentTax(int residentTax) {
		this.residentTax = residentTax;
	}
}
