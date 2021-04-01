package jinjikyuyo.xlsx;

import java.util.HashMap;

/**
 * 単一のワークシートに含まれるデータのコンテナ.
 */
public class XLSXSheet {

	/** シート名 */
	private String _sheetName;
	/** シート内のセルのデータ */
	private HashMap<String, XLSXCell> _cells = new HashMap<String, XLSXCell>();
	/** 共通文字列コンテナ */
	private XLSXSharedStrings _sharedStringLookup;
	
	/**
	 * シートの設定.
	 * @param sheetName シート名
	 */
	public XLSXSheet(String sheetName) {
		_sheetName = sheetName;
	}
	
	/**
	 * シート名の取得.
	 * @return シート名
	 */
	public String getSheetName() {
		return _sheetName;
	}
	
	/**
	 * シートにセルのデータを追加
	 * @param cellKey セルのKey
	 * @param cellValue セルの値
	 */
	public void addCell(String cellKey, XLSXCell cell) {
		_cells.put(cellKey, cell);
	}
	
	/**
	 * 共通文字列コンテナを設定.
	 * @param sharedStrings 共通文字列コンテナ
	 */
	public void setSharedStringLookup(XLSXSharedStrings sharedStrings) {
		_sharedStringLookup = sharedStrings;
	}
	
	/**
	 * セルのデータを取得
	 * @param cellKey セルのkey
	 * @return セルのデータ
	 */
	public XLSXCell getCellAt(String cellKey) {
		return _cells.get(cellKey);
	}
	
	/**
	 * セルの値を取得(全件).
	 * @return セルの値
	 */
	public HashMap<String, String> getCellAll() {
		HashMap<String, String> cells = new HashMap<String, String>();
		
		// セルのkeyの件数分、処理を繰り返す
		for (String key : _cells.keySet()) {
			// セルのデータを取得
			XLSXCell cell = _cells.get(key);
			// セルの値を取得
			String value = cell.getContents();
			// 共通文字列フラグがtrueの場合
			if(cell.isSharedString()) {
				// 共通文字列コンテナから値を取得
				int index = Integer.parseInt(value);
				value = _sharedStringLookup.getSharedString(index);
			}
			cells.put(key, value);
		}
		
		return cells;
	}
}
