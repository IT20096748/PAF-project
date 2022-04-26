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
	
}
