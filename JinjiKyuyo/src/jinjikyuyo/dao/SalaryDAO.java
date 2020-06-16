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
import jinjikyuyo.bean.Salary;

public class SalaryDAO extends CommonDAO {
	
	/**
	 * 社員IDに紐づく給与一覧を所得
	 * @param employeeId
	 * @return
	 */
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
				int overtimeAllowance = rs.getInt("overtime_allowance");
				if("1".equals(rs.getString("pool_flag"))) {
					overtimeAllowance = 0;
				}
				salary.setOvertimeAllowance(overtimeAllowance);
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
				salary.setPoolFlag(rs.getString("pool_flag"));
				salary.setResidentTax(rs.getInt("resident_tax"));
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
	
	/**
	 * 対象年の4〜5月の総支給額を取得
	 * @param targetYear
	 * @return
	 */
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
			String sql = "insert into salary values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
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
			st.setString(23, request.getPoolFlag());
			st.setInt(24, request.getResidentTax());
			
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
	
	public String selectToPoolFlag(int employeeId, String ym) {
		String poolFlag = "0";
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT pool_flag FROM salary WHERE employee_id = ? AND operating_month = ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, employeeId);
			st.setString(2, ym);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				poolFlag = rs.getString("pool_flag");
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
		
		return poolFlag;
	}
	
	/**
	 * 対象月の時間外手当てを取得
	 * @param employeeId
	 * @param ym
	 * @return
	 */
	public int selectPoolOvertimeAllowance(int employeeId, String ym) {
		int overtimeAllowance = 0;
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT overtime_allowance FROM salary WHERE employee_id = ? AND operating_month = ? AND pool_flag = 1";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, employeeId);
			st.setString(2, ym);			
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				overtimeAllowance = rs.getInt("overtime_allowance");
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
		
		return overtimeAllowance;
	}
	
	/**
	 * 対象月に紐づく給与一覧を所得
	 * @param employeeId
	 * @return
	 */
	public List<Salary> selectForOperatingMonth(String operatingMonth) {
		List<Salary> list = new ArrayList<>();
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT e.employee_name, s.* FROM salary s "
					+ "INNER JOIN employment_info e on s.employee_id = e.employee_id "
					+ "WHERE operating_month = ? ORDER BY employee_id ASC";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, operatingMonth);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				Salary salary = new Salary();
				salary.setEmployeeName(rs.getString("employee_name"));
				salary.setEmployeeId(rs.getInt("employee_id"));
				salary.setOperatingMonth(rs.getString("operating_month"));
				salary.setOperatingTime(rs.getDouble("operating_time"));
				salary.setUpperTime(rs.getDouble("upper_time"));
				salary.setLowarTime(rs.getDouble("lowar_time"));
				salary.setBasicSalary(rs.getInt("basic_salary"));
				salary.setDutiesAllowance(rs.getInt("duties_allowance"));
				salary.setCommutingAllowance(rs.getInt("commuting_allowance"));
				int overtimeAllowance = rs.getInt("overtime_allowance");
				if("1".equals(rs.getString("pool_flag"))) {
					overtimeAllowance = 0;
				}
				salary.setOvertimeAllowance(overtimeAllowance);
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
				salary.setPoolFlag(rs.getString("pool_flag"));
				salary.setResidentTax(rs.getInt("resident_tax"));
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
}
