/**  
* <p>Title: History.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: www.baidudu.com</p>  
* @author Yujie Yang  
* @date 2018年5月26日  
* @version 1.0  
*/  
package System.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import System.Entity.Record;
import System.Util.TimeUtil;

/**  
* <p>Title: History</p>  
* <p>Description: the object to store the consumption history</p>  
* @author Yujie Yang 
* @date 2018年5月26日  
*/
public class History implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int used;
	private volatile List<Record> history = new ArrayList<Record>();
    /**  
     * <p>Title: getUsed</p>  
     * <p>Description: get the amount of the total consumption </p>  
     * @return  the total consumption
     */  
    public Integer getUsed() {
        used = 0;
        synchronized(this) {
        	for(Record record : history){
        		used = record.getData() + used;
        	}
        	return used;
        }
    }
    public void addData(Date date, Integer data){
        history.add(new Record(date, data));
    }

    public List<Record> getHistory() {
        return history;
    }

    /**  
     * <p>Title: getTodayHistory</p>  
     * <p>Description: </p>  
     * @return  
     */  
    public List<Record> getTodayHistory(){
        long begin = TimeUtil.getTimesmorning().getTime();
        long end  = TimeUtil.getTimesnight().getTime();
        List<Record> temp = new ArrayList<Record>();
        synchronized(this) {
        for(Record record: history){
            	if(record.getDate().getTime() > begin && record.getDate().getTime() < end){
            		temp.add(record);
            	}
        	}
        }
        return temp;
    }

    /**  
     * <p>Title: getWeekHistory</p>  
     * <p>Description: </p>  
     * @return  
     */  
    public List<Record> getWeekHistory(){
        long begin = TimeUtil.getTimesWeekmorning().getTime();
        long end  = TimeUtil.getTimesWeeknight().getTime();
        List<Record> temp = new ArrayList<Record>();
        synchronized(this) {
        	for(Record record: history){
        	   if(record.getDate().getTime() > begin && record.getDate().getTime() < end){
        		   temp.add(record);
        	   }
        	}
        }
        return  temp;
    }

    /**  
     * <p>Title: getMonHistory</p>  
     * <p>Description: </p>  
     * @return  
     */  
    public List<Record> getMonHistory(){
        long begin = TimeUtil.getTimesMonthmorning().getTime();
        long end  = TimeUtil.getTimesMonthnight().getTime();
        List<Record> temp = new ArrayList<Record>();
        synchronized(this) {
        	for(Record record: history){
        		if(record.getDate().getTime() > begin && record.getDate().getTime() < end){
        			temp.add(record);
        		}
        	}
        }
        return  temp;
    }
    
    /**  
     * <p>Title: getLastUsed</p>  
     * <p>Description: Get the latest record</p>  
     * @return  
     */  
    public Integer getLastUsed() {
    	return this.history.get(this.history.size()-1).getData();
    }
}
