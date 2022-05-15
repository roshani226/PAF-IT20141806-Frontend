package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Unit;

@Path("/Unit")
public class UnitService {
	Unit UnitObj = new Unit();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUnit() {
		return UnitObj.readUnit();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUnit(@FormParam("user_account_no") String user_account_no,			
	 @FormParam("usage_date") String usage_date,
	 @FormParam("used_units") String used_units,
	 @FormParam("price_per_unit") String price_per_unit)
	 //@FormParam("total_price") String total_price)
	{
	 String output = UnitObj.insertUnit (user_account_no, usage_date, used_units, price_per_unit);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUnit(@FormParam("unit_id") String unit_id,@FormParam("user_account_no") String user_account_no,			
			 @FormParam("usage_date") String usage_date,
			 @FormParam("used_units") String used_units,
			 @FormParam("price_per_unit") String price_per_unit)
	{
	
	 String output = UnitObj.updateUnit(unit_id, user_account_no, usage_date, used_units, price_per_unit);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUnit(@FormParam("id") String id)
	{
	
	 String output = UnitObj.deleteUnit(id);
	return output;
	}
	
	
	@GET
	@Path("/Update")
	@Produces(MediaType.TEXT_HTML)
	public String viewUnit(@QueryParam("id") String id) {
		return UnitObj.viewUnit(id);
	}
}
