/**  
* <p>Title: Meter.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: www.baidudu.com</p>  
* @author Yujie Yang  
* @date 2018Äê5ÔÂ26ÈÕ  
* @version 1.0  
*/  
package System.Entity;

import java.util.Date;
import java.util.List;

import System.Entity.Record;

/**  
* <p>Title: Meter</p>  
* <p>Description: </p>  
* @author Yujie Yang 

*/
public interface Meter {
	    public Integer getUsed();
	    public Integer getLatestUsed();
	    public void addData(Date date, Integer data);
	    public List<Record> getHistory() ;
	    public List<Record> getTodayHistory();
	    public List<Record> getWeekHistory();
	    public List<Record> getMonHistory();
}
