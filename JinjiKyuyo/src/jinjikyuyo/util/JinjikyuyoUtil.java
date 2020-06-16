package jinjikyuyo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jinjikyuyo.logic.InsurancePremiumRateLogic;

public class JinjikyuyoUtil {

	private InsurancePremiumRateLogic logic = new InsurancePremiumRateLogic();
	private static final Map<Integer, Integer> MONTHRY_REMUNERATION_MAP;
	
	static {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(63000, 68000);
		map.put(73000, 78000);
		map.put(83000, 88000);
		map.put(93000, 98000);
		map.put(101000, 104000);
		map.put(107000, 110000);
		map.put(114000, 118000);
		map.put(122000, 126000);
		map.put(130000, 134000);
		map.put(138000, 142000);
		map.put(146000, 150000);
		map.put(155000, 160000);
		map.put(165000, 170000);
		map.put(175000, 180000);
		map.put(185000, 190000);
		map.put(195000, 200000);
		map.put(210000, 220000);
		map.put(230000, 240000);
		map.put(250000, 260000);
		map.put(270000, 280000);
		map.put(290000, 300000);
		map.put(310000, 320000);
		map.put(330000, 340000);
		map.put(350000, 360000);
		map.put(370000, 380000);
		map.put(395000, 410000);
		map.put(425000, 440000);
		map.put(455000, 470000);
		map.put(485000, 500000);
		map.put(515000, 530000);
		map.put(545000, 560000);
		map.put(575000, 590000);
		map.put(605000, 620000);
		map.put(635000, 650000);
		map.put(665000, 680000);
		map.put(695000, 710000);
		map.put(730000, 750000);
		map.put(770000, 790000);
		map.put(810000, 830000);
		map.put(855000, 880000);
		map.put(905000, 930000);
		map.put(955000, 980000);
		map.put(1005000, 1030000);
		map.put(1055000, 1090000);
		map.put(1115000, 1150000);
		map.put(1175000, 1210000);
		map.put(1235000, 1270000);
		map.put(1295000, 1330000);
		map.put(1355000, 1390000);
		MONTHRY_REMUNERATION_MAP = Collections.unmodifiableMap(map);
	}
	
	private static final List<Integer> MONTHRY_REMUNERATION_LIST = new ArrayList<>(MONTHRY_REMUNERATION_MAP.keySet());
	private static final String HEALTH_INSURANCE = "health_insurance";
	private static final String CARE_INSURANCE = "care_insurance";
	private static final String EMPLOYEE_PENSION = "employee_pension";
	private static final String EMPLOYMENT_INSURANCE = "employment_insurance";
	/**
	 * 標準月額設定
	 * @param totalPayment
	 * @return
	 */
	public int searchMonthryRemuneration(int totalPayment) {
		int monthryRemuneration = 0;
		int lower = 0;
		int upper = MONTHRY_REMUNERATION_LIST.size();
		if(63000 > totalPayment) {
			monthryRemuneration = 58000;
		} else if(1355000 <= totalPayment) {
			monthryRemuneration = 1390000;
		} else {
			while(lower < upper) {
				int mid = (lower + upper) / 2;
				if(upper - lower == 1) {
					monthryRemuneration = MONTHRY_REMUNERATION_MAP.get(MONTHRY_REMUNERATION_LIST.get(lower));
					break;
				} else if (MONTHRY_REMUNERATION_LIST.get(mid) == totalPayment) {
					monthryRemuneration = MONTHRY_REMUNERATION_MAP.get(MONTHRY_REMUNERATION_LIST.get(mid));
					break;
				} else if (MONTHRY_REMUNERATION_LIST.get(mid) < totalPayment) {
					lower = mid;
				} else {
					upper = mid;
				}
			}
		}
		
		return monthryRemuneration;
	}
	
	/**
	 * 健康保険料計算処理
	 * @param monthryRemuneration
	 * @param targetDate
	 * @param age
	 * @return
	 */
	public int getHealthInsurance(int monthryRemuneration, String targetDate, int age) {
		int healthInsurance = 0;
		// 健康保険料率取得
		double healthInsuranceRate = logic.getInsurancePremiumRate(HEALTH_INSURANCE, targetDate);
		// 介護保険料率取得
		double careInsuranceRate = logic.getInsurancePremiumRate(CARE_INSURANCE, targetDate);
		if (40 <= age) {
			healthInsurance = (int)Math.round(monthryRemuneration * (healthInsuranceRate + careInsuranceRate) / 100 / 2);
		} else {
			healthInsurance = (int)Math.round(monthryRemuneration * healthInsuranceRate / 100 / 2);
		}
		
		return healthInsurance;
	}
	
	/**
	 * 厚生年金保険料計算処理
	 * @param monthryRemuneration
	 * @param targetDate
	 * @return
	 */
	public int getEmployeePension(int monthryRemuneration, String targetDate) {
		int employeePension = 0;
		// 厚生年金保険料率取得処理
		double employeePensionRate = logic.getInsurancePremiumRate(EMPLOYEE_PENSION, targetDate);
		if (monthryRemuneration < 88000) {
			monthryRemuneration = 88000;
		} else if (monthryRemuneration > 620000) {
			monthryRemuneration = 620000; 
		}
		employeePension = (int)Math.round(monthryRemuneration * employeePensionRate / 100 / 2);
		
		return employeePension;
	}
	
	/**
	 * 雇用保険料計算処理
	 * @param monthryRemuneration
	 * @param targetDate
	 * @return
	 */
	public int getEmploymentInsurance(int monthryRemuneration, String targetDate) {
		int employmentInsurance = 0;
		// 雇用保険料率取得処理
		double employmentInsuranceRate = logic.getInsurancePremiumRate(EMPLOYMENT_INSURANCE, targetDate);
		employmentInsurance = (int)Math.round(monthryRemuneration * employmentInsuranceRate);
		
		return employmentInsurance;
	}
}
