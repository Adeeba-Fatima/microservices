package com.training.boothibernate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.boothibernate.entity.Customer;
import com.training.boothibernate.entity.CustomerErrorResponse;
import com.training.boothibernate.exceptions.CustomerException;
import com.training.boothibernate.exceptions.CustomerNotFoundException;
import com.training.boothibernate.services.CustomerService;

@RestController
@RequestMapping("/api")
//decide upon which rest client to serve
@CrossOrigin(origins="http://localhost:4200")
public class CustomerRestController {
	
	//dependency over CustomerService
	//add dependency
	//@Autowire for DI
	@Autowired
	private CustomerService customerService;
	
	//add 5 service methods to expose REST Service
	
	//add GET mapping for /api/customers
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		List<Customer> customers=this.customerService.getAll();
		
		if(customers==null || customers.isEmpty()) {
			throw new CustomerNotFoundException("No Customer Records not Found!!!");
		}
		return customers;
	}
	
	
	//add GET mapping for /api/customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable Long customerId){
		Customer customer=this.customerService.getById(customerId);
		
		if(customer==null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		return customer;
	}
	
	
	
	//going to recieve json object from client
	//converted object shall be received as argument in method
	//add POST mapping for /api/customers
	//in success : return back same customer instance
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer){
		
		if(!this.customerService.add(customer)) {
			throw new CustomerException("Cannot add new Customer!!!");
		}
		return customer;
	}
	
	//going to recieve json object from client
	//converted object shall be received as argument in method
	//add PUT mapping for /api/customers
	//in success : return back same customer instance
	@PutMapping("/customers")
	public Customer editCustomer(@RequestBody Customer customer){
		
		if(!this.customerService.update(customer)) {
			throw new CustomerException("Cannot update Customer data!!!");
		}
		return customer;
	}
	
	//add DELETE mapping for /api/customers/{customerId}
	//in success : return back same customer instance (which is being deleted)
	@DeleteMapping("/customers/{customerId}")
	public Customer deleteCustomer(@PathVariable Long customerId){
		//check if id is valid
		Customer customer=this.customerService.getById(customerId);
		
		if(customer==null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		//check if deletion is successful
		if(!this.customerService.delete(customerId)) {
			throw new CustomerException("Cannot delete Customer data with id - " + customerId);
		}
		
		//success
		return customer;
	}
	
	// ResponseEntity :a wrapper representing HTTP Object
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex){
		CustomerErrorResponse errResponse=new CustomerErrorResponse(
												HttpStatus.NOT_FOUND.value(),
												ex.getMessage(),
												System.currentTimeMillis());
		return new ResponseEntity<>(errResponse,HttpStatus.NOT_FOUND);
	}
	
	
	

}
