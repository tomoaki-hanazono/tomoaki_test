package jinjikyuyo.logic;

import java.util.ArrayList;
import java.util.List;

import jinjikyuyo.bean.EmploymentBean;
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
}
