package jinjikyuyo.xlsx;

/**
 * スプレッドシート内の単一セルのデータのコンテナ.
 */
public class XLSXCell {
	
	/** セルの値 */
	private String _contents;
	/** 共通文字列識別番号 */
	private boolean _isSharedString = false;
	
	/**
	 * セルの設定.
	 * @param contents セルの値
	 * @param isSharedString 共通文字列フラグ true:使用 false:未使用
	 */
	public XLSXCell (String contents, boolean isSharedString) {
		// セルの値を設定
		_contents = contents == null ? "" : contents;
		// 共通文字列を使用する、かつ、セルの値が存在する場合
		if (isSharedString && _contents.length() > 0) {
			// 数値フラグ
			boolean isNumber = true;
			// セルの値が数値か判定
			for (int count = 0; count < _contents.length(); count++) {
				if (!Character.isDigit(_contents.charAt(count))) {
					isNumber = false;
					break;
				}
			}
			// セルの値が数値の場合、共通文字列フラグを設定
			if (isNumber) _isSharedString = true;
		}
	}
	
	/**
	 * セルの文字列を取得.
	 * @return 文字列
	 */
	public String getContents() {
		return _contents;
	}
	
	/**
	 * 共通文字列フラグを取得.
	 * @return 共通文字列フラグ true:使用 false:未使用
	 */
	public boolean isSharedString() {
		return _isSharedString;
	}
}
