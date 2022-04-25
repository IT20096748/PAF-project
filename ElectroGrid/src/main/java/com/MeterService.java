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

import model.Meter;

@Path("/Meters")
public class MeterService {
	
Meter meterObj = new Meter();
	
	@GET
	@Path("/View-All")
	@Produces(MediaType.TEXT_HTML)
	public String readMeters()
	{

		return meterObj.readMeters();
	}
	
	
	@POST
	@Path("/Create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertMeter(@FormParam("meterCode") String meterCode,
							 @FormParam("premisesID") String premisesID,
							 @FormParam("electricityAccountNo") String electricityAccountNo,
							 @FormParam("manufactureDate") String manufactureDate)
	{
		String output = meterObj.insertMeter(meterCode, premisesID, electricityAccountNo, manufactureDate);
		return output;
	}
	
	@PUT
	@Path("/Modify/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMeter(String meterData, @PathParam("id") String meterID)
	{
		//Convert the input string to a JSON object
		JsonObject meterObject = new JsonParser().parse(meterData).getAsJsonObject();
		
		//Read the values from the JSON object
		String meterCode = meterObject.get("meterCode").getAsString();
		String premisesID = meterObject.get("premisesID").getAsString();
		String electricityAccountNo = meterObject.get("electricityAccountNo").getAsString();
		String manufactureDate = meterObject.get("manufactureDate").getAsString();
		
		String output = meterObj.updateMeter(meterID, meterCode, premisesID, electricityAccountNo, manufactureDate);
		
		return output;
	}
		
	
	@DELETE
	@Path("/Delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMeter(String meterData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(meterData, "", Parser.xmlParser());
		
		//Read the value from the element <meterID>
		String meterID = doc.select("meterID").text();
		
		String output = meterObj.deleteMeter(meterID);
		
		return output;
	}

}
