package taijukiroku.validator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidator {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	Calendar nowCalendar = Calendar.getInstance();
	static final String HANKAKU_SUJI = "^[0-9]*$";

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
	
	public boolean isFuture(String date) {
		String today = format.format(nowCalendar.getTime());
		
		int diff = date.compareTo(today);
		
		if (diff > 0){
			return false;
		}
		return true;
	}
	
	public boolean isRequired(String value) {
		if(value == null || value.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean isNumber(String num) {
		Pattern p = Pattern.compile(HANKAKU_SUJI);
		Matcher m = p.matcher(num);
		return m.find();
	}
	
	public boolean isLength(String value, int i) {
		if(value.length() != i) {
			return false;
		}		
		return true;
	}
	
	public boolean isLengthOver(String value, int i) {
		if(value.length() > i) {
			return false;
		}		
		return true;
	}
	
	public boolean isBodyInfo(String value) {
		String s = value.replace(".", "");
		int i = 4;
		if (value.indexOf(".") < 0) {
			i = 3;
		}		
		return this.isNumber(s) && this.isLengthOver(s,i);
	}
}
