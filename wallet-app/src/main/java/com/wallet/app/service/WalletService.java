package com.wallet.app.service;

import java.sql.SQLException;

import com.wallet.app.dto.*;
import com.wallet.app.exception.WalletException;

public interface WalletService 
{
	Wallet registerWallet(Wallet newWallet)throws WalletException, SQLException;
	
	Boolean login(Integer walletId,String password)throws WalletException;

	Double addFundsToWallet(Integer walletId, Double amount)throws WalletException;

	Double showWalletBalance(Integer walletId)throws WalletException;

	Boolean fundTransfer(Integer fromId, Integer toId, Double amount)throws WalletException;
	
	Wallet unRegisterWallet(Integer walletId,String password)throws WalletException;
	
	Double withDraw(Integer walletId,String password,Double amount) throws WalletException;
	
	

}
