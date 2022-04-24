package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconfig {
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/electro_grid";	
	private static String username="root";
	private static String password="";
	private static Connection con;
	
	//A common method to connect to the DB
	public static Connection getConnection(){
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				//Provide the connection details: DBServer/DBName, username, password
				con = DriverManager.getConnection(url, username, password);
			}
			catch (Exception e)
			{e.printStackTrace();}
			
			return con;
	}

}
