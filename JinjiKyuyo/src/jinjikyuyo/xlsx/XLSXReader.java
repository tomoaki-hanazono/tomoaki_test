package jinjikyuyo.xlsx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * xlsx形式のexcelデータ読込処理.
 */
public class XLSXReader {

	/** ワークシートのパス */
	private String WORKSHEETPATH = "xl/worksheets/";
	/** sharedstrings.xmlのパス */
	private String SHAREDSTRINGPATH = "xl/sharedstrings.xml";
	/** ワークブックのコンテナ */
	private XLSXWorkbook _workbook = new XLSXWorkbook();
	/** 共通文字列のコンテナ */
	private XLSXSharedStrings _sharedStrings = new XLSXSharedStrings();
	
	/**
	 * excelデータの読込.
	 * @param fileName ファイル名＋パス
	 * @throws IOException
	 */
	public XLSXReader(String fileName) throws IOException {
		this(new File(fileName));
	}
	
	public XLSXReader(File file) throws IOException {
		this(new FileInputStream(file));
	}
	
	/**
	 * ワークブックの取得
	 * @return ワークブック
	 */
	public XLSXWorkbook getWorkbook() {
		return _workbook;
	}
	
	/**
	 * excelデータの読込.
	 * @param Stream excelデータ
	 * @throws IOException
	 */
	public XLSXReader(InputStream Stream) throws IOException {
		// excelファイルをzipファイル形式で読み込み
		ZipInputStream instream = new ZipInputStream(Stream);
		ZipEntry entry = null;
		
		// zipファイル・ディレクトリの読み込み
		while ((entry = instream.getNextEntry()) != null) {
			// ディレクトリの場合、処理をスキップ
			if (entry.isDirectory()) continue;
			
			// sharedstrings.xmlの場合
			if (entry.getName().compareToIgnoreCase(SHAREDSTRINGPATH) == 0) {
				// sharedstrings.xmlのコンテンツを読み込み
				ByteArrayOutputStream buffer = new ByteArrayOutputStream(8192);
				while (true) {
					int b = instream.read();
					if (b == -1) break;
					buffer.write(b);
				}
				// 共通文字列コンテナ設定処理を呼出
				parseSharedStrings(buffer.toByteArray());
				continue;
			}
			
			// ワークシートディレクトリ配下でない場合、処理をスキップ
			if (!entry.getName().toLowerCase().startsWith(WORKSHEETPATH)) continue;

			// ワークシートのパスを除いたファイル名を取得
			String filename = entry.getName().substring(WORKSHEETPATH.length());
			// ワークシートディレクトリ直下で無い場合、処理をスキップ
			if (filename.contains("/")) continue;

			// ファイルのコンテンツを読み込み
			ByteArrayOutputStream buffer = new ByteArrayOutputStream(8192);
			while (true) {
				int b = instream.read();
				if (b == -1) break;
				buffer.write(b);
			}
 
			// シート設定処理を呼出
			parseSpreadsheet(filename,buffer.toByteArray());
		}
		// シートに共通文字列コンテナを設定
		_workbook.setSharedStringLookup(_sharedStrings);
		instream.close();
	}
	
	/**
	 * 共通文字列コンテナの設定.
	 * @param contents コンテンツ
	 */
	private void parseSharedStrings(byte[] contents) {
		try {
			// コンテンツを開く
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(contents));
			
			int index = 0;
			// タグ：siのリストを取得
			NodeList shared = doc.getElementsByTagName("si");
			// タグ：siのリストの件数分をループ
			for (int current = 0; current < shared.getLength(); current++) {
				// タグ：si内の要素を取得
				Element si = (Element)shared.item(current);
				
				// タグ：si内の要素内のタグ：tのリストを取得
				NodeList textData = si.getElementsByTagName("t");
				// 共通文字列
				StringBuffer sb = new StringBuffer();
				// タグ：tのリストの件数分をループ
				for (int i = 0; i < textData.getLength(); i++) {
					// タグ：t内の要素を取得
					Element el = (Element)textData.item(i);
					// タグ：t内の最初の要素の値を共通文字列に追加
					if (el.getFirstChild() != null) {
						String value = el.getFirstChild().getNodeValue();
						sb.append(value);
					}
				}

				// 共通文字列が存在する場合、共通文字列コンテナに設定
				// 存在しない場合は、nullを設定
				if (sb == null || sb.toString().equals("")) {
					_sharedStrings.addSharedString(index++, null);
				} else {
					_sharedStrings.addSharedString(index++, sb.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * シートの設定
	 * @param Filename ファイル名
	 * @param Contents コンテンツ
	 */
	private void parseSpreadsheet(String Filename,byte [] Contents) {
		// シート名を設定
		String sheetName = Filename;
		if (Filename.contains("."))
			sheetName = Filename.substring(0,Filename.lastIndexOf("."));

		// ワークブックにシートを追加
		int sheetIndex = _workbook.addSheet(sheetName);

		try {
			// コンテンツを開く
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document dom = builder.parse(new ByteArrayInputStream(Contents));

			// タグ：sheetDataのリストを取得
			NodeList sheetData = dom.getElementsByTagName("sheetData");
			// タグ：sheetDataのリストの件数分をループ
			for (int current = 0; current < sheetData.getLength(); current++)
				// シートデータ設定処理を呼出
				parseSheetData((Element)sheetData.item(current),sheetIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * シートデータの設定.
	 * @param SheetData シートデータ
	 * @param SheetIndex シートのインデックス
	 * @throws Exception
	 */
	private void parseSheetData(Element SheetData,int SheetIndex) throws Exception {
		// タグ：rowのリストを取得
		NodeList rowData = SheetData.getElementsByTagName("row");
		// タグ：rowのリストの件数分をループ
		for (int curRow = 0; curRow < rowData.getLength(); curRow++) {
			// 行の要素を取得
			Element row = (Element)rowData.item(curRow);

			try {
				// タグ：cのリストを取得
				NodeList colData = row.getElementsByTagName("c");
				// タグ：cのリストの件数分をループ
				for (int curCol = 0; curCol < colData.getLength(); curCol++) {
					// 列の要素を取得
					Element col = (Element)colData.item(curCol);
					// 列の要素からセルのkeyを取得
					String cellKey = col.getAttribute("r");

					// 共通文字列フラグ
					boolean isSharedString = false;
					// 列の要素の文字列タイプを取得
					String typeString = col.getAttribute("t");
					// 文字列タイプが"s"の場合、共通文字列を使用
					if (typeString != null && typeString.compareToIgnoreCase("s") == 0)
						isSharedString = true;

					// タグ：vを取得
					NodeList valueData = col.getElementsByTagName("v");
					// タグ：vが存在しない場合、タグ：tを取得する
					if (valueData.getLength() == 0) valueData = col.getElementsByTagName("t");
					if (valueData.getLength() > 0) {
						// 最初の要素から値を取得
						Element el = (Element)valueData.item(0);
						String value = el.getFirstChild().getNodeValue();
						// ワークブックにセルを追加
						_workbook.addCell(SheetIndex,cellKey, new XLSXCell(value, isSharedString));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
