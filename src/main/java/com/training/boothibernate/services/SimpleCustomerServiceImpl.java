package com.training.boothibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.training.boothibernate.dao.CustomerDAO;
import com.training.boothibernate.entity.Customer;

@Service
public class SimpleCustomerServiceImpl implements CustomerService {

	//dependent on DAO resources
	//add dependency
	//inject dependency
	@Autowired //field based DI
	//@Qualifier("jdbctemplate")
	@Qualifier("hibernate")
	private CustomerDAO customerDAO;
	
	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return this.customerDAO.getAllCustomers();
	}

	@Override
	public Customer getById(Long id) {
		// TODO Auto-generated method stub
		return this.customerDAO.getCustomerById(id);
	}

	@Override
	public boolean add(Customer customer) {
		// TODO Auto-generated method stub
		return this.customerDAO.addCustomer(customer);
	}

	@Override
	public boolean update(Customer customer) {
		// TODO Auto-generated method stub
		return this.customerDAO.updateCustomer(customer);
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return this.customerDAO.deleteCustomer(id);
	}

}
