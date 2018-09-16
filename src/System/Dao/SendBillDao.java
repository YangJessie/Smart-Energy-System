package System.Dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import System.Constant.constant;
import System.Entity.Record;
import System.Entity.consumer;

/**  
* @className: SendBillDao</p>  
* @Description: methods for sending email file</p>  
* @author Yan Pan
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class SendBillDao {
	/**  
	 * <p>Title: writeBill</p>  
	 * <p>Description: Create the bill file</p>  
	 * @param consumer
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException  
	 */  
	public static String writeBill(consumer consumer) throws ClassNotFoundException, IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("User: " + consumer.getName() +" ID: " + consumer.getId() + "\n");
        sb.append("Gas:\n");
        List<Record> temp = consumer.getGasMeter().getMonHistory();
        int size = temp.size();
        int count = 0;
        for(int i = 0; i< size ; i++) {
        	count = count + temp.get(i).getData();
        	sb.append(temp.get(i).getDate().toString()+ " " +count + " "+ temp.get(i).getData() + "\n");
        }
        sb.append("Total cost: " + count*ProviderDao.readData().getGasTariff() + "\n");
        sb.append("Electricity:\n");
        temp = consumer.getElectricityMeter().getMonHistory();
        size = temp.size();
        count = 0;
        for(int i = 0; i< size ; i++) {
        	count = count + temp.get(i).getData();
        	sb.append(temp.get(i).getDate().toString()+ " " +count + " " + temp.get(i).getData().toString() + "\n");
        }
        sb.append("Total cost: " + count*ProviderDao.readData().getElectricityTariff() + "\n");
        System.out.println("OK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return sb.toString();
	}
    /**  
     * <p>Title: sendData</p>  
     * <p>Description: Send(actually store in dat file) the bill to user</p>  
     * @param consumer
     * @param data
     * @throws IOException  
     */  
    public static void sendData(consumer consumer, String data) throws IOException {
    	String uuid = consumer.getName();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	int month = cal.get(Calendar.MONTH);
        File file = new File(constant.CONSUMER_DATA_FILE_PATH + uuid + constant.CONSUMER_SEND_FILE_PATH, month+"-bill.txt");      
        if(!file.exists()){
        	File directory = new File(constant.CONSUMER_DATA_FILE_PATH + uuid + constant.CONSUMER_SEND_FILE_PATH);
        	directory.mkdirs();
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter os = new OutputStreamWriter(fileOutputStream);
        os.write(data);
        os.close();
        fileOutputStream.close(); 
        System.out.println("Save Bill OK!!!!!!!!!!!!!!!!!!!!!!!");
    }
    public static String readBill(consumer con) throws IOException, ClassNotFoundException{
    	String uuid = con.getName();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	int month = cal.get(Calendar.MONTH);
        File file = new File(constant.CONSUMER_DATA_FILE_PATH + uuid + constant.CONSUMER_SEND_FILE_PATH, month+ "-bill.txt");
        File directory = new File(constant.CONSUMER_DATA_FILE_PATH + uuid + constant.CONSUMER_SEND_FILE_PATH);
        //old consumer
        if(directory.exists() && file.exists()){
        	FileReader reader=new FileReader(file);
        	BufferedReader bReader=new BufferedReader(reader);
        	StringBuilder sb=new StringBuilder();
        	String s="";
        	while ((s =bReader.readLine()) != null) {
        		sb.append(s + "\n");
                System.out.println(s);
            }
            bReader.close();
            reader.close();
            String bill = sb.toString();
            System.out.println(bill );       
            System.out.println("bill reveived.");
            return bill;
        }
        else {
        	 System.out.println("bill has not received! ");
            
        }
        return null;
    }
}
    
