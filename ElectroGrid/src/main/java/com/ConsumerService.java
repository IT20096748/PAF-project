package com;
import model.Consumer;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Consumers")

public class ConsumerService {

	Consumer consumerObj = new Consumer();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readConsumers()
	{

		return consumerObj.readConsumers();
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumer(@FormParam("accountNo") String accountNo,
							 @FormParam("firstName") String firstName,
							 @FormParam("lastName") String lastName,
							 @FormParam("gender") String gender,
							 @FormParam("occupation") String occupation,
							 @FormParam("phoneNumber") String phoneNumber,
							 @FormParam("email") String email,
							 @FormParam("password") String password,
							 @FormParam("province") String province,
							 @FormParam("city") String city ,
							 @FormParam("street") String street,
							 @FormParam("postalCode") String postalCode)
	{
		String output = consumerObj.insertConsumer(accountNo,firstName,lastName,gender,occupation,phoneNumber,email,password,province,city,street,postalCode);
		return output;
	}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConsumer(String consumerData)
	{
		//Convert the input string to a JSON object
		JsonObject consumerObject = new JsonParser().parse(consumerData).getAsJsonObject();
		
		//Read the values from the JSON object
		String consumerNo= consumerObject.get("consumerNo").getAsString();
		String accountNo = consumerObject.get("accountNo").getAsString();
		String firstName= consumerObject.get("firstName").getAsString();
		String lastName = consumerObject.get("lastName").getAsString();
		String gender = consumerObject.get("gender").getAsString();
		String occupation = consumerObject.get("occupation").getAsString();
		String phoneNumber= consumerObject.get("phoneNumber").getAsString();
		String email = consumerObject.get("email").getAsString();
		String password = consumerObject.get("password").getAsString();
		String province = consumerObject.get("province").getAsString();
		String city = consumerObject.get("city").getAsString();
		String street = consumerObject.get("street").getAsString();
		String postalCode = consumerObject.get("postalCode").getAsString();
		
		String output = consumerObj.updateConsumer(consumerNo, accountNo, firstName, lastName, gender, occupation, phoneNumber, email, password, province, city, street, postalCode);
		return output;
		
	
	}
}