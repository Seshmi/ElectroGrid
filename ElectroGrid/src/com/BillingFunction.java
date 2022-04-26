package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Billing;

@Path("/Billing")
public class BillingFunction {
	Billing BillObject = new Billing();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBilling() {
		return BillObject.readBilling();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBilling(
	 @FormParam("bill_Acc") String bill_Acc,		
	 @FormParam("bill_Date") String bill_Date,
	 @FormParam("bill_Unit") String bill_Unit,
	 @FormParam("bill_price") String bill_price,
	 @FormParam("bill_Total") String bill_Total)
	{
	 String output = BillObject.insertBilling(bill_Acc, bill_Date, bill_Unit, bill_price, bill_Total);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBilling(String billData)
	{
	//Convert the input string to a JSON object
	 JsonObject bill_Object = new JsonParser().parse(billData).getAsJsonObject();
	//Read the values from the JSON object
	 String B_ID = bill_Object.get("B_ID").getAsString();
	 String bill_Acc = bill_Object.get("bill_Acc").getAsString();
	 String bill_Date = bill_Object.get("bill_Date").getAsString();
	 String bill_Unit = bill_Object.get("bill_Unit").getAsString();
	 String bill_price = bill_Object.get("bill_price").getAsString();
	 String bill_Total = bill_Object.get("bill_Total").getAsString();
	 String output = BillObject.updateBilling(B_ID, bill_Acc, bill_Date, bill_Unit, bill_price, bill_Total);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBilling(String billData)
	{
	//Convert The Input String To An XML Document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String ID = doc.select("B_ID").text();
	 String output = BillObject.deleteBilling(ID);
	return output;
	}
}
