package com.atif.RecipeFinder.Service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.atif.recipefinder.Service.RecipeFinderService;
import com.atif.recipefinder.model.FridgeItem;
import com.atif.recipefinder.model.Ingredient;
import com.atif.recipefinder.model.Recipe;
import com.atif.recipefinder.model.Unit;

import org.junit.Test;

public class RecipeFinderServiceTest {

	@Test
	public void testFindValidRecpies() throws ParseException {
		
		List<Recipe> recipies = new ArrayList<Recipe>();
		
		Recipe r = new Recipe();
		r.setName("salad sandwich");
		
		List<Ingredient> i = new ArrayList<Ingredient>();
		
		Ingredient i1 = new Ingredient();
		i1.setItem("bread");
		i1.setAmount(2);
		i1.setUnit(Unit.slices);
		
		Ingredient i2 = new Ingredient();
		i2.setItem("mixed salad");
		i2.setAmount(100);
		i2.setUnit(Unit.grams);
		
		i.add(i1);
		i.add(i2);
		r.setIngredients(i);
		recipies.add(r);
		
		List<FridgeItem> fridgeItems = new ArrayList<FridgeItem>();
		FridgeItem fi1 = new FridgeItem();
				
		fi1.setItem("bread");
		fi1.setAmount(10);
		fi1.setUnit(Unit.slices);
		fi1.setUseBy((new SimpleDateFormat("dd/MM/yyyy")).parse("25/12/2015"));
				
		FridgeItem fi2 = new FridgeItem();
		
		fi2.setItem("mixed salad");
		fi2.setAmount(150);
		fi2.setUnit(Unit.grams);
		fi2.setUseBy((new SimpleDateFormat("dd/MM/yyyy")).parse("26/12/2015"));
		
		fridgeItems.add(fi1);		
		fridgeItems.add(fi2);
		
		Map<Date, Recipe> m = RecipeFinderService.findValidRecpies(fridgeItems, recipies);
		
		assertEquals(m.size(),1);		
		assertEquals(m.get((m.keySet()).toArray()[0]).getName(),"salad sandwich");		
	}
	
	@Test
	public void testFindValidRecpieswithNonValidIngrdiant() throws ParseException {
		
		List<Recipe> recipies = new ArrayList<Recipe>();
		
		Recipe r = new Recipe();
		r.setName("salad sandwich");
		
		List<Ingredient> i = new ArrayList<Ingredient>();
		
		Ingredient i1 = new Ingredient();
		i1.setItem("bread");
		i1.setAmount(2);
		i1.setUnit(Unit.slices);
		
		Ingredient i2 = new Ingredient();
		i2.setItem("mixed salad");
		i2.setAmount(100);
		i2.setUnit(Unit.grams);
		
		i.add(i1);
		i.add(i2);
		r.setIngredients(i);
		recipies.add(r);
		
		List<FridgeItem> fridgeItems = new ArrayList<FridgeItem>();
		FridgeItem fi1 = new FridgeItem();
				
		fi1.setItem("bread");
		fi1.setAmount(10);
		fi1.setUnit(Unit.slices);
		fi1.setUseBy((new SimpleDateFormat("dd/MM/yyyy")).parse("25/12/2015"));
				
		FridgeItem fi2 = new FridgeItem();
		
		fi2.setItem("butter");
		fi2.setAmount(150);
		fi2.setUnit(Unit.grams);
		fi2.setUseBy((new SimpleDateFormat("dd/MM/yyyy")).parse("26/12/2015"));
		
		fridgeItems.add(fi1);		
		fridgeItems.add(fi2);
		
		Map<Date, Recipe> m = RecipeFinderService.findValidRecpies(fridgeItems, recipies);
		
		assertEquals(m.size(),0);		
			
	}
	
	@Test
	public void testFindValidRecpieswithNonValidUseBy() throws ParseException {
		
		List<Recipe> recipies = new ArrayList<Recipe>();
		
		Recipe r = new Recipe();
		r.setName("salad sandwich");
		
		List<Ingredient> i = new ArrayList<Ingredient>();
		
		Ingredient i1 = new Ingredient();
		i1.setItem("bread");
		i1.setAmount(2);
		i1.setUnit(Unit.slices);
		
		Ingredient i2 = new Ingredient();
		i2.setItem("mixed salad");
		i2.setAmount(100);
		i2.setUnit(Unit.grams);
		
		i.add(i1);
		i.add(i2);
		r.setIngredients(i);
		recipies.add(r);
		
		List<FridgeItem> fridgeItems = new ArrayList<FridgeItem>();
		FridgeItem fi1 = new FridgeItem();
				
		fi1.setItem("bread");
		fi1.setAmount(10);
		fi1.setUnit(Unit.slices);
		fi1.setUseBy((new SimpleDateFormat("dd/MM/yyyy")).parse("25/12/2012"));
				
		FridgeItem fi2 = new FridgeItem();
		
		fi2.setItem("mixed salad");
		fi2.setAmount(150);
		fi2.setUnit(Unit.grams);
		fi2.setUseBy((new SimpleDateFormat("dd/MM/yyyy")).parse("26/12/2015"));
		
		fridgeItems.add(fi1);		
		fridgeItems.add(fi2);
		
		Map<Date, Recipe> m = RecipeFinderService.findValidRecpies(fridgeItems, recipies);
		
		assertEquals(m.size(),0);		
			
	}

}
