package com;

import model.PowerConsumpation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/PowerConsumpation")
public class PowerConsumpationService {
	PowerConsumpation PowerConObj = new PowerConsumpation();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowerCon() {
		return PowerConObj.readPowerCon();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowerCon(
		@FormParam("name") String name,
		@FormParam("type") String type,
		@FormParam("description") String description) {
		String output = PowerConObj.insertPowerCon(name, type, description);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePowerCon(String powerconData) {
		// Convert the input string to a JSON object
		JsonObject PowerConObject = new JsonParser().parse(powerconData).getAsJsonObject();
		// Read the values from the JSON object
		String consumtiopn_id = PowerConObject.get("consumtiopn_id").getAsString();
		String name = PowerConObject.get("name").getAsString();
		String type = PowerConObject.get("type").getAsString();
		String description = PowerConObject.get("description").getAsString();	
		String output = PowerConObj.updatePowerCon(consumtiopn_id, name, type, description);
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowerCon(String powerconData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(powerconData, "", Parser.xmlParser());

		// Read the value from the element
		String consumtiopn_id = doc.select("consumtiopn_id").text();
		String output = PowerConObj.deletePowerCon(consumtiopn_id);
		return output;
		
	}
}
