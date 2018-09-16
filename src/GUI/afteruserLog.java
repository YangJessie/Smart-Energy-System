package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

import System.Entity.*;
import System.Control.tableControl;
import System.Constant.constant;
import System.Dao.ProviderDao;
import System.Dao.SendBillDao;
import System.Dao.consumerDao;


/**  
* @className: afteruserLog
* @Description: The page after user log in
* @author Yujie Yang 
* @date 2018-5-26 
*/  
public class afteruserLog extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String TableUtil = null;
	private JLabel name,ID,ele,gas,currentCostE,currentCostG,Elight,Glight,Ebudget,Gbudget;  
	private JRadioButton Day,week,month; 
	private JButton sendUse,changeEleBud,changeGasBud,checkTariff,recvBill,backMain;
	private JPanel jp1,jp3; 
	private Timer Electimer,Gastimer;
	private JTable gasTable,electricTable;
	private String[] columnNames = {"Time","Used"};
	ButtonGroup bg;
	
	public afteruserLog(consumer con) throws ClassNotFoundException, IOException{       
	    //con.setElectricityBudget(1);
	    //con.setGasBudget(1);
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
    	name.setFont(new java.awt.Font("Dialog",0,18));
    	jp1.add(name);
    	
    	ID=new JLabel("User ID:"+con.getId());   	
    	ID.setFont(new java.awt.Font("Dialog",0,18));
		jp1.add(ID);
		
		Ebudget = new JLabel("Electricity Budget:(£)" + con.getElectricityBudget());
		Ebudget.setBounds(29, 10, 150, 13);
		jp3.add(Ebudget);
		
		Gbudget=new JLabel("Gas Budget:(£)" + con.getGasBudget());
		Gbudget.setBounds(366, 10, 150, 13);
		jp3.add(Gbudget);
		
		ele=new JLabel("Updated eletricity usage:(in Kwh)  "+String.format("%04d", con.getElectricityMeter().getUsed())); 
    	ele.setBounds(29,30,220,13);
		jp3.add(ele);
		
        gas=new JLabel("Updated gas usage:(in Kwh)  "+String.format("%04d", con.getGasMeter().getUsed())); 
        gas.setBounds(366,30,200,13);
		jp3.add(gas);	
		
		Elight=new JLabel("  #^_^# ");
		Elight.setOpaque(true);
		Elight.setBounds(29, 62, 63, 13);  
		jp3.add(Elight);
		
		Glight=new JLabel(" #^_^# ");
		Glight.setOpaque(true);
		Glight.setBounds(366, 62, 63, 13);  
		jp3.add(Glight);
		
		currentCostE= new JLabel("Current Electricity Cost:(£)" + con.getElectricityMeter().getUsed()*ProviderDao.readData().getElectricityTariff());
		currentCostE.setBounds(100, 62, 200, 13);
		jp3.add(currentCostE);
		
		
		currentCostG= new JLabel("Curreny Gas Cost:(£)" + con.getGasMeter().getUsed()*ProviderDao.readData().getGasTariff());
		currentCostG.setBounds(440, 62, 200, 13);
		jp3.add(currentCostG);
		
		changeEleBud = new JButton("Change Electricity Meter Budget");
		changeEleBud.setBounds(29, 95, 220, 23);
		jp3.add(changeEleBud);
		
		changeGasBud = new JButton("Change Gas Meter Budget");
		changeGasBud.setBounds(366, 95, 200, 23);
		jp3.add(changeGasBud);
		
        
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
    	
		sendUse=new JButton("Send Monthly consumption");
	    sendUse.setBounds(40, 350, 220, 23);
		jp3.add(sendUse);
		
		checkTariff=new JButton("Check Tariff(price per kWh)");
		checkTariff.setBounds(300, 350, 220, 23);
		jp3.add(checkTariff);
		
		recvBill=new JButton("Receive bill");
		recvBill.setBounds(40, 400, 180, 23);
		jp3.add(recvBill);
		
		backMain=new JButton("Back to Mainpage");
		backMain.setBounds(300, 400, 190, 23);
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
    		    	   try {
						consumer conBuf=consumerDao.readData(con.getName());
						ele.setText("Updated eletricity usage:(in Kwh) "+String.format("%04d", conBuf.getElectricityMeter().getUsed()));
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    		    	   
    		    	   if(eleUp.getAlarm()==1){
    		    		   Elight.setBackground(Color.GREEN);
    		    		   System.out.println("elec Green!");
    		    	   }
    		    	   else if(eleUp.getAlarm()==2){
    		    		   Elight.setText(" ");     
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
    		    	   
					try {
						consumer conBuf = consumerDao.readData(con.getName());
						gas.setText("Updated gas usage:(in Kwh)  "+String.format("%04d", conBuf.getGasMeter().getUsed()));
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    		    	   
    		    	   if(gasUp.getAlarm()==1){
    		    		   Glight.setBackground(Color.GREEN);
    		    		   System.out.println("gas Green!");
    		    	   }
    		    	   else if(eleUp.getAlarm()==2){
    		    		   Glight.setText("  ");
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
    	
    	changeEleBud.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("Please input Budget");
				try {
					con.setElectricityBudget(Integer.parseInt(inputValue));
					synchronized(con) {
						consumerDao.saveData(con);
					}
					if(con.getElectricityMeter().getUsed() > con.getElectricityBudget()) {
						Elight.setBackground(Color.RED);
					}
					else {
						Elight.setText(" #^_^# ");
						Elight.setBackground(Color.GREEN);
					}
					Ebudget.setText("Electricity Budget:" + con.getElectricityBudget());
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Not Integer", "Not Integer", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
        
    	changeGasBud.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("Please input Budget");
				try {
					con.setGasBudget((Integer.parseInt(inputValue)));
					synchronized(con) {
						consumerDao.saveData(con);
					}
					if(con.getGasMeter().getUsed() > con.getGasBudget()) {
						Glight.setBackground(Color.RED);
					}
					else {
						Glight.setBackground(Color.GREEN);
					}
					Gbudget.setText("Gas Budget:" + con.getGasBudget());
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Not Integer", "Not Integer", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
        
    	checkTariff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null,"Electric Tariff:"+ProviderDao.readData().getElectricityTariff()
							+"\n Gas Tariff: "+ProviderDao.readData().getGasTariff()
							,"Tariff",JOptionPane.WARNING_MESSAGE);
				} catch (HeadlessException | ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
    	});
    	sendUse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("User is Sending consumption!");
				try {
					consumerDao.sendData(con);
					JOptionPane.showMessageDialog(null,"The system has Send your monthly consumption to the administrator!","Email Service System",JOptionPane.WARNING_MESSAGE); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
    	recvBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String data = SendBillDao.readBill(con);
					if(data!=null)    new recvBill(con,data);
					else{
						JOptionPane.showMessageDialog(null, "Administrator has not sent bill to you!", "Email service warning", JOptionPane.ERROR_MESSAGE);				
					}				
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
    	
    	backMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new mainUI();
			}
		});
	    	
	    	this.setLayout(new BorderLayout());
	    	this.add("North",jp1);
	    	this.add("Center",jp3);
	    	this.setTitle("Welcome "+con.getName()+"!");
	    	this.setBounds(100,100,700,550);
         	this.setLocation(400,400);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       	this.setResizable(false);
	      	this.setVisible(true);
    	   	
	}
   
}
