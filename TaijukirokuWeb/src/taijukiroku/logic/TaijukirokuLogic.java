package taijukiroku.logic;

import java.util.*;
import taijukiroku.bean.TaijuInfo;
import taijukiroku.dao.TaijuInfoDAO;

public class TaijukirokuLogic {
	
	private TaijuInfoDAO dao = new TaijuInfoDAO();

	public List<TaijuInfo> getTaijuInfoList(int userNo) {
		List<TaijuInfo> taijuInfoList = new ArrayList<>();
		
		try {

			taijuInfoList = dao.selectForUserNo(userNo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return taijuInfoList;
	}
	
	public int countTaijuInfo(int userNo, String date) {
		int count = 0;
		
		try {
			count = dao.countTaijuInfo(userNo, date);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public void registTaijuInfo(TaijuInfo taijuInfo) {
		try {
			
			dao.insert(taijuInfo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
