package taijukiroku.dao;

import java.sql.*;
import java.util.*;
import taijukiroku.bean.TaijuInfo;

public class TaijuInfoDAO extends CommonDAO {

	public List<TaijuInfo> selectForUserNo(int userNo) {
		List<TaijuInfo> list = new ArrayList<>();
		Connection con = null;
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM taijuinfo WHERE ";
			sql += "user_no = " + String.valueOf(userNo);
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				TaijuInfo taijuInfo = new TaijuInfo();
				taijuInfo.setUserNo(rs.getInt("user_no"));
				taijuInfo.setDate(rs.getString("date"));
				taijuInfo.setBodyWeight(rs.getDouble("body_weight"));
				taijuInfo.setBmi(rs.getDouble("bmi"));
				taijuInfo.setResult(rs.getString("result"));
				
				list.add(taijuInfo);
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
       
		return list;
	}
	
	public void insert(TaijuInfo taijuInfo) throws Exception {
		
		Connection con = null;
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "insert into taijuinfo values(?,?,?,?,?,now(),now())";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, taijuInfo.getUserNo());
			st.setString(2, taijuInfo.getDate());
			st.setDouble(3, taijuInfo.getBodyWeight());
			st.setDouble(4, taijuInfo.getBmi());
			st.setString(5, taijuInfo.getResult());
			
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
