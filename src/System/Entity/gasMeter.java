package System.Entity;

import System.Entity.History;
import System.Entity.Record;
import System.Util.TimeUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**  
* @className: gasMeter</p>  
* @Description: </p>  
* @author Yujie Yang 
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class gasMeter implements Serializable, Meter {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private volatile History history = new History();
    @Override
    public Integer getUsed() {
    	return history.getUsed();
    }
    @Override
    public Integer getLatestUsed() {
        return history.getLastUsed();
    }
    @Override
    public void addData(Date date, Integer data){
        history.addData(date, data);
    }
    @Override
    public List<Record> getHistory() {
        return history.getHistory();
    }
    @Override
    public List<Record> getTodayHistory(){
    	return history.getTodayHistory();
    }
    @Override
    public List<Record> getWeekHistory(){
    	return history.getWeekHistory();
    }
    @Override
    public List<Record> getMonHistory(){
    	return history.getMonHistory();
    }

}
