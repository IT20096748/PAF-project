package model;

import java.sql.*;

public class ElectricityBill {

	Connection con = null;
	
	//Electricity Bill generator
	public String generateElectricityBill(String meterID, String monthStartedUnitsAmount, String monthEndedUnitsAmount, String electricityAccount) {
		
		String output = "";
		
		try
		{
			con = DBconfig.getConnection();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			String billID = CommonDButil.IDGenerator();
			String issuedDate = CommonDButil.DateTime();
			
			
			int endedUnits =Integer.parseInt(monthEndedUnitsAmount);
			int startedUnits = Integer.parseInt(monthStartedUnitsAmount);
			int unitsConsumed = endedUnits - startedUnits;
			
			double chargeForUnitsconsumed = BillDButil.ChargeCalculator(unitsConsumed);
			
			double fixedCharge = BillDButil.FixedCharge();
			
			double totalCostOfSupply = chargeForUnitsconsumed + fixedCharge;
			
			String month = CommonDButil.Month();
			
			int electricityAccountNo =Integer.parseInt(electricityAccount);
			
			
			
			
			
			// create a prepared statement
			String query = " insert into Bill(`billID`,`electricityAccountNo`,`meterID`,`month`,`issuedDate`,`unitsConsumed`,`chargeForUnitsconsumed`,`fixedCharge`,`totalCostOfSupply`,`startedUnits`,`endedUnits`,`status`)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, billID);
			preparedStmt.setInt(2, electricityAccountNo);
			preparedStmt.setInt(3, Integer.parseInt(meterID));
			preparedStmt.setString(4, month);
			preparedStmt.setString(5, issuedDate);
			preparedStmt.setInt(6, unitsConsumed);
			preparedStmt.setDouble(7, chargeForUnitsconsumed);
			preparedStmt.setDouble(8, fixedCharge);
			preparedStmt.setDouble(9, totalCostOfSupply);
			preparedStmt.setInt(10, startedUnits);
			preparedStmt.setInt(11, endedUnits);
			preparedStmt.setString(12, "Not Paid");
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Electricity bill generated successfully";
		}
		catch (Exception e)
		{
			output = "Error while generating the bill.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}
