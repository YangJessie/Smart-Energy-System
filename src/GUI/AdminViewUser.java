/**  
* <p>Title: AdminViewUser.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: www.baidudu.com</p>  
* @author Yujie Yang  
* @date 2018-5-26 
* @version 1.0  
*/  
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import System.Constant.constant;
import System.Control.tableControl;
import System.Dao.ProviderDao;
import System.Dao.consumerDao;
import System.Entity.Record;
import System.Entity.consumer;
import System.Entity.elecUpdater;
import System.Entity.gasUpdater;
import System.Util.TimeUtil;


/**  
* @className: AdminViewUser
* @Description:Page when administrator wants to see users detail 
* @author Yujie Yang  
*/  
public class AdminViewUser extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel name,ID,ele,gas,Elight,Glight,Ebudget,Gbudget;  
	private JRadioButton Day,week,month; 
	private JButton sendBill,backMain;
	private JPanel jp1,jp3; 
	private Timer Electimer,Gastimer;
	private JTable gasTable,electricTable;
	private String[] columnNames = {"Time","Used"};
	ButtonGroup bg;
	
	public AdminViewUser(consumer con){       
	    con.setElectricityBudget(1);
	    con.setGasBudget(1);
	    elecUpdater eleUp = new elecUpdater(con); 
	    gasUpdater gasUp= new gasUpdater(con);
	    
		eleUp.startUpdate();  
		gasUp.startUpdate();
		jp1=new JPanel();
    	jp3=new JPanel();
    	
    	jp1.setLayout(new GridLayout(2,1));     
        jp3.setLayout(null);
        //jp4.setLayout(new BorderLayout());
        
    	name=new JLabel("User Name:"+con.getName());   	
    	name.setFont(new java.awt.Font("Dialog",1,20));
    	jp1.add(name);
    	
    	ID=new JLabel("User ID:"+con.getId());   	
    	ID.setFont(new java.awt.Font("Dialog",1,20));
		jp1.add(ID);
		
		ele=new JLabel("Updated eletricity usage:  "+con.getElectricityMeter().getUsed()); 
    	ele.setBounds(29,30,200,13);
		jp3.add(ele);
		
        gas=new JLabel("Updated gas usage:  "+con.getGasMeter().getUsed()); 
        gas.setBounds(366,30,183,13);
		jp3.add(gas);
		
		Ebudget = new JLabel("Electricity Budget:" + con.getElectricityBudget());
		Ebudget.setBounds(110, 62, 150, 13);
		jp3.add(Ebudget);
		
		Gbudget=new JLabel("Gas Budget:" + con.getGasBudget());
		Gbudget.setBounds(440, 62, 150, 13);
		jp3.add(Gbudget);
		
		Elight=new JLabel("  #^_^# ");
		Elight.setOpaque(true);
		Elight.setBounds(29, 62, 63, 13);  
		jp3.add(Elight);
		
		Glight=new JLabel(" #^_^# ");
		Glight.setOpaque(true);
		Glight.setBounds(366, 62, 63, 13);  
		jp3.add(Glight);		
        
		Day=new JRadioButton("Daily");
		Day.setBounds(44, 320, 80, 23);
		jp3.add(Day);
		
		week=new JRadioButton("weekly");
		week.setBounds(240, 320, 80, 23);
		jp3.add(week);
		
		month = new JRadioButton("Monthly");
		month.setBounds(420, 320, 80, 23);
		jp3.add(month);
		
		bg=new ButtonGroup();
    	bg.add(Day);
    	bg.add(week);
    	bg.add(month);
    	//Day.setSelected(true);
    	
		sendBill=new JButton("Send electricity and gas bill of this month");
	    sendBill.setBounds(160, 350, 300, 23);
		jp3.add(sendBill);
		 
		backMain=new JButton("Back to Mainpage");
		backMain.setBounds(200, 380, 190, 23);
		jp3.add(backMain);
		
		electricTable = new JTable(new Object[1][2],columnNames);
		electricTable.setEnabled(false);
		electricTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane JSPelec= new JScrollPane(electricTable);
		JSPelec.setBounds(30, 150, 280, 160);
		jp3.add(JSPelec);
		
		gasTable = new JTable(new Object[1][2],columnNames);
		gasTable.setEnabled(false);
		gasTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane JSPgas= new JScrollPane(gasTable);
		JSPgas.setBounds(330, 150, 280, 160);
		jp3.add(JSPgas);
		
    	Electimer=new Timer(1000,
    			new ActionListener(){
    		       public void actionPerformed(ActionEvent e){ 		    	   
    		    	   ele.setText("Updated eletricity usage:   "+con.getElectricityMeter().getUsed());
    		    	   if(eleUp.getAlarm()==1){
    		    		   Elight.setBackground(Color.GREEN);
    		    		   System.out.println("elec Green!");
    		    	   }
    		    	   else if(eleUp.getAlarm()==2){
    		    		   Elight.setText(" (กัoกั)");
    		    		   Elight.setBackground(Color.RED);
    		    		   System.out.println("elec Red!");
    		    	   }
    		    	       else {
    		    	    	   Elight.setText("Sorry, you have not set usage budget!");
    	    		    	   }							 			     	 			   	    	       
		    		}	
    	});
    	
    	Gastimer=new Timer(1000,
    			new ActionListener(){
    		       public void actionPerformed(ActionEvent e){
    		    	   gas.setText("Updated gas usage:   "+con.getGasMeter().getUsed());
    		    	   if(gasUp.getAlarm()==1){
    		    		   Glight.setBackground(Color.GREEN);
    		    		   System.out.println("gas Green!");
    		    	   }
    		    	   else if(eleUp.getAlarm()==2){
    		    		   Glight.setText(" (กัoกั)");
    		    		   Glight.setBackground(Color.RED);
    		    		   System.out.println("gas Red!");
    		    	   }
    		    	       else {
    		    	    	   Glight.setText("Sorry, you have not set usage budget!");
    		    	      	   }						    	 			     	 	
		    		}				
			});
    	Electimer.start();
    	Gastimer.start();
    	
    	Day.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Record> gas = con.getGasMeter().getTodayHistory();
				gasTable.setModel(new DefaultTableModel(tableControl.getDayData(gas),constant.DAY_COL));
				gasTable.repaint();
				
				
				List<Record> elec = con.getElectricityMeter().getTodayHistory();
				electricTable.setModel(new DefaultTableModel(tableControl.getDayData(elec),constant.DAY_COL));
				electricTable.repaint();
			}
		});
    	
    	week.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Record> temp = con.getGasMeter().getWeekHistory();
				try {
					gasTable.setModel(new DefaultTableModel(tableControl.getWeekData(temp, con, constant.GAS_TYPE),constant.WEEK_COL));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gasTable.repaint();

				List<Record> temp1 = con.getElectricityMeter().getWeekHistory();
				try {
					electricTable.setModel(new DefaultTableModel(tableControl.getWeekData(temp1, con, constant.ElE_TYPE),constant.WEEK_COL));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				electricTable.repaint();
			}
		});
    	
    	month.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Record> temp = con.getGasMeter().getMonHistory();
				try {
					gasTable.setModel(new DefaultTableModel(tableControl.getMonthData(temp, con, constant.GAS_TYPE),constant.MONTH_COL));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gasTable.repaint();

				List<Record> temp1 = con.getElectricityMeter().getMonHistory();
				try {
					electricTable.setModel(new DefaultTableModel(tableControl.getMonthData(temp, con, constant.ElE_TYPE),constant.MONTH_COL));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				electricTable.repaint();
			}
		});
          
    	sendBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new sendEmail(con);					 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
    	backMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new afterAdmiLog(ProviderDao.readData());
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
		    }
    	});
				
	    	this.setLayout(new BorderLayout());
	    	this.add("North",jp1);
	    	this.add("Center",jp3);
	    	this.setTitle("Welcome "+con.getName()+"!");
	    	this.setBounds(100,100,650,520);
         	this.setLocation(400,400);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       	this.setResizable(false);
	      	this.setVisible(true);
    	   	
	}
}
