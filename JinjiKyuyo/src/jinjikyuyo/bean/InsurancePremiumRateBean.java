package jinjikyuyo.bean;

/**
 * 
 * 保険料率Bean.
 *
 */
public class InsurancePremiumRateBean implements java.io.Serializable {
	// 保険ID
	private String insuranceId;
	// 適用開始月
	private String startDate;
	// 保険名
	private String insuranceName;
	// 保険料率
	private int insurancePremiumRate1;
	// 保険料率
	private int insurancePremiumRate2;
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public int getInsurancePremiumRate1() {
		return insurancePremiumRate1;
	}
	public void setInsurancePremiumRate1(int insurancePremiumRate1) {
		this.insurancePremiumRate1 = insurancePremiumRate1;
	}
	public int getInsurancePremiumRate2() {
		return insurancePremiumRate2;
	}
	public void setInsurancePremiumRate2(int insurancePremiumRate2) {
		this.insurancePremiumRate2 = insurancePremiumRate2;
	}

}
