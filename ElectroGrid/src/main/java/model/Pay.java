package model;

import java.sql.*;

public class Pay {
	
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
	
	//view payment list
	public String readPaymentList()
	{
		String output = "";
		
		try
		{
				con = DBconfig.getConnection();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
	
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Payment ID</th><th>Electricity Account No</th>" +
						"<th>Bill ID</th>" +
						"<th>Payment Date</th>" +
						"<th>Payment Amount</th>" +
						"</tr>";
				
				String query = "select * from Payment";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String paymentID = rs.getString("paymentID");
					String electricityAccountNo = Integer.toString(rs.getInt("electricityAccountNo"));
					String billID = Integer.toString(rs.getInt("billID"));
					String paymentDate = rs.getString("paymentDateTime");
					String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
					
					// Add into the html table
					output += "<tr><td>" + paymentID + "</td>";
					output += "<td>" + electricityAccountNo + "</td>";
					output += "<td>" + billID + "</td>";
					output += "<td>" + paymentDate + "</td>";
					output += "<td>" + paymentAmount + "</td></tr>";
	
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	//view one payment record
	public String readPayment(String ID)
	{
		String output = "";
		
		try
		{
				con = DBconfig.getConnection();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
	
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Payment ID</th><th>Electricity Account No</th>" +
						"<th>Bill ID</th>" +
						"<th>Payment Date</th>" +
						"<th>Payment Amount</th>" +
						"</tr>";
				
				// create a prepared statement
				String query = "select * from Payment WHERE billID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(ID));
				
				// execute the statement
				ResultSet rs = preparedStmt.executeQuery();
				
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String paymentID = rs.getString("paymentID");
					String electricityAccountNo = Integer.toString(rs.getInt("electricityAccountNo"));
					String billID = Integer.toString(rs.getInt("billID"));
					String paymentDate = rs.getString("paymentDateTime");
					String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
					
					// Add into the html table
					output += "<tr><td>" + paymentID + "</td>";
					output += "<td>" + electricityAccountNo + "</td>";
					output += "<td>" + billID + "</td>";
					output += "<td>" + paymentDate + "</td>";
					output += "<td>" + paymentAmount + "</td></tr>";
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}
