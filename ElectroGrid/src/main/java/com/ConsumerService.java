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
}