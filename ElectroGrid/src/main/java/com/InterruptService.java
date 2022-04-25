package com;
import model.Interrupt;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Interrupts")
public class InterruptService
{
	Interrupt interruptObj = new Interrupt();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInterrupts()
	 {
	 return interruptObj.readInterrupts();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInterrupt(@FormParam("interruptArea") String interruptArea,
	 @FormParam("interruptStartTime") String interruptStartTime,
	 @FormParam("interruptEndTime") String interruptEndTime,
	 @FormParam("interruptHours") String interruptHours,
	 @FormParam("interruptMessage") String interruptMessage)
	{
	 String output = interruptObj.insertInterrupt(interruptArea, interruptStartTime, interruptEndTime, interruptHours, interruptMessage);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInterrupt(String interruptData)
	{
	//Convert the input string to a JSON object
	 JsonObject interruptObject = new JsonParser().parse(interruptData).getAsJsonObject();
	//Read the values from the JSON object
	 String interruptID = interruptObject.get("interruptID").getAsString();
	 String interruptArea = interruptObject.get("interruptArea").getAsString();
	 String interruptStartTime = interruptObject.get("interruptStartTime").getAsString();
	 String interruptEndTime = interruptObject.get("interruptEndTime").getAsString();
	 String interruptHours = interruptObject.get("interruptHours").getAsString();
	 String interruptMessage = interruptObject.get("interruptMessage").getAsString();
	 String output = interruptObj.updateInterrupt(interruptID, interruptArea, interruptStartTime, interruptEndTime, interruptHours, interruptMessage);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInterrupt(String interruptData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(interruptData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String interruptID = doc.select("interruptID").text();
	 String output =interruptObj.deleteInterrupt(interruptID);
	return output;
	}
}