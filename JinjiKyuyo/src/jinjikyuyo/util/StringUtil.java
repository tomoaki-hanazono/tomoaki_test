package jinjikyuyo.util;

/**
 * 文字列Util
 */
public class StringUtil {
	
	/**
	 * 全角半角変換（数値、「：」、「．」）.
	 * @param str 文字列
	 * @return 
	 */
	public String convert(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			switch(str.substring(i,i+1)) {
			case "１":
				sb.append("1");
				break;
			case "２":
				sb.append("2");
				break;
			case "３":
				sb.append("3");
				break;
			case "４":
				sb.append("4");
				break;
			case "５":
				sb.append("5");
				break;
			case "６":
				sb.append("6");
				break;
			case "７":
				sb.append("7");
				break;
			case "８":
				sb.append("8");
				break;
			case "９":
				sb.append("9");
				break;
			case "０":
				sb.append("0");
				break;
			case "：":
				sb.append(":");
			case "．":
				sb.append(".");
			case " ":
			case "　":
				break;
			default :
				sb.append(str.substring(i,i+1));
				break;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 数値チェック.
	 * @param str
	 * @return true:数値のみの文字列 false:数値以外を含む文字列
	 */
	public boolean checkNm(String str) {
		if (str == null) return false;
		for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
            	return false;
            }
		}
		return true;
	}
	
	/**
	 * 時間変換（分→時）
	 * @param m 分
	 * @return 時
	 */
	public int convertMtoH(int m) {
		int value = 0;
		if (m <= 0) {
			double d = Math.round((double)m / 60);
			value = (int)d;
		}
		
		return value;
	}

}
