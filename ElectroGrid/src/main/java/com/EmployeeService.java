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
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String employeeData)
	{
		//Convert the input string to a JSON object
		JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
		
		//Read the values from the JSON object
		String empNo= employeeObject.get("empNo").getAsString();
		String empID = employeeObject.get("empID").getAsString();
		String firstName= employeeObject.get("firstName").getAsString();
		String lastName = employeeObject.get("lastName").getAsString();
		String gender = employeeObject.get("gender").getAsString();
		String jobTitle = employeeObject.get("jobTitle").getAsString();
		String phoneNumber= employeeObject.get("phoneNumber").getAsString();
		String email = employeeObject.get("email").getAsString();
		String password = employeeObject.get("password").getAsString();
		String province = employeeObject.get("province").getAsString();
		String city = employeeObject.get("city").getAsString();
		String street = employeeObject.get("street").getAsString();
		String postalCode = employeeObject.get("postalCode").getAsString();
		
		String output = employeeObj.updateEmployee(empNo,empID,firstName,lastName,gender,jobTitle,phoneNumber,email,password,province,city,street,postalCode);
		return output;
		
	
	}
}