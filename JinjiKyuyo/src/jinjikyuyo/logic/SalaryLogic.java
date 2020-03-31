package jinjikyuyo.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
