package com.capgemini.bank.test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.bean.Wallet;
import com.capgemini.bank.service.WalletService;
import com.capgemini.bank.service.WalletServiceImpl;




public class TestClas {

	
	WalletService service;
	
	@Before
	public void initData(){
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9900112212", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
		 service= new WalletServiceImpl(data);
			
	}
	

}
