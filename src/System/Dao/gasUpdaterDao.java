package System.Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

import System.Constant.constant;
import System.Entity.gasMeter;

/**  
* @className: gasUpdaterDao</p>  
* @Description: </p>  
* @author Yan Pan
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class gasUpdaterDao {
	/**  
	 * <p>Title: readData</p>  
	 * <p>Description: </p>  
	 * @param uuid
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException  
	 */  
	public static gasMeter readData(String uuid) throws IOException, ClassNotFoundException {
        File file = new File(constant.CONSUMER_DATA_FILE_PATH + uuid,constant.CUNSUMER_GAS_FILE_NAME);
        File directory = new File(constant.CONSUMER_DATA_FILE_PATH + uuid);
        if(directory.exists() && file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            gasMeter gasMeter = (gasMeter) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Loaded Gas Data");
            return gasMeter;
        }
        else {
            directory.mkdir();
            file.createNewFile();
            System.out.println("Init Successfully");
            return new gasMeter();
        }
    }
   
    /**  
     * <p>Title: fakeData</p>  
     * <p>Description: </p>  
     * @return  
     */  
    public static Integer fakeData(){
        Random random = new Random();
        return  random.nextInt(10);     //random number smaller than 10 
    }

}
