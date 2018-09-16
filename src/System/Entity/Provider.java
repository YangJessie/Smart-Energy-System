package System.Entity;

import System.Dao.*;
import System.Util.UuidUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**  
* <p>Title: Provider</p>  
* <p>Description: The provider who </p>  
* @author Xuanxi Liu 
* @date 2018Äê5ÔÂ29ÈÕ  
*/  
public class Provider implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double gasTariff;
    private Double electricityTariff;
    private List<consumer> consumersList = new ArrayList<consumer>();
    private String password;
    private String name;

    /**  
    * <p>Title:Provider</p>  
    * <p>Description:constructor of the Provider </p>  
    * @param name
    * @param pass  
    */  
    public Provider(String name,String pass){
    	this.name=name;
    	this.password=pass;
        this.consumersList = new ArrayList<consumer>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setGasTariff(Double updateGasTariff){
        this.gasTariff = updateGasTariff;
    }
    public Double getGasTariff(){
        return gasTariff;
    }

    public void setElectricityTariff(Double updateElectricity){
        this.electricityTariff = updateElectricity;
    }

    public Double getElectricityTariff(){
        return this.electricityTariff;
    }
    /**  
     * <p>Title: generateNewConsumer</p>  
     * <p>Description: Provider is able to create new consumer </p>  
     * @param name
     * @param password
     * @throws IOException  
     */  
    public void generateNewConsumer(String name, String password) throws IOException {
        consumer newConsumer = new consumer(name,password);
        newConsumer.setId(UuidUtil.getUUID());
        consumersList.add(newConsumer);
        ProviderDao.saveData(this);
        consumerDao.saveData(newConsumer);
    }
    public void addConsumer(consumer consumer) {
    	this.consumersList.add(consumer);
    }
    
    /**  
     * <p>Title: removeConsumer</p>  
     * <p>Description: </p>  
     * @param name
     * @throws IOException  
     */  
    public void removeConsumer(String name) throws IOException {
		for(int i=0;i<consumersList.size();i++) {
			if(consumersList.get(i).getName()==name) {
				consumersList.remove(i);
				ProviderDao.deleteConsumer(name);
				break;
		     }
		}
    }
    public List<consumer> getConsumerList(){
    	return this.consumersList;
    }
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void delete(int index) {
    	this.consumersList.remove(index);
    }
}
