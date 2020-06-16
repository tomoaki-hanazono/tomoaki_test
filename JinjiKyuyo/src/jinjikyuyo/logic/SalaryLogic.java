package jinjikyuyo.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jinjikyuyo.bean.Salary;
import jinjikyuyo.bean.SalaryBean;
import jinjikyuyo.dao.SalaryDAO;

public class SalaryLogic {
	
	private SalaryDAO dao = new SalaryDAO();
	
	public List<SalaryBean> getSalaryInfoList(int employeeId) {
		List<SalaryBean> list = new ArrayList<>();
		
		try {
			list = dao.selectForEmployeeId(employeeId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 給与登録処理
	 * @param request
	 * @return
	 */
	public int registSalaryInfo(SalaryBean request) {
		int reslt = 0;
		
		try {
			reslt = dao.insert(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
	
	public Map<Integer, Map<String, Integer>> getMonthryRemunerat(String targetYear) {
		Map<Integer, Map<String, Integer>> resltMap = new HashMap<>();
		
		try {
			resltMap = dao.selectMonthryRemunerat(targetYear);
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return resltMap;
	}
	
	public String getBeforePoolFlag(int employeeId, String beforeYm) {
		String beforePoolFlag = "0";
		
		try {
			beforePoolFlag = dao.selectToPoolFlag(employeeId, beforeYm);
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		return beforePoolFlag;
	}

	/**
	 * プールさせてる時間外手当てを取得
	 * @param employeeId
	 * @param poolYm
	 * @return
	 */
	public int getPoolOvertimeAllowance(int employeeId, String poolYm) {
		int poolOvertimeAllowance = 0;
		
		try {
			poolOvertimeAllowance = dao.selectPoolOvertimeAllowance(employeeId, poolYm);
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		return poolOvertimeAllowance;
	}
	
	public List<Salary> getSalaryInfoList(String operatingMonth) {
		List<Salary> list = new ArrayList<>();
		
		try {
			list = dao.selectForOperatingMonth(operatingMonth);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
