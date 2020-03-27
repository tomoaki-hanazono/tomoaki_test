package jinjikyuyo.logic;

import jinjikyuyo.dao.IncomeTaxMasterDAO;

public class IncomeTaxMasterLogic {
	
	private IncomeTaxMasterDAO dao = new IncomeTaxMasterDAO();
	
	public int getIncomeTax(int otherPayment, String support) {
		int incomeTax = 0;
		
		try {
			incomeTax = dao.selectIncomeTax(otherPayment, support);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return incomeTax;
	}

}
