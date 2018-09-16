package Test;

import java.io.IOException;

import System.Dao.consumerDao;
import System.Entity.*;

public class sprintOne {
	 public static void main(String args[]) throws IOException, ClassNotFoundException {
	        consumer con1 = new consumer("JessieYang","12345");
	        
	        elecUpdater ele1=new elecUpdater(con1);
	        ele1.setConsumer(con1);
	        ele1.startUpdate();
	        while(true){
	        	System.out.println("---------------------------------------------");
	        	
	        	System.out.println(con1.getElectricityMeter().getMonHistory());
	        	System.out.println(con1.getElectricityMeter().getWeekHistory());
	        	System.out.println(con1.getElectricityMeter().getTodayHistory());
	        	
	        }
	    }
}
