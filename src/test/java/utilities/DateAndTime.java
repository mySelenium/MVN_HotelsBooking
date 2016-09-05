package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTime {
	
	public static String getCurrentDateTime(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		   System.out.println(dateFormat.format(date));
		  
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
		   //System.out.println(dateFormat.format(cal.getTime()));
		   return dateFormat.format(cal.getTime());

	}

}
