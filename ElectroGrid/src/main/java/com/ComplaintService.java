/**
 * 
 */
package com;

import model.Complaint;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Complaints")


public class ComplaintService {
Complaint cObj = new Complaint();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{

		return cObj.readComplaints();
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("cID") String cID,
							 @FormParam("type") String type,
							 @FormParam("cdesc") String cdesc)
	{
		String output = cObj.insertComplaint(cID, type, cdesc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComplaint(String complaintData)
	{
		//Convert the input string to a JSON object
		JsonObject cObject = new JsonParser().parse(complaintData).getAsJsonObject();
		
		//Read the values from the JSON object
		String cID = cObject.get("cID").getAsString();
		String type = cObject.get("type").getAsString();
		String cdesc = cObject.get("cdesc").getAsString();
		
		
		String output = cObj.updateComplaint(cID,type,cdesc);
		
		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String complaintData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(complaintData, "", Parser.xmlParser());
		
		//Read the value from the element <cID>
		String cID = doc.select("cID").text();
		
		String output = cObj.deleteComplaint(cID);
		
		return output;
	}

	
	

}
