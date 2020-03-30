package jinjikyuyo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jinjikyuyo.bean.EmploymentInfoBean;

public class EmploymentInfoDAO extends CommonDAO {
	
	public EmploymentInfoBean selectForEmployeeId(int employeeId) {
		EmploymentInfoBean employmentInfo = null;
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM employment_info WHERE employee_id = ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, employeeId);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				employmentInfo = new EmploymentInfoBean();
				employmentInfo.setEmployeeId(rs.getInt("employee_id"));
				employmentInfo.setEmployeeName(rs.getString("employee_name"));
				employmentInfo.setEmploymentPeriodStart(rs.getString("employment_period_start"));
				employmentInfo.setEmploymentPeriodEnd(rs.getString("employment_period_end"));
				employmentInfo.setBasicSalary(rs.getInt("basic_salary"));
				employmentInfo.setDutiesAllowance(rs.getInt("duties_allowance"));
				employmentInfo.setCommutingAllowance(rs.getInt("commuting_allowance"));
				employmentInfo.setOvertimeAllowance(rs.getInt("overtime_allowance"));
				employmentInfo.setOtherAllowance(rs.getInt("other_allowance"));
				employmentInfo.setExcessMoney(rs.getInt("excess_money"));
				employmentInfo.setEductionMoney(rs.getInt("eduction_money"));
				employmentInfo.setLowerLimit(rs.getInt("lower_limit"));
				employmentInfo.setUpperLimit(rs.getInt("upper_limit"));
				employmentInfo.setJobDescription(rs.getString("job_description"));
				employmentInfo.setRemarks(rs.getString("remarks"));
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
		
		return employmentInfo;
	}

	public int insert(EmploymentInfoBean request) throws Exception {
		Connection con = null;
		int reslt = 0;
		
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "insert into employment_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
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
	
	public int update(EmploymentInfoBean request) throws Exception {
		Connection con = null;
		int reslt = 0;
		
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "UPDATE employment_info SET basic_salary = ?," + 
					" duties_allowance = ?," + 
					" commuting_allowance = ?," + 
					" overtime_allowance = ?," + 
					" other_allowance = ?," + 
					" excess_money = ?," + 
					" eduction_money = ?," + 
					" lower_limit = ?," + 
					" upper_limit = ?" + 
					" WHERE employee_id = ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, request.getBasicSalary());
			st.setInt(2, request.getDutiesAllowance());
			st.setInt(3, request.getCommutingAllowance());
			st.setInt(4, request.getOvertimeAllowance());
			st.setInt(5, request.getOtherAllowance());
			st.setInt(6, request.getExcessMoney());
			st.setInt(7, request.getEductionMoney());
			st.setInt(8, request.getLowerLimit());
			st.setInt(9, request.getUpperLimit());
			st.setInt(10, request.getEmployeeId());
			
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
