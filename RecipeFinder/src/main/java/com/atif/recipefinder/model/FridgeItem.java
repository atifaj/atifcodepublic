package com.atif.recipefinder.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FridgeItem extends Ingredient {
	
	private Date useBy;
		
	public Date getUseBy() {
		return useBy;
	}
	public void setUseBy(Date useBy) {
		this.useBy = useBy;
	}
	
	public void setUseByAsString(String useByAsString) throws ParseException {
		   DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		   Date d = df.parse(useByAsString);
		   setUseBy(d);
	}

}
