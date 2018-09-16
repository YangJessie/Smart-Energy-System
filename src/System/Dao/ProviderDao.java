package System.Dao;

import System.Constant.constant;
import System.Entity.*;

import java.io.*;
import java.util.List;

/**  
* @className: ProviderDao</p>  
* @Description: methods for reading/writing provider information</p>  
* @author Yan Pan
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class ProviderDao {
    /**  
     * <p>Title: saveData</p>  
     * <p>Description: </p>  
     * @param provider
     * @throws IOException  
     */  
    public static void saveData(Provider provider) throws IOException {
        File file = new File(constant.PROVIDER_DATA_FILE_PATH,constant.PROVIDER_DATA_FILE_NAME);
        if(!file.exists()){
            File diretory = new File(constant.PROVIDER_DATA_FILE_PATH);
            diretory.mkdirs();
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(provider);
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("Provider Data Saved");
    }
    /**  
     * <p>Title: readData</p>  
     * <p>Description: </p>  
     * @return
     * @throws IOException
     * @throws ClassNotFoundException  
     */  
    public static Provider readData() throws IOException, ClassNotFoundException {
        File file = new File(constant.PROVIDER_DATA_FILE_PATH,constant.PROVIDER_DATA_FILE_NAME);
        if(file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Provider provider = (Provider) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Loaded Data");
            return provider;
        }
        else {
            file.createNewFile();
            System.out.println("Init Successfully");
            return new Provider(null,null);
        }
    }
    /**  
     * <p>Title: deleteConsumer</p>  
     * <p>Description: delete an existed consumer</p>  
     * @param name  
     */  
    public static void deleteConsumer(String name) {
        File file = new File(constant.CONSUMER_DATA_FILE_PATH + name,constant.CONSUMER_DATA_FILE_NAME);
        File directory = new File(constant.CONSUMER_DATA_FILE_PATH + name);
        file.delete();
        directory.delete();
    }
  
}
