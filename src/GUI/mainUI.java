package GUI;

import javax.swing.*;

import System.Control.checkLogin;
import System.Entity.Provider;
import System.Entity.consumer;
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.IOException;

/**  
* <p>Title: mainUI</p>  
* <p>Description: The main page of the energy management and monitoring system </p>  
* @author Yujie Yang 
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class mainUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton reg,login,changepin;  
    JRadioButton user,admin;  
    JPanel jp1,jp2,jp3,jp4;  
    JTextField jtf;  
    JLabel name,pass,auth;  
    JPasswordField jpf;  
    ButtonGroup bg; 
    checkLogin Mainlogin;
   	
    /**  
    * <p>Title:mainUI</p>  
    * <p>Description:constructor of the mainUI </p>    
    */  
    public mainUI(){
    	
    	reg=new JButton("Register"); reg.setFont(new java.awt.Font("Dialog",1,20));
    	login=new JButton("Login"); login.setFont(new java.awt.Font("Dialog",1,20));
    	changepin=new JButton("Reset Password"); changepin.setFont(new java.awt.Font("Dialog",1,20));
    	user=new JRadioButton("User"); user.setFont(new java.awt.Font("Dialog",1,20));
    	admin=new JRadioButton("Administrator"); admin.setFont(new java.awt.Font("Dialog",1,20));
    	
    	reg.addActionListener(this);
    	login.addActionListener(this);
    	changepin.addActionListener(this);
    	bg=new ButtonGroup();
    	bg.add(user);
    	bg.add(admin);
    	bg.add(changepin);
    	user.setSelected(true);  //default choose user
    	
    	jp1=new JPanel();
    	jp2=new JPanel();
    	jp3=new JPanel();
    	jp4=new JPanel();
    	    	
    	name=new JLabel("user name:"); name.setFont(new java.awt.Font("Dialog",1,20));
    	pass=new JLabel("password:");  pass.setFont(new java.awt.Font("Dialog",1,20));
    	auth=new JLabel("Authentication:");  auth.setFont(new java.awt.Font("Dialog",1,20));
    	
    	jtf=new JTextField(10);
    	jtf.setFont(new java.awt.Font("Dialog",0,20));
    	jpf=new JPasswordField(10);
    	jpf.setFont(new java.awt.Font("Dialog",0,20));
    	
    	jp1.add(name);jp1.add(jtf); //enter user name
    	jp2.add(pass);jp2.add(jpf); //enter password
    	jp3.add(auth);jp3.add(user);jp3.add(admin);  //choose user or administrator
    	jp4.add(reg);jp4.add(login);jp4.add(changepin);//two buttons
    	
    	this.add(jp3);
    	this.add(jp1);
    	this.add(jp2);
       	this.add(jp4);
    	
    	this.setLayout(new GridLayout(4,1));
    	this.setTitle("Energy Management/Monitor System");
    	this.setSize(500, 300);
    	this.setLocation(400,400);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	this.setResizable(false);
      	this.setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="Login"){
			if(user.isSelected()){   //user login
				try {
					consumer conbuf=Mainlogin.userLogin(jtf,jpf);
					if(conbuf!=null){
						dispose();
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
						              new afteruserLog(conbuf);
								}catch(Exception e) {
									e.printStackTrace();
								}
							}
						});
								
						
					}
				} catch (HeadlessException | ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(admin.isSelected()){     
				try {
					Provider Admibuf = Mainlogin.AdminLogin(jtf,jpf);
					if(Admibuf!=null){
						dispose();
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									new afterAdmiLog(Admibuf);
								}catch(Exception e) {
									e.printStackTrace();
								}
							}
						});								
					}
         	   }catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			    }			
	      }
		}
		else if(e.getActionCommand()=="Register"){
			if(user.isSelected()){   //user register 
				try {
					 Mainlogin.userRegister(jtf,jpf); 
					 clear();
					 
				} catch (IOException | HeadlessException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(admin.isSelected()){   //administrator cannot register 
				admin.setSelected(false);
			}
		}
		else if(e.getActionCommand()=="Reset Password"){			
			dispose();
			resetPin resetpassword=new resetPin();
		}
    }
	public void clear(){  
	    jtf.setText("");  
	    jpf.setText("");  
    }
	
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        mainUI welcome=new mainUI();       
	}
}