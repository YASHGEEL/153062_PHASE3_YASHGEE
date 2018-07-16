package com.capgemini.bank.user;

import java.math.BigDecimal;
import java.util.Scanner;

import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.service.WalletService;
import com.capgemini.bank.service.WalletServiceImpl;

public class Client 
{
	   public static void main( String[] args ) throws InterruptedException
	   {
		   WalletService service = new WalletServiceImpl();
		   Scanner sc = new Scanner(System.in);
		   Scanner sc1 = new Scanner(System.in);
		   String ans="";
		   do
			{
				System.out.println("*********MY BANK ONLINE*********");
				System.out.println("\t1. Create Account.");
				System.out.println("\t2. Money Deposit.");
				System.out.println("\t3. Money Withdrwal.");
				System.out.println("\t4. Current Balance.");
				System.out.println("\t5. Fund Transfer.");
				System.out.println("\t6. Exit.");				
				System.out.println("****** ENTER YOUR CHOICE********");
				int choice = sc.nextInt();
				
				switch(choice)
				{
					case 1 : System.out.println("Enter the below details to open the account.");
							 System.out.println("Please provide your Name..");
							 String name  = sc1.nextLine();
							 System.out.println("Please provide your mobile number");
							 String mobileNumber = sc.next();
							 Customer customer = service.createAccount(name, mobileNumber);
							 if(customer!=null)
							 {							
								 System.out.println("*** Account Created Successfully ***");
								 System.out.println("\t   Account Details..");
								 System.out.println(customer);
							 }
							 break;
							 
					case 2 : System.out.println("Please provide the mobile number");
							 mobileNumber = sc.next();
							 System.out.println("Enter the amount which you want to deposit.");
							 BigDecimal amount = new BigDecimal(sc.nextInt());
							 customer = service.depositAmount(mobileNumber, amount);
							 if(customer!=null)
							 {
								 System.out.println("Updated Account balance");
								 System.out.println(customer);
							 }
							 break;
							 
					case 3 : System.out.println("Please provide the mobile number");
					 		 mobileNumber = sc.next();
					 		 System.out.println("Enter the amount which you want to withdrawl.");
					 		 amount = new BigDecimal(sc.nextInt());
					 		 customer = service.withdrawAmount(mobileNumber, amount);
					 		if(customer!=null)
							 {
								 System.out.println("Updated Account balance");
								 System.out.println(customer);
							 }
					 		 break;
					 		 
					case 4 : System.out.println("Please provide the mobile number");
							 customer = service.showBalance(sc.next());
							 if(customer!=null)
								 System.out.println(customer);
							 break;
							 
					case 5 : System.out.println("Please provide your  mobile number");
							 mobileNumber = sc.next();
							 System.out.println("Please provide the  beneficiary mobile number");
							 String beneficiaryMobileNumber = sc.next();
							 System.out.println("Enter the amount which you want to withdrawl.");
					 		 amount = new BigDecimal(sc.nextInt());
					 		 customer = service.fundTransfer(mobileNumber, beneficiaryMobileNumber, amount);
					 		if(customer!=null)
							 {
								 System.out.println("Updated Account balance");
								 System.out.println(customer);
							 }
					 		 break;
					 		 
					case 6 : System.out.println("Thanks for visiting XYZ Bank.");
							 System.exit(0);
							 break;
							 
					default : System.out.println("Invalid choice. Please enter the valid choice.");
							  break;
			
				}
				Thread.sleep(100);
				System.out.println("Do you want to continue yes/no");
				ans = sc.next();
		
			}while(ans.equalsIgnoreCase("yes")||ans.equalsIgnoreCase("y"));
	   }
}
