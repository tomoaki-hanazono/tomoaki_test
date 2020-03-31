package jinjikyuyo.bean;

public class MonthryRemunerationInfoBean implements java.io.Serializable {
	private int employeeId;
	private String targetYear;
	private int monthryRemuneration;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getTargetYear() {
		return targetYear;
	}
	public void setTargetYear(String targetYear) {
		this.targetYear = targetYear;
	}
	public int getMonthryRemuneration() {
		return monthryRemuneration;
	}
	public void setMonthryRemuneration(int monthryRemuneration) {
		this.monthryRemuneration = monthryRemuneration;
	}
}
