
/**
 * スプレッドシート内の単一セルのデータのコンテナ.
 */
public class XLSXCell {

	private XLSXSharedStrings _sharedStringLookup;
	private int _columnNumber;
	private String _contents;
	private int _sharedIndex = -1;
	
	public XLSXCell (XLSXSharedStrings sharedStringLookup, int columnNumber,
			String contents, boolean isSharedString) {
		_sharedStringLookup = sharedStringLookup;
		_columnNumber = columnNumber;
		_contents = contents == null ? "" : contents;
		if (isSharedString && _contents.length() > 0) {
			boolean isNumber = true;
			for (int count = 0; count < _contents.length(); count++) {
				if (!Character.isDigit(_contents.charAt(count))) {
					isNumber = false;
					break;
				}
			}
			if (isNumber) _sharedIndex = Integer.parseInt(_contents);
		}
	}
	
	/**
	 * 行内のこのセルの列番号（0始まり）を取得
	 * @return 列番号
	 */
	public int getColumnNuber() {
		return _columnNumber;
	}
	
	/**
	 * このセルの文字列を取得
	 * @return 文字列
	 */
	public String getContents() {
		if (_sharedIndex >= 0) return _sharedStringLookup.getSharedString(_sharedIndex);
		
		return _contents;
	}
}
