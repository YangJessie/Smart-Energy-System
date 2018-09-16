package System.Constant;

/**  
* @className: constant</p>  
* @Description: constant includes file directory and file names</p>  
* @author Yan Pan 
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class constant {
	public static final Double GAS_DEFAULT_UNIT_PRICE = 3.88;
    public static final Double ELECTRICITY_DEFAULT_UNIT_PRICE = 14.60;
    public static final String METER = "kWh";
    public static final Integer GAS_DEAULT_UPDATE_PERIOD = 30;
    public static final Integer ELECTRICITY_DEFAULT_UPDATE_PERIOD = 30;
    public static final String PROVIDER_DATA_FILE_PATH = "./provider/data/";
    public static final String CONSUMER_DATA_FILE_PATH = "./consumer/data/";
    public static final String CONSUMER_SEND_FILE_PATH = "/send";
    public static final String PROVIDER_RECEIVE_FILE_PATH = "./provider/receive";
    public static final String PROVIDER_CONSUMER_DATA_FILE_PATH = "./provider/data/consumer";
    public static final String PROVIDER_DATA_FILE_NAME = "provider.dat";
    public static final String CONSUMER_DATA_FILE_NAME = "data.dat";
    public static final String CUNSUMER_ELECTRICCITY_FILE_NAME = "electricityMeter.dat";
    public static final String CUNSUMER_GAS_FILE_NAME = "gasMeter.dat";
    public static final String SEND_FILE_NAME_PREFIX ="_send.dat";
    public static final String RECEIVE_FILE_NAME_PREFIX = "_receive.dat";
    public static final Integer COL_DELETE = 2;
    public static final Integer COL_VIEW = 3;
    public static final String PROVIDER_NAME = "Provider";
    public static final String CONSUMER_NAME = "Consumer";
    public static final Integer CONSUMER_LOGIN_CHECK_ERROR = -1;
	public static final String[] DAY_COL = {"Time","Used"};
	public static final String[] MONTH_COL = {"Month", "Total used","Cost"};
	public static final String[] WEEK_COL = {"Week", "Total used","Cost"};
	public static final String GAS_TYPE = "Gas";
	public static final String ElE_TYPE = "Ele";
   
    
}

 
