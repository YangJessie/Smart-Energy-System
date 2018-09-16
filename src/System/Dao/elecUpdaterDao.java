package System.Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import System.Constant.constant;
import System.Entity.consumer;
import System.Entity.electricityMeter;

/**  
* @className: elecUpdaterDao</p>  
* @Description: methods for reading/writing electricity meter data</p>  
* @author Yan Pan
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class elecUpdaterDao {
	/**  
	 * <p>Title: saveData</p>  
	 * <p>Description:method for saving data </p>  
	 * @param consumer
	 * @return the number represented whether successfully saving data
	 * @throws IOException  
	 */  
	public static int saveData(consumer consumer) throws IOException {
        String uuid = consumer.getId();
        String uname=consumer.getName();
        File file = new File(constant.CONSUMER_DATA_FILE_PATH + uname, constant.CUNSUMER_ELECTRICCITY_FILE_NAME);
        //new consumer
        if(!file.exists()){    
            File diretory = new File(constant.CUNSUMER_ELECTRICCITY_FILE_NAME + uname);
            diretory.mkdirs();
            file.createNewFile();
        }
        //old consumer
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(consumer.getElectricityMeter());
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("Consumer : " + consumer.getName() + " elecMeter Data Saved");
        return 1;
    }
	/**  
	 * <p>Title: readData</p>  
	 * <p>Description:method for reading data </p>  
	 * @param name
	 * @return the electricity meter data user wants to read
	 * @throws IOException
	 * @throws ClassNotFoundException  
	 */  
	public static electricityMeter readData(String name) throws IOException, ClassNotFoundException {
        File file = new File(constant.CONSUMER_DATA_FILE_PATH + name,constant.CUNSUMER_ELECTRICCITY_FILE_NAME);
        File directory = new File(constant.CONSUMER_DATA_FILE_PATH + name);
        if(directory.exists() && file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            electricityMeter electricityMeter = (electricityMeter) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Loaded Electric Data");
            return electricityMeter;
        }
        else {
            directory.mkdir();
            file.createNewFile();
            System.out.println("Init Successfully");
            return new electricityMeter();
        }
    }
    /**  
     * <p>Title: fakeData</p>  
     * <p>Description:Creat random consumption </p>  
     * @return  
     */  
    public static Integer fakeData(){
        Random random = new Random();
        return  random.nextInt(3);   //random number smaller than 2
    }
   
}
