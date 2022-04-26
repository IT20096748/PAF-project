package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 */


import java.sql.*;

/**
 * @author Nimesha
 *
 */

public class Complaint {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	public String insertComplaint(String cID, String type, String cdesc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into complaint(`cID`,`type`,`cdesc`)"
							+ " values (?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setString(1,cID);
			preparedStmt.setString(2,type);
			preparedStmt.setString(3, cdesc);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the complaint";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	public String readComplaints()
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
	
				// Prepare the html table to be displayed
				output ="<html><head><title>Consumer</title>"
						+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">"
						+"</head><body><table class=\"table\" border='1'><tr>"
						+"<table border='1'><tr><th>Complaint ID&nbsp;</th>"
						+"<th>Complaint Type&nbsp;</th>" 
						+"<th>Description&nbsp;</th>" 
						+"<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from complaint";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String cID = rs.getString("cID");
					String type = rs.getString("type");
					String cdesc = rs.getString("cdesc");
					
					
					// Add into the html table
					output += "<tr><td>" + cID + "</td>";
					output += "<td>" + type + "</td>";
					output += "<td>" + cdesc + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>" 
							+ "<td><form method='post' action='items.jsp'>" 
							+ "<input name='btnRemove' type='button' value='Remove' class='btn btn-danger'>"
							+ "<input name='cID' type='hidden' value='" + cID
							+ "'>" + "</form></td></tr>";
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the complaints.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	public String updateComplaint(String cID,String type,String cdesc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE complaint SET type=?, cdesc=? WHERE cID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setString(1, type);
			preparedStmt.setString(2, cdesc);
			preparedStmt.setString(3, cID);
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the complaint.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	public String deleteComplaint(String cID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from complaint where cID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1,cID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the complaint.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}