package com.atif.recipefinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.atif.recipefinder.Service.CVSReaderService;
import com.atif.recipefinder.Service.JSONReaderService;
import com.atif.recipefinder.Service.RecipeFinderService;
import com.atif.recipefinder.model.FridgeItem;
import com.atif.recipefinder.model.Recipe;

public class RecipeFinder 
{
    
	public static final String ORDER_TAKEOUT = "Order Takeout";
	
	public static void main( String[] args )
    {
        if (args.length != 2) {	     
	       System.out.println("Illegal Arguments"); 
	       System.out.println(""); 
	       System.out.println("Usage:"); 
	       System.out.println("RecipeFidner <fridge.csv> <recipes.json>"); 
	       System.exit(1); 
	     }
        
        String fridgeCVSFilePath = args[0]; 
        String recipeJSONFilePath = args[1]; 
        
        RecipeFinder recipeFinder = new RecipeFinder();
        String recipeName = recipeFinder.findRecipe(fridgeCVSFilePath, recipeJSONFilePath);
        System.out.println(recipeName);
    }
    
    private String findRecipe(String fridgeCVSFilePath,String recipeJSONFilePath){
    	try {
			List<FridgeItem> fridgeItems= CVSReaderService.getFridgeItemsFromCSV(fridgeCVSFilePath);
			List<Recipe> recipies = JSONReaderService.getRecipesFromJSON(recipeJSONFilePath);
			Map<Date,Recipe> validRecipies = RecipeFinderService.findValidRecpies(fridgeItems,recipies);			
			if(validRecipies.size() == 0){
				return ORDER_TAKEOUT;
			}else{
				List<Date> keys = new ArrayList<Date>(validRecipies.keySet());
				Collections.sort(keys);
				return validRecipies.get(keys.get(0)).getName();
			}
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
    }
}
