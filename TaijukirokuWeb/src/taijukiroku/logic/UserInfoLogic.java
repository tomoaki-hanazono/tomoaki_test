package taijukiroku.logic;

import java.sql.SQLException;
import java.util.*;
import taijukiroku.bean.UserInfo;
import taijukiroku.dao.UserInfoDAO;

public class UserInfoLogic {
	
	/**
	 * ユーザ情報全件取得処理.
	 * 
	 * @return ユーザ情報リスト
	 * @throws Exception
	 */
	public List<UserInfo> getUserInfoList() {
		
		List<UserInfo> userInfoList = new ArrayList<>();
		
		try {
			UserInfoDAO dao = new UserInfoDAO();
			
			userInfoList = dao.selectAll();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userInfoList;
	}
	
	/**
	 * 
	 * ユーザ情報1件取得処理.
	 * 
	 * @param userNo
	 * @return ユーザ情報.
	 * @throws Exception
	 */
	public UserInfo getUserInfo(int userNo) {
		UserInfo userInfo = new UserInfo();
		
		try {
			UserInfoDAO dao = new UserInfoDAO();
			
			userInfo = dao.select(userNo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	public void registUser(UserInfo userInfo) {		
		try {
			UserInfoDAO dao = new UserInfoDAO();
			
			dao.insert(userInfo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
