package taijukiroku.dao;

import java.sql.*;
import java.util.*;
import taijukiroku.bean.UserInfo;

/**
 * 
 * ユーザ情報DAO.
 *
 */
public class UserInfoDAO extends CommonDAO {
	
	/**
	 * ユーザ情報全件取得処理.
	 * 
	 * @return ユーザ情報リスト
	 * @throws Exception
	 */
	public List<UserInfo> selectAll() throws Exception {
		List<UserInfo> list = new ArrayList<>();
		
		// DB接続
		Connection con = getConnection();
		
		// SQL作成
		PreparedStatement st = con.prepareStatement("SELECT * FROM userinfo");
		
		// SQL実行
		ResultSet rs = st.executeQuery();

        // データをセット
		while (rs.next()) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserNo(rs.getInt("user_no"));
			userInfo.setUserName(rs.getString("user_name"));
			userInfo.setBirthday(rs.getString("birthday"));
			userInfo.setPhoneNum(rs.getString("phone_num"));
			userInfo.setHeight(rs.getDouble("height"));
        	
			list.add(userInfo);
		}
        
		// 接続解除
		st.close();
       con.close();
		
		return list;
	}
	
	/**
	 * 
	 * ユーザ情報1件取得処理.
	 * 
	 * @param userNo
	 * @return ユーザ情報.
	 * @throws Exception
	 */
	public UserInfo select(int userNo) {
		UserInfo userInfo = new UserInfo();
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM userinfo WHERE ";
			sql += "user_no = " + String.valueOf(userNo);
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			
			// SQL実行
			ResultSet rs = st.executeQuery();
			
			// データをセット
			if (rs.next()) {
				userInfo.setUserNo(rs.getInt("user_no"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setBirthday(rs.getString("birthday"));
				userInfo.setPhoneNum(rs.getString("phone_num"));
				userInfo.setHeight(rs.getDouble("height"));
			} else {
				userInfo = null;
			}
			
			// 接続解除
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null){
				try{
					con.close();
	        	} catch (SQLException e){
	        		e.printStackTrace();
	        	}
			}
		}

		return userInfo;
	}
	
	public void insert(UserInfo userInfo) throws Exception {
		Connection con = null;
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "insert into userinfo values(null,?,?,?,?)";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userInfo.getUserName());
			st.setString(2, userInfo.getBirthday());
			st.setString(3, userInfo.getPhoneNum());
			st.setDouble(4, userInfo.getHeight());
			
			// SQL実行
			st.executeUpdate();

			// 接続解除
			st.close();
			con.commit();
			
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		} finally {
			if (con != null){
				try{
					con.close();
	        	} catch (SQLException e){
	        		e.printStackTrace();
	        	}
			}
		}
		
       con.close();
	}
}
