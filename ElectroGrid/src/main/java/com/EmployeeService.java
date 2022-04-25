package com;
import model.Employee;
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


@Path("/Employees")
public class EmployeeService {

	Employee employeeObj = new Employee();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployees()
	{

		return employeeObj.readEmployees();
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("empID") String empID,
							 @FormParam("firstName") String firstName,
							 @FormParam("lastName") String lastName,
							 @FormParam("gender") String gender,
							 @FormParam("jobTitle") String jobTitle,
							 @FormParam("phoneNumber") String phoneNumber,
							 @FormParam("email") String email,
							 @FormParam("password") String password,
							 @FormParam("province") String province,
							 @FormParam("city") String city ,
							 @FormParam("street") String street,
							 @FormParam("postalCode") String postalCode)
	{
		String output = employeeObj.insertEmployee(empID,firstName,lastName,gender,jobTitle,phoneNumber,email,password,province,city,street,postalCode);
		return output;
	}
}