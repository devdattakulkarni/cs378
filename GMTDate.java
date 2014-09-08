import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class GMTDate {
	
	/*
	 * Reference: http://stackoverflow.com/questions/7707555/getting-date-in-http-format-in-java
	 */
	public static void main(String [] args) {
		
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat(
	        "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
	    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	    System.out.println(dateFormat.format(calendar.getTime()));		
	}
}
