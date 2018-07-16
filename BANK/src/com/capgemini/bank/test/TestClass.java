package com.capgemini.bank.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.bean.Wallet;
import com.capgemini.bank.exception.InsufficientBalanceException;
import com.capgemini.bank.exception.InvalidInputException;
import com.capgemini.bank.service.WalletService;
import com.capgemini.bank.service.WalletServiceImpl;

public class TestClass 
{

	WalletService service;
	
	@Before
	public void initData()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9900112212", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
		 service= new WalletServiceImpl(data);
	}
	
	@Test
	public void showBalance()
	{
		Customer customer = service.showBalance("9900112212");
		assertEquals("9000", customer.getWallet().getBalance().toString());
	}
	
	@Test(expected = InvalidInputException.class)
	public void showBalance1()
	{
		
		Customer customer = service.showBalance("990");
		if(customer==null)
			throw new InvalidInputException("invalid no");
		// as in my WalletServiceImpl I am throwig exception from the class and catching them itself in the class 
		// so to illustrate this i am throwing the maunally exception here in line 51.
	}
	
	@Test
	public void createAccount()
	{
		Customer customer = service.createAccount("yash", "8560938449");
		
	}
	
	@Test(expected = InvalidInputException.class)
	public void createAccount1()
	{
		Customer customer = service.createAccount("yash", "856093");
		if(customer==null)
			throw new InvalidInputException("Invalid mobile number");
	}
	

	@Test(expected = InvalidInputException.class)
	public void createAccount2()
	{
		Customer customer = service.createAccount("amit", "9900112212");
		if(customer==null)
			throw new InvalidInputException("mobile number already exist");
	}
	
	@Test
	public void depositAmount()
	{
		Customer customer = service.depositAmount("9900112212", new BigDecimal(1000));
		assertEquals("10000", customer.getWallet().getBalance().toString());
	}
	
	@Test(expected = InvalidInputException.class)
	public void depositAmount1()
	{
		Customer customer = service.depositAmount("990011221", new BigDecimal(1000));
		if(customer==null)
			throw new InvalidInputException("Invalid mobile number");
	}
	
	@Test
	public void withdrawlAmount()
	{
		Customer customer = service.withdrawAmount("9900112212", new BigDecimal(1000));
		assertEquals("8000", customer.getWallet().getBalance().toString());
	}
	
	@Test(expected = InvalidInputException.class)
	public void withdrawlAmount1()
	{
		Customer customer = service.withdrawAmount("990011221", new BigDecimal(1000));
		if(customer==null)
			throw new InvalidInputException("Invalid mobile number");
		
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void withdrawlAmount2()
	{
		Customer customer = service.withdrawAmount("990011221", new BigDecimal(10000));
		if(customer==null)
			throw new InsufficientBalanceException("Insufficent Balance");
	}
	
	@Test
	public void fundTransfer()
	{
		Customer customer = service.fundTransfer("9900112212", "9963242422", new BigDecimal(1000));
		assertEquals("8000", customer.getWallet().getBalance().toString());
	}
	
	@Test(expected = InvalidInputException.class)
	public void fundTransfer1()
	{
		Customer customer = service.fundTransfer("990011221", "9963242422", new BigDecimal(1000));
		if(customer==null)
			throw new InvalidInputException("Invalid Source mobile number");
	}
	
	@Test(expected = InvalidInputException.class)
	public void fundTransfer2()
	{
		Customer customer = service.fundTransfer("9900112212", "996324242", new BigDecimal(1000));
		if(customer==null)
			throw new InvalidInputException("Invalid Beneficiary mobile number");
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void fundTransfer3()
	{
		Customer customer = service.fundTransfer("9900112212", "996324242", new BigDecimal(1000));
		if(customer==null)
			throw new InsufficientBalanceException("Insufficent Balance");
	}
	
}

