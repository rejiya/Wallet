package com.wallet.app.controller;
import com.wallet.app.service.*;
import java.util.*;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class Controller 
{
	 
	 
	public static void main(String[] args)
	{
	
		
		
		int id;
		String pswd=null;
		Scanner sc =new Scanner(System.in);
		WalletService walletService=new WalletServiceImpl();

		
		 System.out.println("****** WELCOME ******");
		  
		 System.out.println("1-->Register account  "+ '\n' +
		  "2-->Login to Account ");
		  
		 System.out.println("Enter ur choice : ");
		  
		 int n = sc.nextInt();
		  
		  
		  switch(n) {
		  
		  case 1: 
			  System.out.println("****ADD account****"); 
			  Wallet we = new Wallet();
			  we.setId(sc.nextInt()); we.setName(sc.next());
			  we.setBalance(sc.nextDouble()); we.setPassword(sc.next());
			  
			  try
			  { 
				  System.out.println(walletService.registerWallet(we)); 
			  } 
			  catch (WalletException | SQLException e) 
			  { 
				 e.printStackTrace();
			  }
			  
			  break;
	
		  case 2: 
			  System.out.println("****Login***");
			  
			  System.out.println("enter ur id");
			  
			  id=sc.nextInt();
			  System.out.println("enter ur pswd ");
			  pswd = sc.next();
		  
			  try 
			  { 
				  boolean status=walletService.login(id, pswd);
				  
				  if(status == true)
				  {
					  System.out.println(
					  "1-->Add Funds to ur Account"+'\n' +
					  "2-->Show Balance amount"+'\n' + "3-->Transfer amount"+'\n' +
					  "4-->Withdraw Amount"+'\n'+"5-->Delete account");
					  
					  System.out.println("Enter ur option: ");
					  int option = sc.nextInt();
					  while(option !=0)
					  {
							  if(option == 1) 
							{
							  try
							  { 
								  System.out.println("****Add funds****");
								 
								  System.out.println("Enter the id : "); 
								  
								  int funds_id = sc.nextInt();
								  
								  System.out.println("Enter the amount: ");
								  
								  double amt = sc.nextDouble();
								  
								  System.out.println(walletService.addFundsToWallet(funds_id, amt));
								  
								  System.out.println("to exit enter 0");
								  
								  System.out.println("Enter ur option: ");
								  option = sc.nextInt();
								  
							  }
								  
							  catch(WalletException e) 
								  {  e.printStackTrace();
								  
								  };
						  }
							  
							  else if(option == 2)
							  {
								  System.out.println("***Balance Display***");
								  int bal_id = sc.nextInt();
								  try {
									  System.out.println(walletService.showWalletBalance(bal_id));
									  

									  } 
								  catch(WalletException e)
								  { 
									  e.printStackTrace();
								  } ;
								  System.out.println("to exit enter 0");
								  
								  System.out.println("Enter ur option: ");
								  option = sc.nextInt();

							  }
							  
							  else if(option == 3)
							  {
								  System.out.println("****Transfer Amount****"); 
								  int fromId = sc.nextInt(); 
								  int toId=sc.nextInt(); 
								  Double amt=sc.nextDouble();
								  try {
									  System.out.println(walletService.fundTransfer(fromId, toId, amt)); }
								  catch(WalletException e) 
								  {
									   e.printStackTrace();
								  };
								  System.out.println("to exit enter 0");
								  
								  System.out.println("Enter ur option: ");
								  option = sc.nextInt();

							  }
							  
							  else if(option == 4)
							  {
								  System.out.println("****Withdraw Amount****");
								  int wId=sc.nextInt();
								  String psw = sc.next(); 
								  Double amount=sc.nextDouble(); 
								  try {
									  System.out.println(walletService.withDraw(wId,psw,amount)); 
									  } 
								  catch(WalletException e1) 
								  { 
									  e1.printStackTrace();
									  };
									  System.out.println("to exit enter 0");
									  
									  System.out.println("Enter ur option: ");
									  option = sc.nextInt();

							  }
							  else if(option == 5)
							  {
								  System.out.println("****Delete Account****"); 
								  System.out.println("Enter ur id: ");
								  int walletId=sc.nextInt(); 
								  System.out.println("Enter ur password: ");
								  String password = sc.next(); 
								  try {
									  
									  System.out.println(walletService.unRegisterWallet(walletId, password)); 
									  }
								  catch (WalletException e)
								  {
									  e.printStackTrace();
								  };
								  
								  System.out.println("to exit enter 0");
								  
								  System.out.println("Enter ur option: ");
								  option = sc.nextInt();

								  
							}
					  }
					  }
					  else
					  {
						  System.out.println("enter valid id and password");
					  }
							
			  }
				  
				  
			  catch(WalletException e) 
			  { 
				  e.printStackTrace();
			  
			  };
		  
		  		break;
		  
		 default:
			 System.out.println("login to view or modify account");
			 break;
		  
	}


	}
}

