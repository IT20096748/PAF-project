package model;

public class BillDButil {

	//charge calculator
	public static Double ChargeCalculator(int units){
		
		double amt = 0;
		
		if(units <= 50)
	    {
	        amt += units * 5.40;
	    }
	    if(units > 50 && units <= 100)
	    {
	        amt += ((units-50) * 7.50);
	    }
	    if(units > 100 && units <= 150)
	    {
	        amt += ((units-100) * 10.60);
	    }
	    if(units > 150 && units <= 200)
	    {
	        amt += ((units-150) * 20.30);
	    }
	    if(units > 200 && units <= 250)
	    {
	        amt += ((units-200) * 25.20);
	    }
	    if(units > 250 && units <= 300)
	    {
	        amt += ((units-250) * 35.80);
	    }
	    if(units > 300)
	    {
	    	amt += ((units-300) * 40.78);
	    }
		
		return amt;
		
	}
	
	
public static String TariffBlockDesign(int units){
		
		String output = "";
		
		if(units <= 50)
	    {
	       output += "<td>1 - 50</td>";
	    }
	    if(units > 50 && units <= 100)
	    {
	    	output += "<td>51 - 100</td>";
	    }
	    if(units > 100 && units <= 150)
	    {
	    	output += "<td>101 - 150</td>";
	    }
	    if(units > 150 && units <= 200)
	    {
	    	output += "<td>151 - 200</td>";
	    }
	    if(units > 200 && units <= 250)
	    {
	    	output += "<td>201 - 250</td>";
	    }
	    if(units > 250 && units <= 300)
	    {
	    	output += "<td>251 - 300</td>";
	    }
	    if(units > 300)
	    {
	    	output += "<td>300 < </td>";
	    }
		
		return output;
		
	}
	
	
	public static String UnitsConsumedDesign(int units){
		
		String output = "";
		
		if(units <= 50)
	    {
	       output += "<td>"+units+"</td>";
	    }
		else if(units > 50 && units <= 100)
	    {
	    	output += "<td>50</td><td>"+(units - 50)+"</td>";
	    }
		else if(units > 100 && units <= 150)
	    {
			output += "<td>50</td><td>50</td><td>"+(units - 100)+"</td>";
	    }
		else if(units > 150 && units <= 200)
	    {
			output += "<td>50</td><td>50</td><td>50</td><td>"+(units - 150)+"</td>";
	    }
		else if(units > 200 && units <= 250)
	    {
			output += "<td>50</td><td>50</td><td>50</td><td>50</td><td>"+(units - 200)+"</td>";
	    }
		else if(units > 250 && units <= 300)
	    {
			output += "<td>50</td><td>50</td><td>50</td><td>50</td><td>50</td><td>"+(units - 250)+"</td>";
	    }
		else if(units > 300)
	    {
			output += "<td>50</td><td>50</td><td>50</td><td>50</td><td>50</td><td>50</td><td>"+(units - 300)+"</td>";
	    }
		
		return output;
		
	}
	
	
	public static String UnitRateDesign(int units){
		
		String output = "";
		
		if(units <= 50)
	    {
	       output += "<td>5.40</td>";
	    }
	    if(units > 50 && units <= 100)
	    {
	    	output += "<td>7.50</td>";
	    }
	    if(units > 100 && units <= 150)
	    {
	    	output += "<td>10.60</td>";
	    }
	    if(units > 150 && units <= 200)
	    {
	    	output += "<td>20.30</td>";
	    }
	    if(units > 200 && units <= 250)
	    {
	    	output += "<td>25.20</td>";
	    }
	    if(units > 250 && units <= 300)
	    {
	    	output += "<td>35.80</td>";
	    }
	    if(units > 300)
	    {
	    	output += "<td>40.78</td>";
	    }
		
		return output;
		
	}
	
	
	public static String ChargePerBlockDesign(int units){
		
		String output = "";
		
		if(units <= 50)
	    {
			output += "<td>"+ (units * 5.40) +"</td>";
	    }
	    if(units > 50 && units <= 100)
	    {
	        output += "<td>"+ ((units-50) * 7.50) +"</td>";
	    }
	    if(units > 100 && units <= 150)
	    {
	        output += "<td>"+ ((units-100) * 10.60) +"</td>";
	    }
	    if(units > 150 && units <= 200)
	    {
	        output += "<td>"+ ((units-150) * 20.30) +"</td>";
	    }
	    if(units > 200 && units <= 250)
	    {
	        output += "<td>"+ ((units-200) * 25.20) +"</td>";
	    }
	    if(units > 250 && units <= 300)
	    {
	    	output += "<td>"+ ((units-250) * 35.80) +"</td>";
	    }
	    if(units > 300)
	    {
	    	output += "<td>"+ ((units-300) * 40.78) +"</td>";
	    }
		
		return output;
		
	}
	
	
	public static Double FixedCharge(){
		
		Double Charge = 480.0;
		
		return Charge;
		
	}
	
	public static String BillDesign(String month, String issuedDate, String city, String firstName, String electricityAccountNo, String startedUnits, String endedUnits, String unitsConsumed, String chargeForUnitsconsumed, String fixedCharge, String totalCostOfSupply, String status) {
		
		String tariffBlockDesign = BillDButil.TariffBlockDesign(Integer.parseInt(unitsConsumed));
		String unitsConsumedDesign = BillDButil.UnitsConsumedDesign(Integer.parseInt(unitsConsumed));
		String unitRateDesign = BillDButil.UnitRateDesign(Integer.parseInt(unitsConsumed));
		String ChargePerBlockDesign = BillDButil.ChargePerBlockDesign(Integer.parseInt(unitsConsumed));
		
		
		String output = "";
		
		output = "<html><head><title>CEB</title>" +
				"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">" +
				"</head><body><center>" + 
				"<img src=\"https://firebasestorage.googleapis.com/v0/b/hms-medilanka.appspot.com/o/ceb.png?alt=media&token=b7442ec4-e6e5-454d-98dc-98c1b9985d84\" style=\"width: 400px;\">" +
				"<div class=\"p-3 mb-2 bg-danger text-white\"><h5>Ceylon Electricity Board Statement of Electricity Account</h5></div></center>";
		
		output += "<table class=\"table\" border='6'><tr><td><table class=\"table\" border='1'>";
		
		output += "<tr><th>Month :</th><td>" + month +"</td></tr>"
				+ "<tr><th>Issued Date :</th><td>"+ issuedDate +"</td></tr>"
				+ "<tr><th>Area Office :</th><td>"+ city +"</td></tr>"
				+ "<tr><th>Premises ID :</th><td>KRD6534</td></tr>";
		
		output += "</table></td><td><table class=\"table\" border='1'>";
		
		output += "<tr><th>Name :</th><td>"+ firstName +"</td></tr>"
				+ "<tr><th>Address :</th><td>Kosgolla Kosgolla</td></tr>"
				+ "<tr><th>Electricity Account No :</th><td>"+ electricityAccountNo +"</td></tr>";
		
		output += "</table></td></tr></table><table class=\"table table-bordered\" border='6'>";
		
		output += "<tr><th>Tariff Block :</th>"+ tariffBlockDesign +"</tr>"
				+ "<tr><th>Units consumed :</th>"+ unitsConsumedDesign +"</tr>"
				+ "<tr><th>Unit Rate. Rs /kWh :</th>"+ unitRateDesign  +"</tr>"
				+ "<tr><th>Charge per Block. Rs :</th>"+ ChargePerBlockDesign  +"</tr>";
		
		output += "</table><table class=\"table table-bordered\" border='6' style=\"width: 600px;\">";
		
		output += "<tr><th>Meter reading for first date of the month :</th><td>"+ startedUnits +"</td></tr>"
				+ "<tr><th>Meter reading for last date of the month :</th><td>"+ endedUnits +"</td></tr>"
				+ "<tr><th>Total No. of Units consumed (kWh) :</th><td>"+ unitsConsumed +"</td></tr>"
				+ "<tr><th>Charge for Units consumed (Rs.) :</th><td>"+ chargeForUnitsconsumed +"</td></tr>"
				+ "<tr><th>Fixed Charge (Rs.) :</th><td>"+ fixedCharge +"</td></tr>"
				+ "<tr><th>Total Cost of Supply (Rs.) :</th><td>"+ totalCostOfSupply +"</td></tr>"
				+ "<tr><th>Status :</th><td class=\"text-danger\"><strong>"+ status +"</strong></td></tr>";
		
		output += "</table></body></html>";
		
		
		return output;
	}
	
}
