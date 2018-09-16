package System.Control;

import java.awt.Font;
import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import System.Dao.ProviderDao;
import System.Dao.consumerDao;
import System.Entity.Provider;
import System.Entity.consumer;

/**  
* <p>Title: checkLogin</p>  
* <p>Description: </p>  
* @author Xuanxi Liu  
* @date 2018Äê5ÔÂ31ÈÕ  
*/
public class checkLogin {
	 /**  
	 * <p>Title: userLogin</p>  
	 * <p>Description: method for user login </p>  
	 * @param jtf the text field to enter user name
	 * @param jpf the password field to enter user password
	 * @return consumer who successfully log in
	 * @throws IOException
	 * @throws HeadlessException
	 * @throws ClassNotFoundException  
	 */  
	public static consumer userLogin(JTextField jtf , JPasswordField jpf) throws IOException, HeadlessException, ClassNotFoundException{
	    	String name=jtf.getText();
			String pass=String.valueOf(jpf.getPassword());	
			consumer conbuf=consumerDao.readData(name);
			if(name.isEmpty()||pass.isEmpty()){
		    	   JOptionPane.showMessageDialog(null,"Empty input£¡","Tips",JOptionPane.WARNING_MESSAGE);
		    }
			 
			 else if(conbuf!=null&&conbuf.getPassword().equals(pass)) {
				 JOptionPane.showMessageDialog(null,"Successfully login£¡","Tips",JOptionPane.WARNING_MESSAGE); 
				 return conbuf;
			 }
			return null;	 	    
	    }
		/**  
		 * <p>Title: userRegister</p>  
		 * <p>Description: New user register the system </p>  
		 * @param jtf the text field to enter user name
		 * @param jpf the password field to enter user password
		 * @return
		 * @throws IOException
		 * @throws HeadlessException
		 * @throws ClassNotFoundException  
		 */  
		public static int userRegister(JTextField jtf , JPasswordField jpf) throws IOException, HeadlessException, ClassNotFoundException{		
			String name=jtf.getText();
			String pass=String.valueOf(jpf.getPassword());	
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("TimesRoman", Font.PLAIN, 30)));
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("TimesRoman", Font.PLAIN, 30)));
	        if(name.isEmpty()||pass.isEmpty()){
	    	   JOptionPane.showMessageDialog(null,"Empty input£¡","Tips",JOptionPane.WARNING_MESSAGE);
	    	   return 0;
	        }
	        else if(consumerDao.readData(name)!=null){
				JOptionPane.showMessageDialog(null,"User name has existed, pelase enter new one!","Tips",JOptionPane.WARNING_MESSAGE); 
				return 0;
			}
	        else {
	        	consumer newcus=new consumer(name,pass);
	        	if(consumerDao.readData(name)==null&&consumerDao.saveData(newcus)==1){
	    			Provider Probuf=ProviderDao.readData();
	    			Probuf.addConsumer(newcus);
	    			ProviderDao.saveData(Probuf);
	    			JOptionPane.showMessageDialog(null,"Successfully register£¡","Tips",JOptionPane.WARNING_MESSAGE);           
	                return 1;
	                
	    		}
	        }		
			return 0;
		}
		/**  
		 * <p>Title: AdminLogin</p>  
		 * <p>Description: Administrator log in the system</p>  
		 * @param jtf the text field to enter user name
		 * @param jpf the password field to enter user password
		 * @return
		 * @throws IOException
		 * @throws ClassNotFoundException  
		 */  
		public static Provider AdminLogin(JTextField jtf , JPasswordField jpf) throws IOException, ClassNotFoundException{	
			String name=jtf.getText();
			String pass=String.valueOf(jpf.getPassword());	
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("TimesRoman", Font.PLAIN, 30)));
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("TimesRoman", Font.PLAIN, 30)));
			Provider Probuf=ProviderDao.readData();
			if(name.isEmpty()||pass.isEmpty())
		    	   JOptionPane.showMessageDialog(null,"Empty input£¡","Tips",JOptionPane.WARNING_MESSAGE);
			else if(Probuf.getPassword().equals(pass)) {
				 JOptionPane.showMessageDialog(null,"Successfully login£¡","Tips",JOptionPane.WARNING_MESSAGE); 
				 return Probuf;
			 } 
			return null;
	    }
}
