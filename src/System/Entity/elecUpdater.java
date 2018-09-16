package System.Entity;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import System.Dao.elecUpdaterDao;
import System.Entity.consumer;
import System.Dao.ProviderDao;
import System.Dao.consumerDao;

/**  
* @className: elecUpdater</p>  
* @Description: </p>  
* @author Yujie Yang 
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class elecUpdater extends TimerTask implements Updater{
    private consumer consumer;   
    private int alarm;
    
    public elecUpdater(consumer c){
    	this.consumer=c;
    }
	public consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(consumer consumer) {
		this.consumer = consumer;
	}
    public void startUpdate(){
    	//this.elecUpdaterDao=new elecUpdaterDao();
    	Timer timer=new Timer();
    	timer.schedule(this, new Date(),3*1000);  //this:ElectricityScanner, period in millisecond
    }
	@Override
	public void update(consumer con) {
		// TODO Auto-generated method stub
		con.getElectricityMeter().addData(new Date(), elecUpdaterDao.fakeData());	
	}
	
	/**  
	 * <p>Title: elecAlarm</p>  
	 * <p>Description: check whether the electricity consumption was higher than budget</p>  
	 * @param con
	 * @param tariff  
	 */  
	public void elecAlarm(consumer con,double tariff){
		if(this.consumer.getElectricityBudget()>0) {
			double cost=(con.getElectricityMeter().getUsed())*tariff;
			if(cost<con.getElectricityBudget()) this.setAlarm(1); //Elec usage has not been exceeded
			else                                this.setAlarm(2); //Elec usge has been exceeded
		}
		
		else this.setAlarm(0);  
		System.out.println("elecAlarm "+this.alarm);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		update(this.consumer);
		try {
			elecAlarm(this.consumer,ProviderDao.readData().getElectricityTariff());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getAlarm() {
		System.out.println("Elec use exceed? "+this.alarm);
		return this.alarm;
	}
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

}
