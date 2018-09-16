package System.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import System.Entity.Record;

/**  
* @className: TimeUtil  
* @Description: Methods to calculate period of time   
* @author Yujie Yang 
*/  
public class TimeUtil {
	// get the data in Date form of today 00:00
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    // get the data in Date form of today 24:00
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return  cal.getTime();
    }


    // get the data in Date form of Monday 00:00 this week
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return  cal.getTime();
    }


    // get the data in Date form of Sunday 24:00 this week
    public  static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }


    // get the data in Date form of 00:00 on the first day of this month
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return  cal.getTime();
    }


    // get the data in Date form of 24:00 on the last day of this month
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }
    public static List<Long> getMonthList(List<Record> history){
    	Calendar cal = Calendar.getInstance();
    	long begin = 0;
    	long end = 0;
    	String beginString = null;
    	String endString = null;
    	List<Long> datesList= new ArrayList<Long>();
    	List<String> tempDate = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
    	for(int i = 0; i< history.size() ;i ++) {
    		cal.setTime(history.get(i).getDate());
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            begin = cal.getTime().getTime();
            beginString = formatter.format(cal.getTime());
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 24);
            end = cal.getTime().getTime();
            endString = formatter.format(cal.getTime());
            if(tempDate.contains(beginString)) {
            	continue;
            }
            else {
            	datesList.add(begin);
            	datesList.add(end);
            	tempDate.add(beginString);
            	tempDate.add(endString);
            }
    	}
    	return datesList;
    }
}
