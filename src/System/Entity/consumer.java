package System.Entity;

import System.Entity.consumer;
import System.Util.UuidUtil;
import java.io.Serializable;

/**  
* <p>Title: consumer</p>  
* <p>Description: consumer who uses the energy monitoring system </p>  
* @author Yujie Yang 
* @date 2018-5-29  
*/  
public class consumer implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String name;
	    private String id;
	    private String password;
	    private electricityMeter electricityMeter;
	    private gasMeter gasMeter;
	    private double electricityBudget=-1;
	    private double gasBudget=-1;
	    private String email;

	    public String getName() {
	        return name;
	    }

	    public consumer setName(String name) {
	        this.name = name;
	        return this;
	    }

	    public String getId() {
	        return id;
	    }

	    public consumer setId(String id) {
	        this.id = id;
	        return this;
	    }

	    public electricityMeter getElectricityMeter() {
	        return electricityMeter;
	    }

	    public gasMeter getGasMeter() {
	        return gasMeter;
	    }

	    public double getElectricityBudget() {
	        return electricityBudget;
	    }

	    public consumer setElectricityBudget(Integer electricityBudget) {
	        this.electricityBudget = electricityBudget;
	        return this;
	    }

	    public double getGasBudget() {
	        return gasBudget;
	    }

	    public consumer setGasBudget(Integer gasBudeget) {
	        this.gasBudget = gasBudeget;
	        return this;
	    }

	    public String getPassword(){
	        return this.password;
	    }

	    public consumer setPassword(String password){
	        this.password = password;
	        return this;
	    }
	    public consumer(String n,String pin){
	    	this.name=n;
	    	this.password=pin;
	        this.id = UuidUtil.getUUID();   //creat random ID 
	        this.electricityMeter = new electricityMeter();
	        this.gasMeter = new gasMeter();
	    }
        /**  
         * <p>Title: elecAlarm</p>  
         * <p>Description:keep checking whether the usage is higher than budget </p>  
         * @param tariff
         * @return  whether the consumption is higher than the budget
         */  
        public boolean elecAlarm(double tariff){
        	double cost=(this.getElectricityMeter().getUsed())*tariff;
        	if(cost<this.getElectricityBudget()) return false;  //Elec usage has not been exceeded
        	return true;   //exceed budget, need alert
        }
        public consumer(){
        	this.id = UuidUtil.getUUID();
        	this.electricityMeter = new electricityMeter();
        	this.gasMeter = new gasMeter();
        }

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
        
}
