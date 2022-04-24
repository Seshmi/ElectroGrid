package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Payment;



@Path("/payments")

public class PaymentService {

	Payment pay = new Payment();
	

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readPayment() 
	{ 
		return pay.readPayment(); 
	
	}
	
	
	
	
	
	

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertPayment(@FormParam("holder_name") String holder_name, 
						@FormParam("ctype") String ctype, 
						@FormParam("card_no") String card_no, 
						@FormParam("cvv") String cvv,
						@FormParam("exp_month") String exp_month,
						@FormParam("exp_year") String exp_year,
						@FormParam("total") String total,
						@FormParam("pay_date") String pay_date)
	
	{ 
	
		String output = pay.insertPayment(holder_name, ctype, card_no, cvv, exp_month, exp_year, total, pay_date ); 
		return output; 

	}









	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		String pay_id  = itemObject.get("pay_id").getAsString();
		String holder_name    = itemObject.get("holder_name").getAsString();
		String ctype    = itemObject.get("ctype").getAsString();
		String card_no   = itemObject.get("card_no").getAsString();
		String cvv       = itemObject.get("cvv").getAsString();
		String exp_month     = itemObject.get("exp_month").getAsString();
		String exp_year      = itemObject.get("exp_year").getAsString();
		String total      = itemObject.get("total").getAsString();
		String pay_date      = itemObject.get("pay_date").getAsString();
	
		String output    = pay.updatePayment(pay_id, holder_name, ctype, card_no, cvv, exp_month, exp_year, total, pay_date );

		return output;
		
}









	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteItem(String itemData)
	{

		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String pay_id = doc.select("pay_id").text();
	
		String output = pay.deletePayment(pay_id);
	
		return output;
	}


}