import java.util.HashMap;

/**
 * 行のデータのコンテナ.
 */
public class XLSXRow {

	private int _rowNumber;
	private HashMap<Integer, XLSXCell> _cells = new HashMap<Integer, XLSXCell>();
	private int _totalColumns = 0;
	
	public XLSXRow(int rowNumbet) {
		_rowNumber = rowNumbet;
	}
	
	/**
	 * この行の行番号（0始まり）を取得.
	 * @return 行番号
	 */
	public int getRowNumber() {
		return _rowNumber;
	}
	
	/**
	 * この行の列の総数を取得.
	 * @return 列の番号
	 */
	public int getTotalColumns() {
		return _totalColumns;
	}
	
	/**
	 * 行の特定の列にセルを挿入.
	 * @param cell
	 */
	public void addCell(XLSXCell cell) {
		_cells.put(new Integer(cell.getColumnNuber()), cell);
		if (cell.getColumnNuber() >= _totalColumns) _totalColumns = cell.getColumnNuber() + 1;
	}
	
	/**
	 * 特定のセルを取得.
	 * @param columnNumber 列番号（0始まり）
	 * @return セル
	 */
	public XLSXCell getCellAt(int columnNumber) {
		if (columnNumber < _totalColumns) {
			Integer key = new Integer(columnNumber);
			if (_cells.containsKey(key)) return _cells.get(key);
		}
		return new XLSXCell(null, columnNumber, "", false);
	}
}
