package GUI;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import System.Dao.consumerDao;
import System.Entity.consumer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**  
* @className: resetPin</p>  
* @Description: The page for reset password</p>  
* @author Yujie Yang 
* @date 2018Äê5ÔÂ31ÈÕ  
*/  
public class resetPin extends JFrame implements ActionListener {
	JButton OK;  
    JPanel jp1,jp2,jp3,jp4,jp5;  
    JTextField t1; 
    JLabel name,Oldpass,Newpass,ensure;  
    JPasswordField jpf1,jpf2,jpf3;  
    
    public resetPin(){
    	OK=new JButton("Reset"); OK.setFont(new java.awt.Font("Dialog",1,20));
    	OK.addActionListener(this);
    	
    	jp1=new JPanel();
    	jp2=new JPanel();
    	jp3=new JPanel();
    	jp4=new JPanel();
    	jp5=new JPanel();
    	name=new JLabel("User Name:"); 
    	name.setFont(new java.awt.Font("Dialog",1,20));
    	Oldpass=new JLabel("Old Password:"); 
    	Oldpass.setFont(new java.awt.Font("Dialog",1,20));
    	Newpass=new JLabel("New Password:"); 
    	Newpass.setFont(new java.awt.Font("Dialog",1,20));
    	ensure=new JLabel("New Password again:"); 
    	ensure.setFont(new java.awt.Font("Dialog",1,20));
    	
    	t1=new JTextField(10);
    	t1.setFont(new java.awt.Font("Dialog",0,20));
    	jpf1=new JPasswordField(10);
    	jpf1.setFont(new java.awt.Font("Dialog",0,20));
    	jpf2=new JPasswordField(10);
    	jpf2.setFont(new java.awt.Font("Dialog",0,20));
    	jpf3=new JPasswordField(10);
    	jpf3.setFont(new java.awt.Font("Dialog",0,20));
    	
    	jp1.add(name);jp1.add(t1); //enter user name
    	jp2.add(Oldpass);jp2.add(jpf1);
    	jp3.add(Newpass);jp3.add(jpf2);
    	jp4.add(ensure);jp4.add(jpf3);
    	jp5.add(OK);
    	
    	this.add(jp1);
    	this.add(jp2);
    	this.add(jp3);
    	this.add(jp4);
    	this.add(jp5);
    	
    	this.setLayout(new GridLayout(5,1));
    	this.setTitle("User Reset Password");
    	this.setSize(500, 300);
    	this.setLocation(100,100);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	this.setResizable(false);
      	this.setVisible(true);
    }
    /**  
     * <p>Title: checkpass</p>  
     * <p>Description:check if the passwrd is correct </p>  
     * @return
     * @throws ClassNotFoundException
     * @throws IOException  
     */  
    public int checkpass() throws ClassNotFoundException, IOException{
    	String name=t1.getText();
    	String oldp=String.valueOf(jpf1.getPassword());
    	String newp=String.valueOf(jpf2.getPassword());
    	String reCheck=String.valueOf(jpf3.getPassword());
    	if(name.isEmpty()||oldp.isEmpty()||newp.isEmpty()||reCheck.isEmpty()){
    		JOptionPane.showMessageDialog(null,"Empty input£¡","Tips",JOptionPane.WARNING_MESSAGE);
    	    return 0;
    	}
    	consumer cusBuf=consumerDao.readData(name);  
    	if(cusBuf!=null){
    		if(oldp.equals(cusBuf.getPassword())){
        		if(newp.equals(reCheck)){
        			return 1;
        		}
        		else{
        			JOptionPane.showMessageDialog(null,"The password entered did not match£¡","Tips",JOptionPane.WARNING_MESSAGE);
            	    return 0;
        		}
        	}
        	else{
        		JOptionPane.showMessageDialog(null,"Incorrect Password£¡","Tips",JOptionPane.WARNING_MESSAGE);
        	    return 0;
        	}
    	}
    	else{
    		JOptionPane.showMessageDialog(null,"Incorrect User name£¡","Tips",JOptionPane.WARNING_MESSAGE);
    		return 0;
    	}
     }
    /**  
     * <p>Title: clear</p>  
     * <p>Description: clear all the jpasswordfield </p>    
     */  
    public void clear(){  
	    t1.setText("");  
	    jpf1.setText("");  
	    jpf2.setText(""); 
	    jpf3.setText(""); 
    }  
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
    	try {
			if(checkpass()==1){
				JOptionPane.showMessageDialog(null,"Reset password successfully£¡","Tips",JOptionPane.WARNING_MESSAGE);
			    dispose();
			    mainUI welcome=new mainUI();
			}
			else{
				clear();
			}
		} catch (HeadlessException | ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
