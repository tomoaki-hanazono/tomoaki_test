package jinjikyuyo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jinjikyuyo.bean.InsurancePremiumRateBean;

public class InsurancePremiumRateDAO extends CommonDAO {
	
	public Map<String, InsurancePremiumRateBean> selectAll() {
		Map<String, InsurancePremiumRateBean> reslt = new HashMap<>();
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM insurance_premium_rate";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				InsurancePremiumRateBean insurancePremiumRate = new InsurancePremiumRateBean();
				insurancePremiumRate.setInsuranceId(rs.getString("insurance_id"));
				insurancePremiumRate.setInsuranceName(rs.getString("insurance_name"));
				insurancePremiumRate.setInsurancePremiumRate1(rs.getInt("insurance_premium_rate_1"));
				insurancePremiumRate.setInsurancePremiumRate2(rs.getInt("insurance_premium_rate_2"));
				reslt.put(rs.getString("insurance_id"), insurancePremiumRate);
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
	
	public InsurancePremiumRateBean selectInsurancePremiumRateForInsuranceId(String insuranceId) {
		InsurancePremiumRateBean reslt = new InsurancePremiumRateBean();
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM insurance_premium_rate WHERE insurance_id = ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, insuranceId);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				reslt.setInsuranceId(rs.getString("insurance_id"));
				reslt.setInsuranceName(rs.getString("insurance_name"));
				reslt.setInsurancePremiumRate1(rs.getInt("insurance_premium_rate_1"));
				reslt.setInsurancePremiumRate2(rs.getInt("insurance_premium_rate_2"));
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
	
	public int update(List<InsurancePremiumRateBean> requestList) throws Exception {
		Connection con = null;
		int reslt = 0;
		
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "UPDATE insurance_premium_rate SET insurance_premium_rate_1 = ?," + 
					" insurance_premium_rate_2 = ?" + 
					" WHERE insurance_id = ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			
			for (InsurancePremiumRateBean request : requestList) {
				st.setInt(1, request.getInsurancePremiumRate1());
				st.setInt(2, request.getInsurancePremiumRate2());
				st.setString(3, request.getInsuranceId());
				st.addBatch();
			}
			
			// SQL実行
			reslt = st.executeUpdate();
			
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
