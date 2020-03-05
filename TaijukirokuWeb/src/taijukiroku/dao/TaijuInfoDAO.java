package taijukiroku.dao;

import java.sql.*;
import java.util.*;
import taijukiroku.bean.TaijuInfo;

public class TaijuInfoDAO extends CommonDAO {

	public List<TaijuInfo> selectForUserNo(int userNo) throws Exception {
		List<TaijuInfo> list = new ArrayList<>();
		
		// DB接続
		Connection con = getConnection();
		
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
		st.close();
       con.close();
       
		return list;
	}
}
