package jinjikyuyo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jinjikyuyo.bean.EmploymentBean;

public class EmploymentDAO extends CommonDAO {
	
	public List<EmploymentBean> selectForEmployeeId(int employeeId) {
		List<EmploymentBean> list = new ArrayList<>();
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM employment WHERE employee_id = ? ORDER BY employment_id DESC";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, employeeId);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				EmploymentBean employment = new EmploymentBean();
				employment.setEmploymentId(rs.getInt("employment_id"));
				employment.setEmployeeId(rs.getInt("employee_id"));
				employment.setEmployeeName(rs.getString("employee_name"));
				employment.setEmploymentPeriodStart(rs.getString("employment_period_start"));
				employment.setEmploymentPeriodEnd(rs.getString("employment_period_end"));
				employment.setBasicSalary(rs.getInt("basic_salary"));
				employment.setDutiesAllowance(rs.getInt("duties_allowance"));
				employment.setCommutingAllowance(rs.getInt("commuting_allowance"));
				employment.setOvertimeAllowance(rs.getInt("overtime_allowance"));
				employment.setOtherAllowance(rs.getInt("other_allowance"));
				employment.setExcessMoney(rs.getInt("excess_money"));
				employment.setEductionMoney(rs.getInt("eduction_money"));
				employment.setLowerLimit(rs.getInt("lower_limit"));
				employment.setUpperLimit(rs.getInt("upper_limit"));
				employment.setJobDescription(rs.getString("job_description"));
				employment.setRemarks(rs.getString("remarks"));
				list.add(employment);
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

	public int insert(EmploymentBean request) throws Exception {
		Connection con = null;
		int reslt = 0;
		
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "insert into employment values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, request.getEmployeeId());
			st.setString(2, request.getEmployeeName());
			st.setString(3, request.getEmploymentPeriodStart());
			st.setString(4, request.getEmploymentPeriodEnd());
			st.setInt(5, request.getBasicSalary());
			st.setInt(6, request.getDutiesAllowance());
			st.setInt(7, request.getCommutingAllowance());
			st.setInt(8, request.getOvertimeAllowance());
			st.setInt(9, request.getOtherAllowance());
			st.setInt(10, request.getExcessMoney());
			st.setInt(11, request.getEductionMoney());
			st.setInt(12, request.getLowerLimit());
			st.setInt(13, request.getUpperLimit());
			st.setString(14, request.getJobDescription());
			st.setString(15, request.getRemarks());
			
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
	
	public int update(List<EmploymentBean> requestList) throws Exception {
		Connection con = null;
		int reslt = 0;
		
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "UPDATE employment SET basic_salary = ?," + 
					" duties_allowance = ?," + 
					" commuting_allowance = ?," + 
					" overtime_allowance = ?," + 
					" other_allowance = ?," + 
					" excess_money = ?," + 
					" eduction_money = ?," + 
					" lower_limit = ?," + 
					" upper_limit = ?" + 
					" WHERE employment_id = ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			
			for (EmploymentBean request : requestList) {
				st.setInt(1, request.getBasicSalary());
				st.setInt(2, request.getDutiesAllowance());
				st.setInt(3, request.getCommutingAllowance());
				st.setInt(4, request.getOvertimeAllowance());
				st.setInt(5, request.getOtherAllowance());
				st.setInt(6, request.getExcessMoney());
				st.setInt(7, request.getEductionMoney());
				st.setInt(8, request.getLowerLimit());
				st.setInt(9, request.getUpperLimit());
				st.setInt(10, request.getEmploymentId());
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
