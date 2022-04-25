package model;
import java.sql.*;
public class Interrupt
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/egsystem", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertInterrupt(String area, String startTime, String endTime, String hours, String message)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
//create a prepared statement
String query = " insert into interrupts(`interruptID`,`interruptArea`,`interruptStartTime`,`interruptEndTime`,`interruptHours`,`interruptMessage`)"
+ " values (?, ?, ?, ?, ?,?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, area);
preparedStmt.setString(3, startTime);
preparedStmt.setString(4, endTime);
preparedStmt.setString(5, hours);
preparedStmt.setString(6, message);
// execute the statement
preparedStmt.execute();
con.close();
output = "Inserted successfully";
}
catch (Exception e)
{
output = "Error while inserting the interrupt.";
System.err.println(e.getMessage());
}
return output;
} 

public String readInterrupts()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Interrupt Area</th><th>Interrupt StartTime</th>" +
 "<th>Interrupt End Time</th>" +
 "<th>Interrupt Hours</th>" +
 "<th>Interrupt Message</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from interrupts";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String interruptID = Integer.toString(rs.getInt("interruptID"));
 String interruptArea = rs.getString("interruptArea");
 String interruptStartTime = rs.getString("interruptStartTime");
 String interruptEndTime = rs.getString("interruptEndTime");
 String interruptHours = rs.getString("interruptHours");
 String interruptMessage = rs.getString("interruptMessage");
 // Add into the html table
 output += "<tr><td>" + interruptArea + "</td>";
 output += "<td>" + interruptStartTime + "</td>";
 output += "<td>" + interruptEndTime + "</td>";
 output += "<td>" + interruptHours + "</td>";
 output += "<td>" + interruptMessage + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'"
 		+ "          class='btn btn-secondary'></td>"
 + "<td><form method='post' action='interrupts.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove'"
 + "        class='btn btn-danger'>"
 + "<input name='interruptID' type='hidden' value='" + interruptID
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the interrupts.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateInterrupt(String ID, String area, String startTime, String endTime, String hours, String message)
{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE interrupts SET interruptArea=?,interruptStartTime=?,interruptEndTime=?,interruptHours=?,interruptMessage=? WHERE interruptID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, area);
	 preparedStmt.setString(2, startTime);
	 preparedStmt.setString(3, endTime);
	 preparedStmt.setString(4, hours);
	 preparedStmt.setString(5, message);
	 preparedStmt.setInt(6, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the interrupt.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteInterrupt(String interruptID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from interrupts where interruptID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(interruptID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the interrupt.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 