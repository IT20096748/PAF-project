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

	
	

}
