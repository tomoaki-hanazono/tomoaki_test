package jinjikyuyo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {
	
	/**
	 * 和暦変換処理
	 * @param year
	 * @return
	 */
	public static String chageWareki(String year) {
		String wareki = null;
		try {
			// ロケールの設定（和暦用のロケール）
			Locale locale = new Locale("ja","JP","JP");
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy");
			Date date = sd.parse(year);
			
			// 和暦のロケールを指定して年をフォーマットする
			SimpleDateFormat sdf = new SimpleDateFormat("GGGGy年",locale);
			wareki = sdf.format(date);
			

		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return wareki;
	}
}
