package jinjikyuyo.xlsx;

import java.util.HashMap;

/**
 * 共通文字列コンテナ.
 * xlsx xml形式のExcelワークシートの文字列ルックアップ機能.
 */
public class XLSXSharedStrings {

	/** 共通文字列コンテナ */
	private HashMap<Integer,String> _sharedStrings = new HashMap<Integer,String>();
	
	/**
	 * 共有文字列を追加する.
	 * @param index キー（0始まり）
	 * @param sharedString
	 */
	public void addSharedString(int index, String sharedString) {
		_sharedStrings.put(new Integer(index), sharedString);
	}
	
	/**
	 * 共通文字列を取得する.
	 * @param index キー（0始まり）
	 * @return sharedString
	 */
	public String getSharedString(int index) {
		Integer key = new Integer(index);
		if (_sharedStrings.containsKey(key)) {
			return _sharedStrings.get(key);
		}
		return "";
	}
}
