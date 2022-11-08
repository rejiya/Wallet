package com.wallet.app.dao;

import java.sql.SQLException;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.SqlException;
import com.wallet.app.exception.WalletException;

public interface WalletDao {
	
	Wallet addWallet(Wallet newWallet)throws WalletException,SQLException;
	
	Wallet getWalletById(Integer walletId)throws WalletException;
	
	Wallet updateWallet(Wallet updateWallet)throws WalletException;
	
	Wallet deleteWalletById(Integer walletID)throws WalletException;
}
