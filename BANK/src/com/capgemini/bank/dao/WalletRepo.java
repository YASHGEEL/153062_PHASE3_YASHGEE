package com.capgemini.bank.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.capgemini.bank.bean.Customer;




public interface WalletRepo {


	
	public Customer findOne(String mobileNo);

	public Customer createAccount(String name, String mobileNo);

	public Customer depositAmount(String mobileNo, BigDecimal amount);

	public Customer withdrawlAmount(String mobileNo, BigDecimal amount);
}
