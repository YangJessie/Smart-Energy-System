package System.Entity;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import System.Dao.ProviderDao;
import System.Dao.gasUpdaterDao;
import System.Entity.consumer;

public class gasUpdater extends TimerTask implements Updater{
    private consumer consumer;
    private int alarm;
    public gasUpdater(consumer c){
    	this.consumer=c;
    }
	public consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(consumer consumer) {
		this.consumer = consumer;
	}
    public void startUpdate(){
    	
    	Timer timer=new Timer();
    	timer.schedule(this, new Date(), 30*60*1000);
    }
	@Override
	public void update(consumer con) {
		// TODO Auto-generated method stub
		this.consumer.getGasMeter().addData(new Date(), gasUpdaterDao.fakeData());
		
	}
	/**  
	 * <p>Title: gasAlarm</p>  
	 * <p>Description: check whether the gas consumption was higher than budget</p>  
	 * @param con
	 * @param tariff  
	 */  
	public void gasAlarm(consumer con,double tariff){
		if(this.consumer.getGasBudget()>0) {
			double cost=(con.getGasMeter().getUsed())*tariff;
			if(cost<con.getGasBudget()) this.setAlarm(1); //gas usage has not been exceeded
			else                         this.setAlarm(2); //gas usge has been exceeded
		}
		else this.setAlarm(0);   	
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		update(this.consumer);
		try {
			gasAlarm(this.consumer,ProviderDao.readData().getGasTariff());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getAlarm() {
		System.out.println("Gas use exceed? "+this.alarm);
		return alarm;
	}
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

}
