package com.capgemini.bank.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.bean.Wallet;
import com.capgemini.bank.dao.WalletRepo;
import com.capgemini.bank.dao.WalletRepoImpl;
import com.capgemini.bank.exception.InsufficientBalanceException;
import com.capgemini.bank.exception.InvalidInputException;



public class WalletServiceImpl implements WalletService{

	private WalletRepo repo;
	private Map<String, Customer> map = new HashMap<String,Customer>();
	
	public boolean validateMobile(String mobile)
	{
		try
		{
			String pattern = "[1-9][0-9]{9}";
			if(mobile.matches(pattern))
				return true;
			else
				throw new InvalidInputException("Invalid Mobile Number");
		}
		catch(InvalidInputException exp)
		{
			exp.getMessage();
			return false;
		}
	}
	
	public WalletServiceImpl(Map<String, Customer> data)
	{
		repo= new WalletRepoImpl(data);
	}
	
	public WalletServiceImpl(WalletRepo repo)
	{
		super();
		this.repo = repo;
	}

	public WalletServiceImpl()
	{
		 repo = new WalletRepoImpl();
	}

	public Customer createAccount(String name, String mobileNo)
	{
		if(validateMobile(mobileNo))
		{
			Customer customer=repo.findOne(mobileNo);
			try
			{
				if(customer==null)
					return repo.createAccount(name, mobileNo);
				else
					throw new InvalidInputException("Mobile number already exist.");
			}
			catch(InvalidInputException exp)
			{
				exp.getMessage();
				return null;
			}
		}
		else
		{
			return null;
		}
		
				
	}

	public Customer showBalance(String mobileNo) 
	{
		Customer customer=repo.findOne(mobileNo);
			try
			{
				if(customer!=null)
					return customer;
				else
					throw new InvalidInputException("Invalid mobile no");
			}
			catch(InvalidInputException exp)
			{
				exp.getMessage();
				return null;
			}
		
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) 
	{
		
		Customer customer=repo.findOne(sourceMobileNo);
		try{
			if(customer!=null)
				{
					
					customer = repo.findOne(targetMobileNo);
					try
					{
					 if(customer!=null)
					 {
						 Customer customer1 = repo.withdrawlAmount(sourceMobileNo,amount);
						 if(customer1!=null)
							{
								 repo.depositAmount(targetMobileNo,amount);
								 return customer1;
							}
							else
								throw new InsufficientBalanceException("Insufficent amount");
						
					 }
					 else
						 throw new InvalidInputException("Invalid Beneficiary mobile no ");
					}
					catch(InvalidInputException exp)
					{
						exp.getMessage();
						return null;
					}	
					catch(InsufficientBalanceException exp)
					{
						exp.getMessage();
						return null;
					}
				
				}	
			else
				throw new InvalidInputException("Invalid Source mobile no ");
			}
			catch(InvalidInputException exp)
			{
				exp.getMessage();
				return null;
			}
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) 
	{
		Customer customer=repo.findOne(mobileNo);
		try{
			if(customer!=null)
				{
					return repo.depositAmount(mobileNo,amount);
				}
			else
				throw new InvalidInputException("Invalid mobile no ");
			}
			catch(InvalidInputException exp)
			{
				exp.getMessage();
				return null;
			}
		
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) 
	{
		Customer customer=repo.findOne(mobileNo);
		try{
			if(customer!=null)
				{
					customer = repo.withdrawlAmount(mobileNo,amount);
					if(customer!=null)
						return customer;
					else
						throw new InsufficientBalanceException("Insufficent amount");
				}
			else
				throw new InvalidInputException("Invalid mobile no ");
			}
			catch(InvalidInputException exp)
			{
				exp.getMessage();
				return null;
			}
			catch(InsufficientBalanceException exp)
			{
				exp.getMessage();
				return null;
			}
	
		
	}
	
}