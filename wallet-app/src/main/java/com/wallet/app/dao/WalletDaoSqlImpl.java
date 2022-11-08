package com.wallet.app.dao;


import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.SqlException;
import com.wallet.app.exception.WalletException;

public class WalletDaoSqlImpl implements WalletDao {
	
	private Connection connection;

	public WalletDaoSqlImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	
  
	
	@Override
	public Wallet addWallet(Wallet newWallet) throws WalletException,SQLException
	{
		
				 
					String sql = "INSERT INTO walletdetails (id,name,balance,pswd) VALUES(?,?,?,?)";
					try {
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, newWallet.getId());
						preparedStatement.setString(2, newWallet.getName());
						preparedStatement.setDouble(3, newWallet.getBalance());
						preparedStatement.setString(4, newWallet.getPassword());
						System.out.println(preparedStatement);
						Integer count = preparedStatement.executeUpdate();
						if (count == 1)
							System.out.println("Employee added successfully to DB.");
						else
							throw new SQLException("Cannot insert the record due to some invalid input");

					} 
					catch (SQLException e)
					{
						throw e;
					}

					return newWallet;
				
				}
				
			

@Override
	public Wallet getWalletById(Integer walletId)throws WalletException 
	{
		ResultSet we ;
		Wallet wallet = null;
	
		 String sql="SELECT * FROM walletdetails where id=?";
		 
		try {
			if(walletId != null)
			{
				PreparedStatement stmt=connection.prepareStatement(sql);  
				stmt.setInt(1,walletId);
								
				ResultSet rs=stmt.executeQuery();  
			 					
				if(rs.next())
				{
					wallet = new Wallet(rs.getInt("id"),rs.getString("name"),rs.getDouble("balance"),rs.getString("pswd")
							);
					return wallet;
					
				}
			}
				
			
			
			else
			{
				throw new WalletException ("Invalid ID");
			}
			
		}
		catch(Exception e)
			{
			e.printStackTrace();;
			}
		return wallet;
		
		
	}

	@Override
	public Wallet updateWallet(Wallet updateWallet)throws WalletException
	{
		
		Wallet wallet= new Wallet();
		String sql = "UPDATE walletdetails set Balance=? WHERE ID = ?";
		try {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDouble(1, updateWallet.getBalance());
		preparedStatement.setInt(2, updateWallet.getId());
		
		preparedStatement.executeUpdate();
		
		String q="Select * from walletdetails where id="+updateWallet.getId()+"";
		ResultSet rs=preparedStatement.executeQuery(q);

				if(rs.next())
				{
					wallet = new Wallet
							(rs.getInt("id"),rs.getString("name"),rs.getDouble("balance"),rs.getString("pswd")
							);
					
					
					return wallet;
					
				}
							
		
	            }
				
		
		catch (Exception e)
			{
			e.printStackTrace();
			}
			return wallet;
	}

	@Override
	public Wallet deleteWalletById(Integer walletID)throws WalletException
	{
		Wallet wallet = new Wallet();
		
		String sql="DELETE FROM walletdetails WHERE id="+walletID+"";
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			Integer count = preparedStatement.executeUpdate();
			if (count == 1)
				System.out.println("Employee removed successfully from DB.");
			else
				System.out.println("Employee could not be removed to DB.");

				
			}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return wallet;
		
	}
	
	
}