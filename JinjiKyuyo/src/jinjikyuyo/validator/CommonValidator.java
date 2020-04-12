package jinjikyuyo.validator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidator {
	
	// 日付のフォーマット
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	// 現在日付
	Calendar nowCalendar = Calendar.getInstance();
	// 正規表現：半角数値
	static final String HANKAKU_SUJI = "^[0-9]*$";
	// 正規表現：半角英数
	static final String HANKAKU_EISU = "a-zA-Z0-9";
	// 正規表現：半角記号
	static final String HANKAKU_KIGO = "!#%&'/=_~`\\*\\+\\?\\{\\}\\^\\$\\-\\|";
	// 正規表現：半角英数記号
	static final String HANKAKU_EISUKIGO = "[" + HANKAKU_EISU + HANKAKU_KIGO + "]";
	// 正規表現：メールアドレス（ドメイン）
	static final String DOMAIN = HANKAKU_EISUKIGO + "+" + "(\\." + HANKAKU_EISUKIGO + "+)*";
	// 正規表現：メールアドレス
	static final String MAIL_ADDRESS = "^" + DOMAIN + "@" + DOMAIN + "$";
	// 正規表現：全角カナ
	static final String ZENKAKU_KANA = "^[\\u30A0-\\u30FF]+$";

	/**
	 * 日付チェック
	 * @param date 日付
	 * @return 正しい日付の場合：TURE / 不正な日付の場合:FALSE
	 */
	public boolean isDate(String date) {
		if(date == null || date.length() != 8) {
			return false;
		}
		format.setLenient(false);
		try {
	        format.parse(date);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	/**
	 * 未来日チェック
	 * @param date 日付
	 * @return 未来の日付でない場合：TRUE / 未来の場合：FALSE
	 */
	public boolean isFuture(String date) {
		String today = format.format(nowCalendar.getTime());
		
		int diff = date.compareTo(today);
		
		if (diff > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 必須チェック
	 * @param value 文字列
	 * @return 入力されている場合：TRUE / null、または、空の場合：FALSE
	 */
	public boolean isRequired(String value) {
		if(value == null || value.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 半角数値チェック
	 * @param num 数値
	 * @return 半角数値の場合：TRUE / 半角数値以外の場合：FALSE
	 */
	public boolean isNumber(String num) {
		Pattern p = Pattern.compile(HANKAKU_SUJI);
		Matcher m = p.matcher(num);
		return m.find();
	}
	
	/**
	 * 桁数チェック（一致）
	 * @param value 文字列
	 * @param i 桁数
	 * @return 文字数が桁数と一致する場合：TRUE / 文字数が桁数と一致しない場合：FALSE
	 */
	public boolean isLength(String value, int i) {
		if(value.length() != i) {
			return false;
		}		
		return true;
	}
	
	/**
	 * 桁数チェック（以内）
	 * @param value 文字列
	 * @param i 桁数
	 * @return 文字数が桁数と以内の場合：TRUE / 文字数が桁数より大きい場合：FALSE
	 */
	public boolean isLengthUnder(String value, int i) {
		if(value.length() > i) {
			return false;
		}		
		return true;
	}
	
	/**
	 * メールアドレスチェック
	 * @param value メールアドレス
	 * @return メールアドレスのフォーマットになっている場合：TRUE / メールアドレスのフォーマットになっていない場合：FALSE
	 */
	public boolean isMailAddress(String value) {
		Pattern p = Pattern.compile(MAIL_ADDRESS);
		Matcher m = p.matcher(value);
		return m.find();
	}
	
	/**
	 * 全角カナチェック
	 * @param value 文字列
	 * @return 全角カナの場合：TRUE / 全角カナでない場合：FALSE
	 */
	public boolean isZenkakuKana(String value) {
		Pattern p = Pattern.compile(ZENKAKU_KANA);
		Matcher m = p.matcher(value);
		return m.find();
	}
}
