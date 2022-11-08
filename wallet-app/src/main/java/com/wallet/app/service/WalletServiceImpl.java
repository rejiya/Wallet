package com.wallet.app.service;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dao.WalletDaoSqlImpl;
import com.wallet.app.dao.WalletDaoUtility;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WalletServiceImpl implements WalletService
{
	 	private WalletDao walletRepository = new WalletDaoSqlImpl(WalletDaoUtility.getConnectionToMySQL());

	 	private WalletDao walletRepositoryCollections = new WalletDaoImpl();
	
	@Override
	public Wallet registerWallet(Wallet newWallet)throws WalletException, SQLException 
	{
		if(newWallet != null)
		{
			return this. walletRepository .addWallet(newWallet);
		}
		else
		{
			throw new WalletException("INVALID DATA");
		}
	
	}

	@Override
	public Boolean login(Integer walletId, String password)throws WalletException
	{
		
		boolean isLogged = false;
		Wallet wallet = this.walletRepository.getWalletById(walletId);
		
				if(wallet.getPassword().equals(password))
				{
					
					 isLogged = true;
				}
				
				else
				{
					throw new WalletException("Invalid Username/Password");
				}
				
				return isLogged;
	}

	@Override
	public Double addFundsToWallet(Integer walletId, Double amount)throws WalletException 
	{
		Wallet we= this.walletRepository.getWalletById(walletId);
		System.out.println(we);
		if(we.getId() == walletId && amount>0.0)
		{
			Double balance = we.getBalance();
		
			we.setBalance(we.getBalance()+amount);
		
			balance= this.walletRepository.updateWallet(we).getBalance();
			
			return balance;
		}
		else
		{
			throw new WalletException("Cannot add funds to ur account!!");
		}
		
		
	}

	@Override
	public Double showWalletBalance(Integer walletId)throws WalletException
	{
		Wallet we=this.walletRepository.getWalletById(walletId);
		if(we.getId()!=null)
		{
			return we.getBalance();
		}
		
		else
		{
			throw new WalletException("Id does not exist");
		}
	}

	@Override
	public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException
	{
		boolean transfer=false;
		
		Wallet we1 = this.walletRepository.getWalletById(fromId);
		Wallet we2 = this.walletRepository.getWalletById(toId);
		if(we1.getBalance()>amount)
		{
			we1.setBalance(we1.getBalance()-amount);
			we2.setBalance(we2.getBalance()+amount);
			this.walletRepository.updateWallet(we1);
			this.walletRepository.updateWallet(we2);
			transfer=true;
			System.out.println("Transfer Successfull");
			return transfer;
		}
		
		else
			throw new WalletException("Insufficient amount !! ");
		
		
	}

	@Override
	public Wallet unRegisterWallet(Integer walletId, String password)throws WalletException 
		{
		
		Wallet we =this.walletRepository.getWalletById(walletId);
		if(we.getPassword().equals(password))
		{
			 we= this.walletRepository.deleteWalletById(we.getId());
			 if(we.getId() == null)
			 {
				 throw new WalletException("Account removed successfully!!");
			 }
			 return we;
			 
			 //return this.walletRepository.getWalletById(we.getId());
			
		}
		else
		{
			throw new WalletException ("Incorrect password or username !!");
		}
		
	}
	
	public Double withDraw(Integer walletId,String password,Double amount) throws WalletException
	{
		Wallet we = this.walletRepository.getWalletById(walletId);
		if(we.getPassword()==password && we.getBalance()>=amount)
		{
			we.setBalance(we.getBalance()-amount);
			return we.getBalance();
		}
		else
		{
			throw new WalletException("Minimum Balnce ust be Rs.10");
		}
		
		
	}
}
	
	
	   
