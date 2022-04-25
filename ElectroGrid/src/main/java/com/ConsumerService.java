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
}