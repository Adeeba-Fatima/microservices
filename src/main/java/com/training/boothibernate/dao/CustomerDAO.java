package com.training.boothibernate.dao;

import java.util.List;

import com.training.boothibernate.entity.Customer;

public interface CustomerDAO {
	//in consistency with REST API 
	
	List<Customer> getAllCustomers();
	Customer getCustomerById(Long id);
	boolean addCustomer(Customer customer);
	boolean updateCustomer(Customer customer);
	boolean deleteCustomer(Long id);
}
