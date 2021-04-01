import java.util.HashMap;

/**
 * 単一のワークシートに含まれるデータのコンテナ.
 */
public class XLSXSheet {

	private String _sheetName;
	private HashMap<Integer, XLSXRow> _rows = new HashMap<Integer, XLSXRow>();
	private int _totalRows = 0;
	
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
	 * このシートの行の総数を取得.
	 * @return
	 */
	public int getTotalRows() {
		return _totalRows;
	}
	
	/**
	 * このワークシートに行を追加.
	 * @param row
	 */
	public void addRow(XLSXRow row) {
		_rows.put(new Integer(row.getRowNumber()), row);
		if (row.getRowNumber() >= _totalRows) _totalRows = row.getRowNumber() + 1;
	}
	
	/**
	 * このワークシートの指定された行にセルを追加.
	 * 行が存在しない場合は行も追加.
	 * @param rowIndex 行番号
	 * @param cell
	 */
	public void addCell(int rowIndex, XLSXCell cell) {
		Integer key = new Integer(rowIndex);
		if (_rows.containsKey(key)) {
			_rows.get(key).addCell(cell);
		} else {
			XLSXRow row = new XLSXRow(rowIndex);
			addRow(row);
			row.addCell(cell);
		}
	}
	
	public XLSXRow getRowAt(int rowNumber) {
		if (rowNumber < _totalRows) {
			Integer key = new Integer(rowNumber);
			if (_rows.containsKey(key)) return _rows.get(key);
		}
		return new XLSXRow(rowNumber);
	}
}
