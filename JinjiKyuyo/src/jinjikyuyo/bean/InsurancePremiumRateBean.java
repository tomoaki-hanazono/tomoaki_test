package jinjikyuyo.bean;

public class InsurancePremiumRateBean implements java.io.Serializable {
	private String insuranceId;
	private String insuranceName;
	private int insurancePremiumRate1;
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
