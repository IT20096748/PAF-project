package model;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonDButil {
	
	static Connection con = null;
	
	//ID Generator
	public static String IDGenerator(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		String dateTime = sdf.format(date);
		
		String ID = dateTime;
		
		return ID;
	}
	
	//date time
	public static String DateTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		String dateTime = sdf.format(date);
		
		String ID = dateTime;
		
		return ID;
	}
	
	//month
	public static String Month(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		Date date = new Date();
		String month = sdf.format(date);
		
		return month;
	}

}
