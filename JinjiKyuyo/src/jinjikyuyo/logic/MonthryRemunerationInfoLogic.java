package jinjikyuyo.logic;

import java.util.List;

import jinjikyuyo.bean.MonthryRemunerationInfoBean;
import jinjikyuyo.dao.MonthryRemunerationInfoDAO;

public class MonthryRemunerationInfoLogic {

	private MonthryRemunerationInfoDAO dao = new MonthryRemunerationInfoDAO();
	
	public int getMonthryRemuneration(int employeeId, String year) {
		int reslt = 0;
		
		try {
			reslt = dao.selectMonthryRemuneration(employeeId, year);
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return reslt;
	}
	
	public int registMonthryRemunerationInfo(List<MonthryRemunerationInfoBean> requestList) {
		int reslt = 0;
		
		try {
			reslt = dao.insert(requestList);
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return reslt;
	}
}
