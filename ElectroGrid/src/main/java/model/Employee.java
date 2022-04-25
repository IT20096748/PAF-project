package model;
import java.sql.*;

/**
 * @author Nimesha
 *
 */
public class Employee {
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
	public String insertEmployee(String empID ,String firstName,String lastName,String gender,String jobTitle,String phoneNumber, String email, String password, String province , String city , String street, String postalCode )
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			 

			// create a prepared statement
			String query = " insert into employee(`empNo`,`empID`,`firstName`,`lastName`,`gender`,`jobTitle`,`phoneNumber`,`email`,`password`,`province`,`city`,`street`,`postalCode`)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2,Integer.parseInt(empID));
			preparedStmt.setString(3, firstName);
			preparedStmt.setString(4, lastName);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, jobTitle);
			preparedStmt.setString(7, phoneNumber);
			preparedStmt.setString(8, email);
			preparedStmt.setString(9, password);
			preparedStmt.setString(10, province);
			preparedStmt.setString(11, city);
			preparedStmt.setString(12, street);
			preparedStmt.setInt(13, Integer.parseInt(postalCode));
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the Employee.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
		
	}
	public String readEmployees()
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
	
				// Prepare the html table to be displayed
				output = "<html><head><title>Employee</title>"
						+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">"
						+"</head><body><table class=\"table\" border='1'><tr>"
						+"<th>EmployeeNo&nbsp;</th>"
						+ "<th>Employee ID&nbsp;</th>" 
						+"<th>First Name&nbsp;</th>" 
						+"<th>Last Name&nbsp;</th>" 
						+"<th>Gender&nbsp;</th>" 
						+"<th>Occupation&nbsp;</th>" 
						+"<th>Phone Number&nbsp;</th>" 
						+"<th>Email&nbsp;</th>" 
						+"<th>Password&nbsp;</th>" 
						+"<th>Province&nbsp; </th>" 
						+"<th>City&nbsp; </th>" 
						+"<th>Street&nbsp;</th>" 
						+"<th>Postal Code&nbsp;</th>" 
						+"<th>Update</th><th>Remove</th></tr>";
				
				String query = "select* from employee";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String empNo  = Integer.toString(rs.getInt("empNo"));
					String empID = Integer.toString(rs.getInt("empID"));
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String gender = rs.getString("gender");
					String jobTitle = rs.getString("jobTitle");
					String phoneNumber = rs.getString("phoneNumber");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String province = rs.getString("province");
					String city  = rs.getString("city");
					String street = rs.getString("street");
					String postalCode = Double.toString(rs.getDouble("postalCode"));
					
					
					// Add into the html table
					output += "<tr><td>" + empNo + "</td>";
					output += "<td>" + empID + "</td>";
					output += "<td>" + firstName + "</td>";
					output += "<td>" + lastName + "</td>";
					output += "<td>" + gender + "</td>";
					output += "<td>" + jobTitle+ "</td>";
					output += "<td>" + phoneNumber + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + password+ "</td>";
					output += "<td>" + province+ "</td>";
					output += "<td>" + city + "</td>";
					output += "<td>" + street+ "</td>";
					output += "<td>" + postalCode+ "</td>";
	
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>" 
							+ "<td><form method='post' action='consumer.jsp'>" 
							+ "<input name='btnRemove' type='button' value='Remove' class='btn btn-danger'>"
							+ "<input name='empID' type='hidden' value='" + empID
							+ "'>" + "</form></td></tr>";
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the Consumers.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
		
	}
	public String updateEmployee(String empNo,String empID , String firstName, String lastName, String gender,String jobTitle, String phoneNumber, String email, String password, String province , String city , String street, String postalCode )
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE employee SET empID=?,firstName=?,lastName=?,gender=?,jobTitle=?,phoneNumber=?,email=?,Password=?,province=?,city=?,street=?,postalCode=? WHERE empNo=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1,Integer.parseInt(empID));
			preparedStmt.setString(2, firstName);
			preparedStmt.setString(3, lastName);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, jobTitle);
			preparedStmt.setString(6, phoneNumber);
			preparedStmt.setString(7, email);
			preparedStmt.setString(8, password);
			preparedStmt.setString(9, province);
			preparedStmt.setString(10, city);
			preparedStmt.setString(11, street);
			preparedStmt.setInt(12,Integer.parseInt(postalCode));
			preparedStmt.setInt(13,Integer.parseInt(empNo));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the Consumer.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	public String deleteEmployee(String empID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from employee where empID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(empID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the Employee.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
}