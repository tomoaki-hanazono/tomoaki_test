package jinjikyuyo.logic;

import java.util.ArrayList;
import java.util.List;

import jinjikyuyo.bean.EmploymentInfoBean;
import jinjikyuyo.bean.EmploymentInfoHistoryBean;
import jinjikyuyo.dao.EmploymentInfoDAO;
import jinjikyuyo.dao.EmploymentInfoHistroyDAO;

public class EmploymentInfoLogic {

	private EmploymentInfoDAO dao = new EmploymentInfoDAO();
	private EmploymentInfoHistroyDAO historyDao = new EmploymentInfoHistroyDAO();
	
	public EmploymentInfoBean getEmploymentInfo(int employeeId) {
		EmploymentInfoBean employmentInfo = new EmploymentInfoBean();
		
		try {
			employmentInfo = dao.selectForEmployeeId(employeeId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return employmentInfo;
	}
	
	public List<EmploymentInfoHistoryBean> getEmploymentInfoList(int employeeId) {
		List<EmploymentInfoHistoryBean> list = new ArrayList<>();
		
		try {
			list = historyDao.selectForEmployeeId(employeeId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int registEmploymentInfo(EmploymentInfoBean request) {
		int reslt = 0;
		
		try {
			reslt = dao.insert(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
	
	public int updateEmploymentInfo(EmploymentInfoBean request) {
		int reslt = 0;
		
		try {
			EmploymentInfoBean before = dao.selectForEmployeeId(request.getEmployeeId());
			
			EmploymentInfoHistoryBean history = new EmploymentInfoHistoryBean();
			history.set(before);
			historyDao.insert(history);
			
			reslt = dao.update(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
}
