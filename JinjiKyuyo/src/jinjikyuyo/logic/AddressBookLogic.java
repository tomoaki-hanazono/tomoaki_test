package jinjikyuyo.logic;

import java.util.ArrayList;
import java.util.List;

import jinjikyuyo.bean.AddressBookBean;
import jinjikyuyo.dao.AddressBookDAO;

public class AddressBookLogic {

	private AddressBookDAO dao = new AddressBookDAO();
	
	public int registAddressBook(AddressBookBean addressBook) {
		int reslt = 0;
		
		try {
			reslt = dao.insert(addressBook);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reslt;
	}
	
	public List<AddressBookBean> getAddressBookList() {
		List<AddressBookBean> list = new ArrayList<>();
		
		try {
			list = dao.selectAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
