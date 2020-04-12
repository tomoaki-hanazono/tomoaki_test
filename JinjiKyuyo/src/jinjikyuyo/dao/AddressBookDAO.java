package jinjikyuyo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jinjikyuyo.bean.AddressBookBean;
import jinjikyuyo.dao.CommonDAO;

/**
 * 
 * 住所録DAO.
 *
 */
public class AddressBookDAO extends CommonDAO {

	/**
	 * 住所録登録処理
	 * @param request 住所録
	 * @return 登録結果
	 * @throws Exception
	 */
	public int insert(AddressBookBean request) throws Exception {
		
		// コネクションを初期化
		Connection con = null;
		// 登録結果を初期化
		int reslt = 0;
		
		try {
			// DB接続
			con = getConnection();
			con.setAutoCommit(false);
			
			// SQL文作成
			String sql = "insert into address_book values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, request.getFullName());
			st.setString(2, request.getFamilyName());
			st.setString(3, request.getFirstName());
			st.setString(4, request.getNameKana());
			st.setString(5, request.getBirthday());
			st.setString(6, request.getZipCode());
			st.setString(7, request.getAddress1());
			st.setString(8, request.getAddress2());
			st.setString(9, request.getPhoneNumber());
			st.setString(10, request.getMobileNumber());
			st.setString(11, request.getMailAddress());
			st.setString(12, request.getCompany());
			st.setString(13, request.getDepartment());
			st.setString(14, request.getPosition());
			st.setString(15, request.getKeishou());
			st.setString(16, request.getRemarks());
			st.setString(17, request.getDependents());
			
			// SQL実行
			reslt = st.executeUpdate();

			// 接続解除
			st.close();
			// コミット
			con.commit();
			
		} catch (SQLException e) {
			// ロールバック
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
	
	/**
	 * 住所録リスト取得（全件）
	 * @return 住所録リスト
	 */
	public List<AddressBookBean> selectAll() {
		// 住所録リストを初期化
		List<AddressBookBean> list = new ArrayList<>();
		// コネクションを初期化
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT * FROM address_book";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			
			// SQL実行
			ResultSet rs = st.executeQuery();

	        // データをセット
			while (rs.next()) {
				AddressBookBean addressbook = new AddressBookBean();
				addressbook.setEmployeeId(rs.getInt("employee_id"));
				addressbook.setFullName(rs.getString("full_name"));
				addressbook.setFamilyName(rs.getString("family_name"));
				addressbook.setFirstName(rs.getString("first_name"));
				addressbook.setNameKana(rs.getString("name_kana"));
				addressbook.setBirthday(rs.getString("birthday"));
				addressbook.setZipCode(rs.getString("zip_code"));
				addressbook.setAddress1(rs.getString("address1"));
				addressbook.setAddress2(rs.getString("address2"));
				addressbook.setPhoneNumber(rs.getString("phone_number"));
				addressbook.setMobileNumber(rs.getString("mobile_number"));
				addressbook.setMailAddress(rs.getString("mail_address"));
				addressbook.setCompany(rs.getString("company"));
				addressbook.setDepartment(rs.getString("department"));
				addressbook.setPosition(rs.getString("position"));
				addressbook.setKeishou(rs.getString("keishou"));
				addressbook.setRemarks(rs.getString("remarks"));
				addressbook.setDependents(rs.getString("dependents"));
				list.add(addressbook);
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
	 * 登録件数確認 
	 * @param request 住所録
	 * @return 登録件数
	 */
	public int count(AddressBookBean request) {
		// 登録件数を初期化
		int count = 0;
		// コネクションを初期化
		Connection con = null;
		
		try {
			// DB接続
			con = getConnection();
			
			// SQL文作成
			String sql = "SELECT count(*) FROM address_book WHERE ";
			sql += "full_name = ?";
			sql += "AND birthday = ?";
			sql += "AND zip_code = ?";
			sql += "AND address1 = ?";
			sql += "AND address2 = ?";
			
			// SQL作成
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, request.getFullName());
			st.setString(2, request.getBirthday());
			st.setString(3, request.getZipCode());
			st.setString(4, request.getAddress1());
			st.setString(5, request.getAddress2());
			
			// SQL実行
			ResultSet rs = st.executeQuery();

			// データをセット
			while (rs.next()) {
				count = rs.getInt(1);
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
		
		return count;
	}
}
