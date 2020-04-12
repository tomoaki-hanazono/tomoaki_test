package jinjikyuyo.logic;

import java.util.ArrayList;
import java.util.List;

import jinjikyuyo.bean.EmploymentInfoBean;
import jinjikyuyo.bean.EmploymentInfoHistoryBean;
import jinjikyuyo.dao.EmploymentInfoDAO;
import jinjikyuyo.dao.EmploymentInfoHistroyDAO;

/**
 * 
 * 雇用情報Logic.
 *
 */
public class EmploymentInfoLogic {

	// 雇用情報DAO
	private EmploymentInfoDAO dao = new EmploymentInfoDAO();
	// 雇用情報履歴DAO
	private EmploymentInfoHistroyDAO historyDao = new EmploymentInfoHistroyDAO();
	
	/**
	 * 雇用情報取得
	 * @param employeeId 社員番号
	 * @return 雇用情報
	 */
	public EmploymentInfoBean getEmploymentInfo(int employeeId) {
		EmploymentInfoBean employmentInfo = new EmploymentInfoBean();
		
		try {
			// 雇用情報取得
			employmentInfo = dao.selectForEmployeeId(employeeId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return employmentInfo;
	}
	
	/**
	 * 雇用情報一覧取得
	 * @param employeeId 社員番号
	 * @return 雇用情報リスト
	 */
	public List<EmploymentInfoHistoryBean> getEmploymentInfoList(int employeeId) {
		List<EmploymentInfoHistoryBean> list = new ArrayList<>();
		
		try {
			// 雇用情報一覧取得
			list = historyDao.selectForEmployeeId(employeeId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 雇用情報登録
	 * @param request 雇用情報
	 * @return 登録結果
	 */
	public int registEmploymentInfo(EmploymentInfoBean request) {
		int reslt = 0;
		
		try {
			// 雇用情報登録
			reslt = dao.insert(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
	
	/**
	 * 雇用情報更新
	 * @param request 雇用情報
	 * @return 登録結果
	 */
	public int updateEmploymentInfo(EmploymentInfoBean request) {
		int reslt = 0;
		
		try {
			// 現在の雇用情報を取得
			EmploymentInfoBean before = dao.selectForEmployeeId(request.getEmployeeId());
			
			// 取得した雇用情報を雇用情報履歴に登録
			EmploymentInfoHistoryBean history = new EmploymentInfoHistoryBean();
			history.set(before);
			historyDao.insert(history);
			
			// 雇用情報を更新
			reslt = dao.update(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
}
