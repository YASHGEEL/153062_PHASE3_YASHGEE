package com.capgemini.bank.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class Customer 
{

	private String name;
	@Id
	private String mobileNo;
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
	
	public Customer(String name2, String mobileNo2, Wallet wallet2) {
		this.name=name2;
		mobileNo=mobileNo2;
		wallet=wallet2;
	}
	public Customer() 
	{
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "\tCustomer Name = " + name + " \n\tMobileNo = " + mobileNo
				 + wallet;
	}
	
	
}


