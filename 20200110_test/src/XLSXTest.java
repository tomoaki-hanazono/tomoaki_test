
public class XLSXTest {

	// 読み込むexcel
	private static final String INPORT_XLSX = "/home/tomoaki_hanazono/ドキュメント/test.xlsx";
	// 対象シート
	private static final String SHEET1_NAME = "sheet1";
	
	public static void main(String[] args) throws Exception {
		XLSXWorkbook workbook = (new XLSXReader(INPORT_XLSX)).getWorkbook();
		XLSXSheet sheet = workbook.getSheet(SHEET1_NAME);
		
		XLSXRow row3 = sheet.getRowAt(2);
		XLSXCell cellB3 = row3.getCellAt(1);
		XLSXCell cellC3 = row3.getCellAt(2);
		XLSXCell cellD3 = row3.getCellAt(3);
		XLSXCell cellE3 = row3.getCellAt(4);
		XLSXCell cellF3 = row3.getCellAt(5);
		XLSXCell cellG3 = row3.getCellAt(6);
		XLSXCell cellH3 = row3.getCellAt(7);
		
		System.out.println("値：" + cellB3.getContents());
		System.out.println("値：" + cellC3.getContents());
		System.out.println("値：" + cellD3.getContents());
		System.out.println("値：" + cellE3.getContents());
		System.out.println("値：" + cellF3.getContents());
		System.out.println("値：" + cellG3.getContents());
		System.out.println("値：" + cellH3.getContents());
	}
}
