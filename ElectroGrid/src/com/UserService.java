package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.User;

@Path("/User")
public class UserService {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("name") String name,			
	 @FormParam("phone_number") String phone_number,
	 @FormParam("address") String address,
	 @FormParam("Email") String Email)
	{
	 String output = UserObj.insertUser(name, phone_number, address, Email);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{

	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String userID = userObject.get("userID").getAsString();
	 String name = userObject.get("name").getAsString();
	 String phone_number = userObject.get("phone_number").getAsString();
	 String address = userObject.get("address").getAsString();
	 String Email = userObject.get("Email").getAsString();
	 
	 String output = UserObj.updateUser(userID, name, phone_number, address, Email);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	 String userID = doc.select("userID").text();
	 String output = UserObj.deleteUser(userID);
	return output;
	}
	
}
