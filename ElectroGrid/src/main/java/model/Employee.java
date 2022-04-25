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
}