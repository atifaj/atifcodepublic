package com.atif.recipefinder.model;

public class Ingredient {

	protected String item;
	protected int amount;
	protected Unit unit;

	public Ingredient() {
		super();
	}

	public String getItem() {
		return item;
	}

	public void setItem(String name) {
		this.item = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}