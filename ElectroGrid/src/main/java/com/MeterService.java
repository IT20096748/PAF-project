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
	public String readItems()
	{

		return meterObj.readMeters();
	}
	

	@GET
	@Path("/Get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewMeter(@PathParam("id") String meterID)
	{

		return meterObj.getMeter(meterID);
	}
	
	@POST
	@Path("/Create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("itemCode") String itemCode,
							 @FormParam("itemName") String itemName,
							 @FormParam("itemPrice") String itemPrice,
							 @FormParam("itemDesc") String itemDesc)
	{
		String output = meterObj.insertMeter(itemCode, itemName, itemPrice, itemDesc);
		return output;
	}
	
	@PUT
	@Path("/Modify/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData, @PathParam("id") String itemID)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from the JSON object
		String itemCode = itemObject.get("itemCode").getAsString();
		String itemName = itemObject.get("itemName").getAsString();
		String itemPrice = itemObject.get("itemPrice").getAsString();
		String itemDesc = itemObject.get("itemDesc").getAsString();
		
		String output = meterObj.updateMeter(itemID, itemCode, itemName, itemPrice, itemDesc);
		
		return output;
	}
	
	
	@DELETE
	@Path("/Delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String meterData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(meterData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String meterID = doc.select("meterID").text();
		
		String output = meterObj.deleteMeter(meterID);
		
		return output;
	}

}
