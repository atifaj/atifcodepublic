package com.atif.recipefinder.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atif.recipefinder.model.FridgeItem;
import com.atif.recipefinder.model.Ingredient;
import com.atif.recipefinder.model.Recipe;

public class RecipeFinderService {

	public static Map<Date,Recipe> findValidRecpies(List<FridgeItem> fridgeItems,List<Recipe> recipies){
   
		Map<Date,Recipe> foundRecipes = new HashMap<Date,Recipe>();
    	    	
    	Map<String,FridgeItem> fridgeItemsMap = new HashMap<String,FridgeItem>();
    	for (FridgeItem i : fridgeItems){
    		fridgeItemsMap.put(i.getItem(),i);
    	}
    	
    	for (Recipe recipe : recipies) { 
    		Date closestUseby = findClosestUsebyItemRecipeWithValidIngredients(recipe,fridgeItemsMap);
			if(closestUseby != null)
				foundRecipes.put(closestUseby,recipe);    		
    	}
    	
    	return foundRecipes;
    }
    
    private static Date findClosestUsebyItemRecipeWithValidIngredients(Recipe recipe, Map<String,FridgeItem> fridgeItems){
    	Date closestUseby = null;
    	for (Ingredient ingredient : recipe.getIngredients()) {
    		FridgeItem fridgeItem = getIngredientInFridge(ingredient,fridgeItems);
    		
    		if (fridgeItem == null) return null;
    		
    		if (closestUseby == null || closestUseby.compareTo(fridgeItem.getUseBy()) > 0) { 
    			closestUseby = fridgeItem.getUseBy(); 
    		} 

    	}
    	return closestUseby;
    	
    }
    
    private static FridgeItem getIngredientInFridge(Ingredient ingredient,Map<String,FridgeItem> fridgeItems){
    	FridgeItem item = fridgeItems.get(ingredient.getItem());
    	Date currentDate = new Date();
    	if(item != null && ingredient!= null && currentDate.compareTo(item.getUseBy())<= 0 && item.getAmount() >= ingredient.getAmount()){
    		return item;
    	}
    	
    	return null;
    }	
}
