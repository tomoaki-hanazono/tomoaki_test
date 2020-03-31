package jinjikyuyo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jinjikyuyo.bean.SalaryBean;

public class SalaryDAO extends CommonDAO {
	
	public List<SalaryBean> selectForEmployeeId(int employeeId) {
		List<SalaryBean> list = new ArrayList<>();
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM salary WHERE employee_id = ? ORDER BY operating_month DESC";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, employeeId);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				SalaryBean salary = new SalaryBean();
				salary.setEmployeeId(rs.getInt("employee_id"));
				salary.setOperatingMonth(rs.getString("operating_month"));
				salary.setOperatingTime(rs.getDouble("operating_time"));
				salary.setUpperTime(rs.getDouble("upper_time"));
				salary.setLowarTime(rs.getDouble("lowar_time"));
				salary.setBasicSalary(rs.getInt("basic_salary"));
				salary.setDutiesAllowance(rs.getInt("duties_allowance"));
				salary.setCommutingAllowance(rs.getInt("commuting_allowance"));
				salary.setOvertimeAllowance(rs.getInt("overtime_allowance"));
				salary.setOtherAllowance(rs.getInt("other_allowance"));
				salary.setExcessMoney(rs.getInt("excess_money"));
				salary.setEductionMoney(rs.getInt("eduction_money"));
				salary.setLowerLimit(rs.getInt("lower_limit"));
				salary.setUpperLimit(rs.getInt("upper_limit"));
				salary.setHealthInsurance(rs.getInt("health_insurance"));
				salary.setEmployeePension(rs.getInt("employee_pension"));
				salary.setEmploymentInsurance(rs.getInt("employment_insurance"));
				salary.setIncomeTax(rs.getInt("income_tax"));
				salary.setShortageDeduction(rs.getInt("shortage_deduction"));
				salary.setTotalPayment(rs.getInt("total_payment"));
				salary.setTotalDeduction(rs.getInt("total_deduction"));
				salary.setPayment(rs.getInt("payment"));
				list.add(salary);
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
	
	public Map<Integer, Map<String, Integer>> selectMonthryRemunerat(String targetYear) {
		Map<Integer, Map<String, Integer>> resltMap = new HashMap<>();
		Map<String, Integer>reslt;
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM salary WHERE operating_month BETWEEN ? AND ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, targetYear + "04");
			st.setString(2, targetYear + "06");
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				if(!resltMap.containsKey(rs.getInt("employee_id"))) {
					reslt = new TreeMap<>();
				} else {
					reslt = resltMap.get(rs.getInt("employee_id"));
				}
				if(!reslt.containsKey(rs.getString("operating_month"))) {
					reslt.put(rs.getString("operating_month"), rs.getInt("total_payment"));
					resltMap.put(rs.getInt("employee_id"), reslt);
				}
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
		
		
		return resltMap;
	}
	
	public int insert(SalaryBean request) throws Exception {
		Connection con = null;
		int reslt = 0;
		
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "insert into salary values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, request.getEmployeeId());
			st.setString(2, request.getOperatingMonth());
			st.setDouble(3, request.getOperatingTime());
			st.setDouble(4, request.getUpperTime());
			st.setDouble(5, request.getLowarTime());
			st.setInt(6, request.getBasicSalary());
			st.setInt(7, request.getDutiesAllowance());
			st.setInt(8, request.getCommutingAllowance());
			st.setInt(9, request.getOvertimeAllowance());
			st.setInt(10, request.getOtherAllowance());
			st.setInt(11, request.getExcessMoney());
			st.setInt(12, request.getEductionMoney());
			st.setInt(13, request.getLowerLimit());
			st.setInt(14, request.getUpperLimit());
			st.setInt(15, request.getHealthInsurance());
			st.setInt(16, request.getEmployeePension());
			st.setInt(17, request.getEmploymentInsurance());
			st.setInt(18, request.getIncomeTax());
			st.setInt(19, request.getShortageDeduction());
			st.setInt(20, request.getTotalPayment());
			st.setInt(21, request.getTotalDeduction());
			st.setInt(22, request.getPayment());
			
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
