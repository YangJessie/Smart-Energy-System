package System.Dao;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;
import System.Constant.constant;
import System.Entity.*;

/**  
* @className: consumerDao  
* @Description: methods used for reading/writing consumer information  
* @author Yan Pan  
*/  
public class consumerDao {
	/**  
	 * <p>Title: saveData</p>  
	 * <p>Description: method for save consumer data</p>  
	 * @param consumer
	 * @return
	 * @throws IOException  
	 */  
	public static int saveData(consumer consumer) throws IOException {
        String uuid = consumer.getId();
        String uname=consumer.getName();
        File file = new File(constant.CONSUMER_DATA_FILE_PATH + uname, constant.CONSUMER_DATA_FILE_NAME);
        //new consumer
        if(!file.exists()){    
            File diretory = new File(constant.CONSUMER_DATA_FILE_PATH + uname);
            diretory.mkdirs();
            file.createNewFile();
        }
        //old consumer
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(consumer);
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("Consumer: " + consumer.getName() + " Data Saved");
        return 1;
    }
    /**  
     * <p>Title: readData</p>  
     * <p>Description: method for reading consumer data</p>  
     * @param name
     * @return the consumer who successfully log in
     * @throws IOException
     * @throws ClassNotFoundException  
     */  
    public static consumer readData(String name) throws IOException, ClassNotFoundException {
        File file = new File(constant.CONSUMER_DATA_FILE_PATH + name,constant.CONSUMER_DATA_FILE_NAME);
        File directory = new File(constant.CONSUMER_DATA_FILE_PATH + name);
        //old consumer
        if(directory.exists() && file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            consumer consumer = (consumer) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Consumer: " + consumer.getName() + " Loaded Data");
            return consumer;
        }
        else {
        	 System.out.println("Consumer: " + name + " did not exit.");
            
        }
        return null;
    }
    /**  
     * <p>Title: sendData</p>  
     * <p>Description: method for sending user consumption</p>  
     * @param consumer
     * @throws IOException  
     */  
    public static void sendData(consumer consumer)throws IOException {
	       String uname = consumer.getName();
	       File file = new File(constant.CONSUMER_DATA_FILE_PATH+uname+constant.CONSUMER_SEND_FILE_PATH,uname+constant.SEND_FILE_NAME_PREFIX);
	       File directory = new File(constant.CONSUMER_DATA_FILE_PATH+uname+constant.CONSUMER_SEND_FILE_PATH);      
	       
	       File file_p = new File(constant.PROVIDER_RECEIVE_FILE_PATH,uname+constant.RECEIVE_FILE_NAME_PREFIX);
	       File directory_p = new File(constant.PROVIDER_RECEIVE_FILE_PATH);
	       
	       
	       if(!file.exists()){
	    	   directory.mkdirs();
	    	   file.createNewFile();
	       }
	       if(!file_p.exists()){
	    	   directory_p.mkdirs();
	    	   file_p.createNewFile();
	       }
	      
	       FileOutputStream fileOutputStream = new FileOutputStream(file);
	       OutputStreamWriter os = new OutputStreamWriter(fileOutputStream);
	       StringBuffer sb = new StringBuffer();      
	       
	       sb.append("Gas:\n");
	      
	       List<Record> temp = (List)consumer.getGasMeter().getMonHistory();
	       int size = temp.size();
	       int count = 0;
	       for(int i=0;i<size;i++){
	    	   count = count + temp.get(i).getData();
	    	   sb.append(temp.get(i).getDate().toString()+" "+count+" "+temp.get(i).getData()+"\n");
	       }
	       System.out.println("gas month usage: "+count);
	       
	       sb.append("Electricity:\n");
	       temp = (List)consumer.getElectricityMeter().getHistory();
	       size = temp.size();
	       count = 0;
	       for(int i=0;i<size;i++){
	    	   count = count +temp.get(i).getData();
	    	   sb.append(temp.get(i).getDate().toString()+" "+count+" "+temp.get(i).getData().toString()+"\n");
	       }
	       System.out.println("Electricity month useage: "+count);
	       os.write(sb.toString());
	       os.close();
	       fileOutputStream.close();
	       
	       fileOutputStream = new FileOutputStream(file_p);
	       os = new OutputStreamWriter(fileOutputStream);
	       os.write(sb.toString());
	       os.close();
	       fileOutputStream.close();
          }
}
    
