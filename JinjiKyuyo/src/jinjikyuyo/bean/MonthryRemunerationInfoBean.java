package jinjikyuyo.bean;

/**
 * 
 * 標準月額情報Bean.
 *
 */
public class MonthryRemunerationInfoBean implements java.io.Serializable {
	// 社員番号
	private int employeeId;
	// 対象年度
	private String targetYear;
	// 標準月額
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
