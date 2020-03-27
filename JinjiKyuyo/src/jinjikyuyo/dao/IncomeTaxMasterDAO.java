package jinjikyuyo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeTaxMasterDAO extends CommonDAO  {
	
	public int selectIncomeTax(int value,String support) {
		int reslt = 0;
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT ";
			switch(support) {
			case "0":
				sql += "kou_0";
				break;
			case "1":
				sql += "kou_1";
				break;
			case "2":
				sql += "kou_2";
				break;
			case "3":
				sql += "kou_3";
				break;
			case "4":
				sql += "kou_4";
				break;
			case "5":
				sql += "kou_5";
				break;
			case "6":
				sql += "kou_6";
				break;
			case "7":
				sql += "kou_7";
				break;
			}
			
			sql += " FROM income_tax_master WHERE lower_limit <= ? AND upper_limit > ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, value);
			st.setInt(2, value);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				reslt = rs.getInt(1);;
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

}
