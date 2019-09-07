package com.training.boothibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.training.boothibernate.entity.Customer;

@Repository("hibernate")
public class CustomerDAOHibernateImpl implements CustomerDAO {

	//add dependency injection
	@Autowired
	private EntityManager entityManager;
	//private SessionFactory sessionFactory; //no more injected (replace by EntityManager) 
	
	@Override
	@Transactional //add support for transactions
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		
		//fetch the session instance from SessionFactory
		//Session session= this.sessionFactory.getCurrentSession();//fetch from entityManager
		Session session= this.entityManager.unwrap(Session.class);
		
		//HQL 
		//return mysql instruction encapsulated as Query object
		//if data retrieval : good practice to type bound
		Query<Customer> query= session.createQuery("from Customer",Customer.class); //OO : Customer
		List<Customer> customers=query.getResultList();
		
		return customers;
	}

	@Override
	@Transactional //add support for transactions
	public Customer getCustomerById(Long id) {
		// TODO Auto-generated method stub
		Session session= this.entityManager.unwrap(Session.class);
		Customer customer= session.get(Customer.class, id);
		return customer;
	}

	@Override
	@Transactional //add support for transactions
	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session session= this.entityManager.unwrap(Session.class);
		//session.save(customer);
		//performs both operation
		//if id is 0 or null : save
		//otherwise : update on that id
		session.saveOrUpdate(customer);
		return true;
	}

	@Override
	@Transactional //add support for transactions
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session session= this.entityManager.unwrap(Session.class);
		//session.update(customer);
		session.saveOrUpdate(customer);
		return true;
	}

	@Override
	@Transactional //add support for transactions
	public boolean deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		Session session= this.entityManager.unwrap(Session.class);
		
		//session.delete(this.getCustomerById(id));
		
		//HQL
		Query query= session.createQuery("delete from Customer where id=:customerId");
		//set value placeholder
		query.setParameter("customerId", id);
		
		int n=query.executeUpdate();
		
		
		return n>0;
	}

}
