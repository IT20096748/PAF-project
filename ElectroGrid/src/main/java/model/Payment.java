package model;

import java.sql.*;

public class Payment {
	
Connection con = null;
	
	//make payment
	public String makePayment(String electricityAccountNo, String billID, String paymentAmount)
	{
		String output = "";
		
		try
		{
			con = DBconfig.getConnection();
			
			String paymentDateTime = CommonDButil.DateTime();
			String paymentID = CommonDButil.IDGenerator();
			
			if (con == null)
			{return "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = " insert into Payment(`paymentID`,`electricityAccountNo`,`billID`,`paymentDateTime`,`paymentAmount`)"
							+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, paymentID);
			preparedStmt.setInt(2, Integer.parseInt(electricityAccountNo));
			preparedStmt.setInt(3, Integer.parseInt(billID));
			preparedStmt.setString(4, paymentDateTime);
			preparedStmt.setDouble(5, Double.parseDouble(paymentAmount));
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Payed successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
