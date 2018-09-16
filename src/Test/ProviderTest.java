package Test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


import System.Dao.*;
import System.Entity.*;
public class ProviderTest {

	@Test
	public void Daotest() throws ClassNotFoundException, IOException {
		Provider provider = new Provider("Admin","111");
		provider.setElectricityTariff(22.00);
		try {
			provider.generateNewConsumer("Nate", "qwe123");
			provider.generateNewConsumer("Tom", "abd123");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ProviderDao.saveData(provider);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Provider readOne = null;
		try {
			readOne = ProviderDao.readData();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		provider.addConsumer(consumerDao.readData(readOne.getConsumerList().get(0).getId()));
		assertTrue(readOne.getConsumerList().get(0).getName().equals("Nate"));
		assertTrue(readOne.getConsumerList().get(0).getPassword().equals("qwe123"));
		assertTrue(readOne.getElectricityTariff()==22.00);
		System.out.println(readOne.getConsumerList().get(0).getId());

	}
	
	public ProviderTest() {
		Provider provider = new Provider("Admin","111");
		assertEquals(provider.getName(),"Admin");
		provider.setElectricityTariff(20.00);
		provider.setGasTariff(10.00);
		
		try {
			ProviderDao.saveData(provider);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
