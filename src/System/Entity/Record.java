package System.Entity;

import java.io.Serializable;
import java.util.Date;

/**  
* @className: Record 
* @Description: the object to store consumption history
* @author Xuanxi Liu 
*/  
public class Record implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4862854956447027966L;
	private Date date;
	private Integer data;
	public Record(Date date, Integer data) {
		this.date = date;
		this.data = data;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}

}
//record新增加一个类
