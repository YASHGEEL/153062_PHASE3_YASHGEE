package com.capgemini.bank.dao;
 

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.bean.Wallet;




public class WalletRepoImpl implements WalletRepo{

	private Map<String, Customer> data = new HashMap<String, Customer>(); 
	
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		
		this.data = data;
	}

	public WalletRepoImpl() {
		// TODO Auto-generated constructor stub
	}

	public Customer findOne(String mobileNo) 
	{
		
		Customer customer = new Customer();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANK");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		customer=em.find(Customer.class, mobileNo);
		if(customer==null)
			return null;
		else
			return customer;
		
	}

	@Override
	public Customer createAccount(String name, String mobileNo) 
	{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANK");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer customer = new Customer(name, mobileNo, new Wallet(new BigDecimal(0)));
		em.persist(customer);
		tx.commit();
		em.close();
		return customer;
		
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount)
	{
		Customer customer = new Customer();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANK");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		customer=em.find(Customer.class, mobileNo);
		BigDecimal tempBalance = customer.getWallet().getBalance();
		BigDecimal newBalance = tempBalance.add(amount);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(newBalance);
		customer.setWallet(wallet);
		em.close();
		EntityManager em1 = emf.createEntityManager();
		Customer cust = (Customer) em1.merge(customer);
		em1.persist(cust);
		tx.commit();
		em1.close();
		return cust;
	}

	@Override
	public Customer withdrawlAmount(String mobileNo, BigDecimal amount) 
	{
	
		Customer customer = new Customer();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANK");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		customer=em.find(Customer.class, mobileNo);
		BigDecimal tempBalance = customer.getWallet().getBalance();
		BigDecimal newBalance = tempBalance.subtract(amount);
		int check = Integer.parseInt(newBalance.toString());
		if(check<=0)
		{
			return null;
		}
		Wallet wallet = customer.getWallet();
		wallet.setBalance(newBalance);
		customer.setWallet(wallet);
		em.close();
		EntityManager em1 = emf.createEntityManager();
		Customer cust = (Customer) em1.merge(customer);
		em1.persist(cust);
		tx.commit();
		em1.close();
		return cust;
	}
}
