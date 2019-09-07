package com.training.boothibernate.services;

import java.util.List;

import com.training.boothibernate.entity.Customer;

public interface CustomerService {
	List<Customer> getAll();
	Customer getById(Long id);
	boolean add(Customer customer);
	boolean update(Customer customer);
	boolean delete(Long id);
}