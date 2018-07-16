package com.capgemini.bank.exception;

public class InvalidInputException extends RuntimeException {

	public InvalidInputException(String msg) 
	{
		System.err.println(msg);
		
	}
}
