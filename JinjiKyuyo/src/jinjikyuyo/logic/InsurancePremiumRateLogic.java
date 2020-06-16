package jinjikyuyo.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jinjikyuyo.bean.InsurancePremiumRateBean;
import jinjikyuyo.dao.InsurancePremiumRateDAO;

public class InsurancePremiumRateLogic {
	
	private InsurancePremiumRateDAO dao = new InsurancePremiumRateDAO();
	private static final String EMPLOYMENT_INSURANCE_ID = "employment_insurance";
	
	public Map<String, InsurancePremiumRateBean> getInsurancePremiumRateAll() {
		Map<String, InsurancePremiumRateBean> reslt = new HashMap<>();
		
		try {
			reslt = dao.selectAll();
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return reslt;
	}
	
	/**
	 * 保険料率取得処理
	 * @param insuranceId
	 * @param targetDate
	 * @return
	 */
	public double getInsurancePremiumRate(String insuranceId, String targetDate) {
		double insurancePremiumRate = 0;
		InsurancePremiumRateBean reslt = new InsurancePremiumRateBean();
		
		try {
			reslt = dao.selectInsurancePremiumRateForInsuranceId(insuranceId,targetDate);
			
			if(EMPLOYMENT_INSURANCE_ID.equals(insuranceId)) {
				insurancePremiumRate = (double)reslt.getInsurancePremiumRate1() / (double)reslt.getInsurancePremiumRate2();;
			} else {
				String i = String.valueOf(reslt.getInsurancePremiumRate1());
				String d = String.valueOf(reslt.getInsurancePremiumRate2());
				String s = i + "." +d;
				insurancePremiumRate = Double.parseDouble(s);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return insurancePremiumRate;
	}
	
	public int registInsurancePremiumRate(List<InsurancePremiumRateBean> requestList) {
		int reslt = 0;
		
		try {
			reslt = dao.insert(requestList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
	
	public int updateInsurancePremiumRate(List<InsurancePremiumRateBean> requestList) {
		int reslt = 0;
		
		try {
			reslt = dao.update(requestList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
}
