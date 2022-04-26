package model;

import java.sql.*;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	//view meter list
	public String readMeters()
	{
		String output = "";
		
		try
		{
				con = DBconfig.getConnection();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
	
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Meter Code</th><th>Premises ID</th>" +
						"<th>Electricity Account No</th>" +
						"<th>Manufacture Date</th>" +
						"<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from Meter";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					String meterID = Integer.toString(rs.getInt("meterID"));
					String meterCode = rs.getString("meterCode");
					String premisesID = rs.getString("premisesID");
					String electricityAccountNo = Integer.toString(rs.getInt("electricityAccountNo"));
					String manufactureDate = rs.getString("manufactureDate");
					
					// Add into the html table
					output += "<tr><td>" + meterCode + "</td>";
					output += "<td>" + premisesID + "</td>";
					output += "<td>" + electricityAccountNo + "</td>";
					output += "<td>" + manufactureDate + "</td>";
	
					// buttons
					output += "<td><form method='post' action='items.jsp'>" 
							+ "<input name='btnUpdate' type='submit' value='Update' class='btn btn-secondary'>"
							+ "<input name='itemID' type='hidden' value='" + meterID
							+ "'>" + "</form></td>"
							+ "<td><form method='post' action='items.jsp'>" 
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='itemID' type='hidden' value='" + meterID
							+ "'>" + "</form></td></tr>";
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//get meter details as JSON format
	public String getMeter(String ID)
	{
		String output = "";
		
		try
		{
				con = DBconfig.getConnection();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
	
				
				// create a prepared statement
				String query = "select * from Meter WHERE meterID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(ID));
				
				// execute the statement
				ResultSet rs = preparedStmt.executeQuery();
				
				// create hashMap object
				HashMap<String, String> mete = new HashMap<String, String>();
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String meterID = Integer.toString(rs.getInt("meterID"));
					String meterCode = rs.getString("meterCode");
					String premisesID = rs.getString("premisesID");
					String electricityAccountNo = Integer.toString(rs.getInt("electricityAccountNo"));
					String manufactureDate = rs.getString("manufactureDate");
					
					mete.put("meterID", meterID);
					mete.put("meterCode", meterCode);
					mete.put("premisesID", premisesID);
					mete.put("electricityAccountNo", electricityAccountNo);
					mete.put("manufactureDate", manufactureDate);					
			
				}
				
				con.close();
				
				//convert hashmap to json using gson
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				String jsonString = gson.toJson(mete);
				
				// Complete the html table
				output = jsonString;
		}
		catch (Exception e)
		{
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//update meter delails
	public String updateMeter(String meterID, String meterCode, String premisesID, String electricityAccountNo, String manufactureDate)
	{
		String output = "";
		
		try
		{
			con = DBconfig.getConnection();
			
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE Meter SET meterCode=?,premisesID=?,electricityAccountNo=?,manufactureDate=? WHERE meterID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, meterCode);
			preparedStmt.setString(2, premisesID);
			preparedStmt.setInt(3, Integer.parseInt(electricityAccountNo));
			preparedStmt.setString(4, manufactureDate);
			preparedStmt.setInt(5, Integer.parseInt(meterID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the meter.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//delete meter
	public String deleteMeter(String meterID)
	{
		String output = "";
		
		try
		{
			con = DBconfig.getConnection();;
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from Meter where meterID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(meterID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
