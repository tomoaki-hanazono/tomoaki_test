package taijukiroku.logic;

import java.util.*;
import taijukiroku.bean.TaijuInfo;
import taijukiroku.dao.TaijuInfoDAO;

public class TaijukirokuLogic {

	public List<TaijuInfo> getTaijuInfoList(int userNo) {
		List<TaijuInfo> taijuInfoList = new ArrayList<>();
		
		try {
			TaijuInfoDAO dao = new TaijuInfoDAO();
			
			taijuInfoList = dao.selectForUserNo(userNo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return taijuInfoList;
	}
	
	public void registTaijuInfo(TaijuInfo taijuInfo) {
		try {
			TaijuInfoDAO dao = new TaijuInfoDAO();
			
			dao.insert(taijuInfo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
