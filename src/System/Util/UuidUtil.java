package System.Util;

import java.util.UUID;

/**  
* @className: UuidUtil
* @Description: method to creat user ID
* @author Yujie Yang 
*/  
public class UuidUtil {
	/**  
	 * <p>Title: getUUID</p>  
	 * <p>Description:method to creat user ID </p>  
	 * @return  
	 */  
	public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
