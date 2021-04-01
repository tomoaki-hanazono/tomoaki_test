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

public class XLSXReader {
	
	private String WORKSHEETPATH = "xl/worksheets/";
	private String SHAREDSTRINGPATH = "xl/sharedstrings.xml";
	private XLSXWorkbook _workbook = new XLSXWorkbook();
	private XLSXSharedStrings _sharedStrings = new XLSXSharedStrings();
	
	public XLSXReader(String fileName) throws IOException {
		this(new File(fileName));
	}
	
	public XLSXReader(File file) throws IOException {
		this(new FileInputStream(file));
	}
	
	public XLSXWorkbook getWorkbook() {
		return _workbook;
	}
	
	public XLSXReader(InputStream Stream) throws IOException {
		// excelファイルをzipファイル形式で読み込み
		ZipInputStream instream = new ZipInputStream(Stream);
		ZipEntry entry = null;
		
		while ((entry = instream.getNextEntry()) != null) {
			if (entry.isDirectory()) continue;
			
			if (entry.getName().compareToIgnoreCase(SHAREDSTRINGPATH) == 0) {
				// read the contents of the entry
				ByteArrayOutputStream buffer = new ByteArrayOutputStream(8192);
				while (true) {
					int b = instream.read();
					if (b == -1) break;
					buffer.write(b);
				}
				parseSharedStrings(buffer.toByteArray());
				continue;
			}
				
			if (!entry.getName().toLowerCase().startsWith(WORKSHEETPATH)) continue;

			String filename = entry.getName().substring(WORKSHEETPATH.length());
			if (filename.contains("/")) continue;

			// read the contents of the entry
			ByteArrayOutputStream buffer = new ByteArrayOutputStream(8192);
			while (true) {
				int b = instream.read();
				if (b == -1) break;
				buffer.write(b);
			}
			// uncomment to see the xml itself: String contents = new String(buffer.toByteArray(),Charset.forName("UTF-8"));
 
			parseSpreadsheet(filename,buffer.toByteArray());
		}
		instream.close();
	}
	
	private void parseSharedStrings(byte[] contents) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(contents));
			
			int index = 0;
			NodeList shared = doc.getElementsByTagName("si");
			for (int current = 0; current < shared.getLength(); current++) {
				Element si = (Element)shared.item(current);
				
				NodeList textData = si.getElementsByTagName("t");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < textData.getLength(); i++) {
					Element el = (Element)textData.item(i);
					if (el.getFirstChild() != null) {
						String value = el.getFirstChild().getNodeValue();
						sb.append(value);
					}
				}

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
	
	private void parseSpreadsheet(String Filename,byte [] Contents) {
		String sheetName = Filename;
		if (Filename.contains("."))
			sheetName = Filename.substring(0,Filename.lastIndexOf("."));

		int sheetIndex = _workbook.addSheet(sheetName);

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document dom = builder.parse(new ByteArrayInputStream(Contents));

			NodeList sheetData = dom.getElementsByTagName("sheetData");
			for (int current = 0; current < sheetData.getLength(); current++)
				parseSheetData((Element)sheetData.item(current),sheetIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseSheetData(Element SheetData,int SheetIndex) throws Exception {
		NodeList rowData = SheetData.getElementsByTagName("row");
		for (int curRow = 0; curRow < rowData.getLength(); curRow++) {
			Element row = (Element)rowData.item(curRow);

			try {
				int rowIndex = Integer.parseInt(row.getAttribute("r")) - 1;    // 0-based row number

				NodeList colData = row.getElementsByTagName("c");
				for (int curCol = 0; curCol < colData.getLength(); curCol++) {
					Element col = (Element)colData.item(curCol);
					int colIndex = DecodeColumnNumber(col.getAttribute("r"));

					boolean isSharedString = false;
					String typeString = col.getAttribute("t");       // t="s" for shared string
					if (typeString != null && typeString.compareToIgnoreCase("s") == 0)
						isSharedString = true;

					NodeList valueData = col.getElementsByTagName("v");
					if (valueData.getLength() == 0) // Add by saka-en
						valueData = col.getElementsByTagName("t"); // Add by saka-en for t="inlineStr"
					if (valueData.getLength() > 0) {
						Element el = (Element)valueData.item(0);
						String value = el.getFirstChild().getNodeValue();
						_workbook.addCell(SheetIndex,rowIndex,
								new XLSXCell(_sharedStrings,colIndex,value,isSharedString));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private int DecodeColumnNumber(String Attribute) {
		if (Character.isLetter(Attribute.charAt(1))) {
			int multiplier = (Character.toUpperCase(Attribute.charAt(0)) - 'A') + 1;
			int column = Character.toUpperCase(Attribute.charAt(1)) - 'A';

			return multiplier * 26 + column;
		}

		return Character.toUpperCase(Attribute.charAt(0)) - 'A';
	}
}
