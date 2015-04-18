package com.atif.recipefinder.model;

public enum Unit {
	
	of("of"), grams("grams"), ml("milliliters"), slices("slices");
	
	String name;
  
	Unit(String name) {
		this.name = name;
	}

	public static Unit buildUnit(String name) {
		switch (name) {
			case "of":
				return of;
			case "grams":
				return grams;
			case "milliliters":
				return ml;
			case "slices":
				return slices;
			default:
				return null;
		}
	}	
}
