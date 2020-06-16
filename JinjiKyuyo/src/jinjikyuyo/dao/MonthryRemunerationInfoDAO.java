package jinjikyuyo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jinjikyuyo.bean.MonthryRemunerationInfoBean;

public class MonthryRemunerationInfoDAO extends CommonDAO {
	/**
	 * 標準月額取得処理
	 * @param employeeId 社員番号
	 * @param year 対象年
	 * @return 標準月額
	 */
	public int selectMonthryRemuneration(int employeeId, String year) {
		int reslt = 0;
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM monthry_remuneration_info WHERE employee_id = ? AND target_year = ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, employeeId);
			st.setString(2, year);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				reslt = rs.getInt("monthry_remuneration");
				break;
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
		return reslt;
	}

	public int insert(List<MonthryRemunerationInfoBean> requestList) throws Exception {
		int reslt = 0;
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "INSERT INTO monthry_remuneration_info VALUE(?,?,?)";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			
			for (MonthryRemunerationInfoBean request : requestList) {
				st.setInt(1, request.getEmployeeId());
				st.setString(2, request.getTargetYear());
				st.setInt(3, request.getMonthryRemuneration());
				// SQL実行
				reslt = st.executeUpdate();
			}
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
		
		return reslt;
	}
}
