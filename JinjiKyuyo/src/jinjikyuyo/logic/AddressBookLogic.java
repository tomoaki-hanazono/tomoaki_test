package jinjikyuyo.logic;

import java.util.ArrayList;
import java.util.List;

import jinjikyuyo.bean.AddressBookBean;
import jinjikyuyo.dao.AddressBookDAO;

/**
 * 
 * 住所録Logic.
 *
 */
public class AddressBookLogic {

	// 住所録DAO
	private AddressBookDAO dao = new AddressBookDAO();
	
	/**
	 * 住所録登録処理
	 * @param addressBook 住所録
	 * @return 登録結果
	 */
	public int registAddressBook(AddressBookBean addressBook) {
		// 登録結果初期化
		int reslt = 0;
		
		try {
			// 住所録登録
			reslt = dao.insert(addressBook);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
	
	/**
	 * 住所録一覧取得
	 * @return 住所録一覧
	 */
	public List<AddressBookBean> getAddressBookList() {
		// 住所録一覧初期化
		List<AddressBookBean> list = new ArrayList<>();
		
		try {
			// 住所録一覧取得
			list = dao.selectAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 登録確認
	 * @param request 住所録
	 * @return 件数（0:未登録 / 1以上:登録済）
	 */
	public int countAddressBook(AddressBookBean request) {
		// 件数を初期化
		int count = 0;
		
		try {
			// 件数取得
			count = dao.count(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	/**
	 * 住所録取得
	 * @return 住所録一覧
	 */
	public AddressBookBean getAddressBook(int employeeId) {
		AddressBookBean addressBookBean = new AddressBookBean();
		
		try {
			// 住所録一覧取得
			addressBookBean = dao.select(employeeId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return addressBookBean;
	}
}
