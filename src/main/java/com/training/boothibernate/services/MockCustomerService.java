package com.training.boothibernate.services;

import com.training.boothibernate.entity.Customer;

public class MockCustomerService {
	
	public Customer getCustomerById(int id) {
		Customer customer = null;
		if(id > 0 && id < 10) {
			customer = new Customer();
			customer.setId(id);
			customer.setFirstName("Dummy Name");
			customer.setLastName("Dummy Last");
			customer.setEmail("dummy@mail.com");
			
		}
		return customer;
	}
}
