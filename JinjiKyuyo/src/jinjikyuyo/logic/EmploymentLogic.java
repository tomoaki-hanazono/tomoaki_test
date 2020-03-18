package jinjikyuyo.logic;

import java.util.ArrayList;
import java.util.List;

import jinjikyuyo.bean.EmploymentBean;
import jinjikyuyo.dao.EmploymentDAO;

public class EmploymentLogic {

	private EmploymentDAO dao = new EmploymentDAO();
	
	public List<EmploymentBean> getEmploymentInfoList(int employeeId) {
		List<EmploymentBean> list = new ArrayList<>();
		
		try {
			list = dao.selectForEmployeeId(employeeId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int registEmploymentInfo(EmploymentBean request) {
		int reslt = 0;
		
		try {
			reslt = dao.insert(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
	
	public int updateEmploymentInfo(List<EmploymentBean> requestList) {
		int reslt = 0;
		
		try {
			reslt = dao.update(requestList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
}
