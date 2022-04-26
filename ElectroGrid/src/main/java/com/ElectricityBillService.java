package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import model.ElectricityBill;

@Path("/Bills")
public class ElectricityBillService {

	ElectricityBill billObj = new ElectricityBill();
	
	@GET
	@Path("/View-All")
	@Produces(MediaType.TEXT_HTML)
	public String viewBills()
	{

		return billObj.viewBills();
	}
	
	@GET
	@Path("/View/{month}/{accountNo}")
	@Produces(MediaType.TEXT_HTML)
	public String viewBill(@PathParam("accountNo") String accountNo,
						 @PathParam("month") String month)
	{
		System.out.println(accountNo);
		System.out.println(month);
		
		//client api for inter service communication Getting consumer details
		Client c = Client.create();
		WebResource resource = c.resource("http://localhost:8080/ElectroGrid/ConsumerService/Consumers/Get/" + accountNo);
		String consumerDetails = resource.get(String.class);
		
		//extract consumer details from json object
		JsonObject consumerObject = new JsonParser().parse(consumerDetails).getAsJsonObject();
		String city = consumerObject.get("city").getAsString();
		String firstName = consumerObject.get("firstName").getAsString();
		System.out.println(city);
		
		return billObj.viewBill(accountNo, month, city, firstName);
	}
	
	@POST
	@Path("/Create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String generateBill(@FormParam("meterID") String meterID,
							 @FormParam("monthStartedUnitsAmount") String monthStartedUnitsAmount,
							 @FormParam("monthEndedUnitsAmount") String monthEndedUnitsAmount)
	{

		//client api for inter service communication Getting meter details
		Client c = Client.create();
		WebResource resource = c.resource("http://localhost:8080/ElectroGrid/MeterService/Meters/Get/" + meterID);
		String meterDetails = resource.get(String.class);
		
		//extract meter details from json object
		JsonObject meterObject = new JsonParser().parse(meterDetails).getAsJsonObject();
		String electricityAccountNo = meterObject.get("electricityAccountNo").getAsString();
		
		String output = billObj.generateElectricityBill(meterID, monthStartedUnitsAmount, monthEndedUnitsAmount, electricityAccountNo);
		return output;
	}
	
	
	@PUT
	@Path("/Modify/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String billData, @PathParam("id") String billID)
	{
		//Convert the input string to a JSON object
		JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
		
		//Read the values from the JSON object
		String monthStartedUnitsAmount = billObject.get("monthStartedUnitsAmount").getAsString();
		String monthEndedUnitsAmount = billObject.get("monthEndedUnitsAmount").getAsString();
		
		String output = billObj.updateBill(billID, monthStartedUnitsAmount, monthEndedUnitsAmount);
		
		return output;
	}
	
	
	@DELETE
	@Path("/Delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String billData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String billID = doc.select("billID").text();
		
		String output = billObj.deleteBill(billID);
		
		return output;
	}
	
}
