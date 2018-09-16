package Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;


import org.junit.Test;

import System.Constant.constant;
import System.Dao.*;
import System.Entity.*;

public class consumerTest {
	//private static final String constant = null;


		//���û����в���
		@Test
		public void consumertest() {
			consumer consumer = new consumer("aaa","111");//ʵ����һ���û�
			consumer.setName("aaa");
			consumer.setGasBudget(10);//����������Ԥ��
			try {
				consumerDao.saveData(consumer);//���ļ���д����
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			consumer readOne = null;//consumer��һ��ʵ��
			try {
				 readOne = consumerDao.readData(consumer.getName());//���ļ��������
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertEquals(readOne.getName(),"aaa");//��֤���ĺ�д���Ƿ�һ��
			assertTrue(readOne.getGasBudget() == 10);
			
			int gasdata =gasUpdaterDao.fakeData();
			Date gasdate = new Date();
			consumer.getGasMeter().addData(gasdate, gasdata);
			
			int elecdata = elecUpdaterDao.fakeData();
			Date elecdate = new Date();
			consumer.getElectricityMeter().addData(elecdate, elecdata);//���ɼ����ݣ���ȡ��������Ȼ���������
			
			try {
				consumerDao.saveData(consumer);
				//consumerDao.sendData(consumer);
				//���ļ���д����
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				readOne = consumerDao.readData(consumer.getName());//���ļ��������
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//���Զ�д�Ƿ�����
					
		}
		
		
		public void sendTest(){
			consumer consumer = new consumer("aaa","111");
			consumer.getElectricityMeter().addData(new Date(), 5);
				
			try {
				//consumerDao.saveData(consumer);
				consumerDao.sendData(consumer);//���ļ���д����
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String uuid = consumer.getId();
			String name = consumer.getName();
			File file = new File(constant.CONSUMER_DATA_FILE_PATH+ name +constant.CONSUMER_SEND_FILE_PATH,name+constant.SEND_FILE_NAME_PREFIX);
		    File directory = new File(constant.CONSUMER_DATA_FILE_PATH+name+constant.CONSUMER_SEND_FILE_PATH);
		    File file_p = new File(constant.PROVIDER_RECEIVE_FILE_PATH,name+constant.RECEIVE_FILE_NAME_PREFIX);
		    File directory_p = new File(constant.PROVIDER_RECEIVE_FILE_PATH);
		    System.out.println("--");
			assertTrue(file.exists());
			assertTrue(directory.exists());
			assertTrue(file_p.exists());
			assertTrue(directory_p.exists());
			
		}

	}