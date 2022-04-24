package model;

import java.sql.*;

public class Meter {
	
Connection con = null;
	
	//meter registration 
	public String insertMeter(String meterCode, String premisesID, String electricityAccountNo, String manufactureDate)
	{
		String output = "";
		
		try
		{
			con = DBconfig.getConnection();
			
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into Meter(`meterID`,`meterCode`,`premisesID`,`electricityAccountNo`,`manufactureDate`)"
							+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, meterCode);
			preparedStmt.setString(3, premisesID);
			preparedStmt.setInt(4, Integer.parseInt(electricityAccountNo));
			preparedStmt.setString(5, manufactureDate);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the meter.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
