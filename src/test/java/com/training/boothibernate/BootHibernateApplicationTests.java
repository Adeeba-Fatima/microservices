package com.training.boothibernate;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.boothibernate.entity.Customer;
import com.training.boothibernate.services.MockCustomerService;
import com.training.boothibernate.services.SimpleCustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootHibernateApplicationTests {

	 // private static SimpleCustomerServiceImpl service;
	 private static MockCustomerService service;
	
	@BeforeClass
	public static void setUp() {
		service = new MockCustomerService();
		
	}
	
	@Test
	public void checkReturnValue() {
		// Customer customer =  service.getCustomerById(20);
		Customer customer =  service.getCustomerById(20);
		assertNotNull(customer);
	}
	
	@Test
	public void checkContents() {
		Customer customer =  service.getCustomerById(2);
		assertEquals("dummy@mail.com", customer.getEmail());
	}
	@Test
	public void checkMoreContents() {
		Customer customer =  service.getCustomerById(2);
		assertTrue(customer.getFirstName().contains("Dummy"));
	}
	
	

}

