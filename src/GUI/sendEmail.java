/**  
* <p>Title: sendEmail.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: www.baidudu.com</p>  
* @author Yujie Yang  
* @date 2018年5月29日  
* @version 1.0  
*/  
package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import System.Dao.ProviderDao;
import System.Dao.SendBillDao;
import System.Dao.consumerDao;
import System.Entity.consumer;

/**  
* <p>Title: sendEmail</p>  
* <p>Description: The page when administrator send bill to user  </p>  
* @author Yujie Yang 
* @date 2018年5月29日  
*/
public class sendEmail extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel theme1,theme2;
	private JTextArea textArea;
	private JPanel jp1,jp2;
	private JTextField jtf; 
	private JButton send;
	/**  
	* <p>Title:sendEmail</p>  
	* <p>Description:constructor of the sendEmail </p>  
	* @param con
	* @throws IOException
	* @throws ClassNotFoundException  
	*/  
	public sendEmail(consumer con) throws IOException, ClassNotFoundException{
		
		this.setLayout(new BorderLayout());
		theme1=new JLabel("Users email address:");   	
		theme1.setFont(new java.awt.Font("Dialog",1,18));
		theme1.setBounds(20,10,200,20);
		jtf=new JTextField(10);
    	jtf.setFont(new java.awt.Font("Dialog",0,18));
    	theme2=new JLabel("@energy.monitoring.com");   	
		theme2.setFont(new java.awt.Font("Dialog",1,18));
		theme2.setBounds(20,10,200,20);
		jp1=new JPanel();
		jp1.add(theme1);jp1.add(jtf);jp1.add(theme2);
		this.add("North", jp1);
		
		jp2=new JPanel();
		jp2.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		String data = SendBillDao.writeBill(con);
		textArea.setText(data);
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(10, 20, 550, 300);
		scroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jp2.add(scroll);
		
		send=new JButton("Send"); send.setFont(new java.awt.Font("Dialog",1,18));
		send.setBounds(210, 350, 152, 33);
		
		jp2.add(send);
		this.add("Center",jp2);
    	this.setTitle("Administrator send email to "+con.getName());
    	this.setBounds(100,100,640,480);
     	this.setLocation(400,400);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	this.setResizable(false);
      	this.setVisible(true);
	
	send.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String name=jtf.getText();
			if(name.isEmpty()){
		    	   JOptionPane.showMessageDialog(null,"Empty input！","Tips",JOptionPane.WARNING_MESSAGE);
		    } else
			try {
				if(name.equals(con.getName())) {	
					JOptionPane.showMessageDialog(null,"The bill will send to user " + name +"@energy.monitoring.com！","Tips",JOptionPane.WARNING_MESSAGE); 
					dispose();					
					SendBillDao.sendData(con, data);
				}
				else{
					JOptionPane.showMessageDialog(null,"Incorrect user name！","Tips",JOptionPane.WARNING_MESSAGE);
					dispose();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    }
	   });	
	}	
}
