/**  
* <p>Title: recvBill.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: www.baidudu.com</p>  
* @author Yujie Yang  
* @date 2018Äê6ÔÂ1ÈÕ  
* @version 1.0  
*/  
package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
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

import System.Dao.SendBillDao;
import System.Entity.consumer;

/**  
* @className: recvBill
* @Description: 
* @author Yujie Yang  
*/
public class recvBill extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel receiver,sender;
	private JTextArea textArea;
	private JPanel jp1,jp2;
	private JButton back;
	public recvBill(consumer con,String data) throws ClassNotFoundException, IOException{
		this.setLayout(new BorderLayout());
		sender=new JLabel("Received from: administrator@energy.monitoring.com");
		sender.setFont(new java.awt.Font("Dialog",0,18));
		receiver=new JLabel("User email address: "+con.getName()+"@energy.monitoring.com");   	
		receiver.setFont(new java.awt.Font("Dialog",0,18));
		
		jp1=new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		jp1.add(sender);
		jp1.add(receiver);
		this.add("North", jp1);
		
		jp2=new JPanel();
		jp2.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setText(data);		
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(10, 20, 550, 300);
		scroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jp2.add(scroll);
		
		back=new JButton("Confirm"); back.setFont(new java.awt.Font("Dialog",1,18));
		back.setBounds(210, 350, 152, 33);	
		jp2.add(back);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new afteruserLog(con);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				});		
			}
		});
		this.add("Center",jp2);
    	this.setTitle(con.getName()+"receive bill");
    	this.setBounds(100,100,640,480);
     	this.setLocation(400,400);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	this.setResizable(false);
      	this.setVisible(true);
      	
	}
}
