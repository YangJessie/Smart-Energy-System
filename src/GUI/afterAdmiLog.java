/**  
* <p>Title: afterAdmiLog.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: www.baidudu.com</p>  
* @author Yujie Yang  
* @version 1.0  
*/  
package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import System.Entity.Provider;
import System.Entity.consumer;
import System.Dao.ProviderDao;
import System.Dao.consumerDao;


/**  
* @className: afterAdmiLog 
* @Description:The page presents after administrator log in
* @author Yujie Yang 
*/  
public class afterAdmiLog extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable Consumertable;
	private String[] columnNames = {"ID","Name","",""};
    
	public afterAdmiLog(Provider admin){
		this.setBounds(100, 100, 640, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("Smart energy management system");
		
		JLabel AdminName = new JLabel(admin.getName());
		AdminName.setBounds(287, 26, 180, 20);
		AdminName.setFont(new java.awt.Font("Dialog",1,20));
		this.getContentPane().add(AdminName);
		
		JLabel lblGasPrice = new JLabel("Gas Tariff:(£)" + admin.getGasTariff());
		lblGasPrice.setBounds(33, 98, 101, 13);
		this.getContentPane().add(lblGasPrice);
		
		JLabel lblElectricPrice = new JLabel("Electric Tariff:(£)" + admin.getElectricityTariff());
		lblElectricPrice.setBounds(350, 98, 154, 13);
		this.getContentPane().add(lblElectricPrice);
		
		JButton btnGasChange = new JButton("Edit gas Tariff");
		btnGasChange.setBounds(33, 138, 120, 23);
		this.getContentPane().add(btnGasChange);
		
		JButton btnElectricChange= new JButton("Edit Electricity Tariff");
		btnElectricChange.setBounds(350, 138, 150, 23);
		this.getContentPane().add(btnElectricChange);

		JLabel lblConsumerTable = new JLabel("Consumer Table:");
		lblConsumerTable.setBounds(35, 205, 124, 13);
		this.getContentPane().add(lblConsumerTable);
		
		Consumertable = new JTable();
		Consumertable.setEnabled(false);
		JScrollPane JSP= new JScrollPane(Consumertable);
		JSP.setBounds(35, 220, 570, 150);
		this.getContentPane().add(JSP);
		
		JButton backMain=new JButton("Back to Mainpage");
		backMain.setBounds(220, 380, 190, 23);
		this.getContentPane().add(backMain);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.setBounds(501, 190, 104, 23);
		this.getContentPane().add(btnAddNew);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(400, 190, 90, 23);
		this.getContentPane().add(btnRefresh);
		btnGasChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("Please Input Tariff");
				try {
					admin.setGasTariff((Double.parseDouble(inputValue)));
					synchronized(admin) {
						ProviderDao.saveData(admin);
					}
					lblGasPrice.setText("Gas Price:" + admin.getGasTariff());
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Not Double", "Not Double", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		btnElectricChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("Please Input Tariff");
				try {
					admin.setGasTariff((Double.parseDouble(inputValue)));
					synchronized(admin) {
						ProviderDao.saveData(admin);
					}
					lblElectricPrice.setText("Electric Price:" + admin.getElectricityTariff());
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Not Double", "Not Double", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		btnAddNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Name = JOptionPane.showInputDialog("Please Input Name");
				if(Name != null && !Name.equals("")) {
					String Password = JOptionPane.showInputDialog("Please Input Password");
					if(Password != null && !Password.equals("")) {
						try {
							admin.generateNewConsumer(Name, Password);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						synchronized(admin) {
							try {
								ProviderDao.saveData(admin);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Cannot be null", "Cannot be null", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Cannot be null", "Cannot be null", JOptionPane.ERROR_MESSAGE);
				}
				refreshData(admin);
			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshData(admin);
			}
		});
		
		Consumertable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
                   int col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); 
                   int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());
                   consumer conbuf=admin.getConsumerList().get(row);
                   if(col == 2) {              	   
                	   String uname = conbuf.getName();
                	   try {
						 admin.removeConsumer(uname);
					   } catch (IOException e1) {
						// TODO Auto-generated catch block
						 e1.printStackTrace();
					     }              	   
                   }
                   else if (col ==3) {
                	   System.out.println("view");
                	   if(conbuf!=null){
   						dispose();
   						EventQueue.invokeLater(new Runnable() {
   							public void run() {
   								try {
   						              new AdminViewUser(conbuf);
   								}catch(Exception e) {
   									e.printStackTrace();
   								}
   							}
   						});   								
                   }
				}
                   refreshData(admin);
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
		
		this.setLocation(800,400);
		this.setVisible(true);
	}
	private void refreshData(Provider p) {
		List<consumer> temp = p.getConsumerList();
		for(int i =0 ; i< temp.size();i++) {
			try {
				temp.set(i, consumerDao.readData(temp.get(i).getName()));
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		synchronized(p) {
			try {
				ProviderDao.saveData(p);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		Object[][] data = new Object[temp.size()][4];
		for(int i = 0; i< temp.size();i++) {
			data[i][0] = temp.get(i).getId();
			data[i][1] = temp.get(i).getName();
			data[i][2] = "delete";
			data[i][3] = "view";
		}
		Consumertable.setModel(new DefaultTableModel(data,columnNames));
		Consumertable.repaint();
	}
	public class TableRender extends DefaultTableCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        if (row<0||column!=1)
	            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        else {
	            JButton label = new JButton("button");
	            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
	            return label;
	        }       
	    }
	}
}
