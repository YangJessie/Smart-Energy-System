/**  
* <p>Title: tableControl.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: www.baidudu.com</p>  
* @author Yujie Yang  
* @date 2018Äê5ÔÂ31ÈÕ  
* @version 1.0  
*/  
package System.Control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import System.Constant.constant;
import System.Dao.ProviderDao;
import System.Entity.consumer;
import System.Entity.Record;
import System.Util.TimeUtil;

/**  
* <p>Title: tableControl</p>  
* <p>Description: methods for displaying table</p>  
* @author Xuanxi Liu
*/
public class tableControl {
	public static String[][] getDayData(List<Record> temp){
		String[][] data = new String[temp.size()][2];
		for(int i = 0; i< temp.size();i++) {
			data[i][0] = temp.get(i).getDate().toString();
			data[i][1] = temp.get(i).getData().toString();
		}
		return data;
	} 
	/**  
	 * <p>Title: getMonthData</p>  
	 * <p>Description: </p>  
	 * @param temp
	 * @param readOne
	 * @param type
	 * @return table of monthly consumption
	 * @throws ClassNotFoundException
	 * @throws IOException  
	 */  
	public static String[][] getMonthData(List<Record> temp, consumer readOne,String type) throws ClassNotFoundException, IOException{
		List<Long> datesList = null;
		double price = 0;
		if(type.equals(constant.GAS_TYPE)){
			datesList = TimeUtil.getMonthList(readOne.getGasMeter().getHistory());
			price = ProviderDao.readData().getGasTariff();
		}
		else if(type.equals(constant.ElE_TYPE)) {
			datesList = TimeUtil.getMonthList(readOne.getElectricityMeter().getHistory());
			price = ProviderDao.readData().getElectricityTariff();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");

		String[][] data = new String[datesList.size()/2][3];
		for(int i = 0; i< datesList.size();i+=2) {
			data[i/2][0] = formatter.format(new Date(datesList.get(i)));
			int total = 0;
			for(int j = 0; j<temp.size();j++) {
				if(temp.get(j).getDate().getTime()>datesList.get(i) && temp.get(j).getDate().getTime()<datesList.get(i+1)) {
					total = total + temp.get(j).getData();
				}
			}
			data[i/2][1] = Integer.toString(total);
			data[i/2][2] = Double.toString(total*price);
		}
		return data;
	}
	/**  
	 * <p>Title: getWeekData</p>  
	 * <p>Description: </p>  
	 * @param temp
	 * @param readOne
	 * @param type
	 * @return the table of weekly consumption
	 * @throws ClassNotFoundException
	 * @throws IOException  
	 */  
	public static String[][] getWeekData(List<Record> temp,consumer readOne,String type) throws ClassNotFoundException, IOException{
		double price = 0;
		if(type.equals(constant.GAS_TYPE)){
			price = ProviderDao.readData().getGasTariff();
		}
		else if(type.equals(constant.ElE_TYPE)) {;
			price = ProviderDao.readData().getElectricityTariff();
		}		
		String[][] data = new String[1][3];
		Integer total = 0;
		for(int i = 0; i< temp.size();i++) {
			total = total + temp.get(i).getData();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		data[0][0] = formatter.format(TimeUtil.getTimesWeekmorning()) + " - " + formatter.format(TimeUtil.getTimesWeeknight());
		data[0][1] = Integer.toString(total);
		data[0][2] = Double.toString(total*price);
		return data;
	}
}
