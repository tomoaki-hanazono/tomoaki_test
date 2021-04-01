import java.util.ArrayList;
import java.util.List;

/**
 * XLSXファイルのシートのワークブックのコンテナ.
 */
public class XLSXWorkbook {

	private List<XLSXSheet> _sheets = new ArrayList<XLSXSheet>();
	
	/**
	 * このワークブック内のシートの総数を取得.
	 * @return シートの総数
	 */
	public int getTotalSheets() {
		return _sheets.size();
	}
	
	/**
	 * 新しいシートの追加.
	 * @param sheetName シート名
	 * @return 新しいシートの番号
	 */
	public int addSheet(String sheetName) {
		int index = _sheets.size();
		_sheets.add(new XLSXSheet(sheetName));
		return index;
	}
	
	/**
	 * 指定した名前のシート取得.（名前は大文字と小文字を区別しない方法でチェック）
	 * 存在しない場合は、nullを返す.
	 * @param sheetName シート名
	 * @return シート
	 */
	public XLSXSheet getSheet(String sheetName) {
		for (XLSXSheet sheet : _sheets) {
			if (sheet.getSheetName().compareToIgnoreCase(sheetName) == 0) return sheet;
		}
		return null;
	}
	
	/**
	 * ブック内のシート毎に設定されたインデックスからシートを取得.
	 * @param sheetIndex
	 * @return シート
	 * @throws IndexOutOfBoundsException インデックスが範囲外の場合
	 */
	public XLSXSheet getSheetAt(int sheetIndex) throws IndexOutOfBoundsException {
		if (sheetIndex < 0 || sheetIndex >= _sheets.size())
			throw new IndexOutOfBoundsException();
		return _sheets.get(sheetIndex);
	}
	
	/**
	 * シートに新しいセルを追加.
	 * @param sheetIndex
	 * @param rowIndex
	 * @param cell
	 * @throws IndexOutOfBoundsException インデックスが範囲外の場合
	 */
	public void addCell(int sheetIndex, int rowIndex, XLSXCell cell) throws IndexOutOfBoundsException {
		if (sheetIndex < 0 || sheetIndex >= _sheets.size())
			throw new IndexOutOfBoundsException();
		
		_sheets.get(sheetIndex).addCell(rowIndex, cell);
	}
}
