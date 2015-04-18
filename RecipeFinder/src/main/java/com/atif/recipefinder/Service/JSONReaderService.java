package com.atif.recipefinder.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.atif.recipefinder.model.Recipe;

public class JSONReaderService {

	public static List<Recipe> getRecipesFromJSON(String filePath) throws InvalidFileExcpetion {
    	ObjectMapper mapper = new ObjectMapper();
    			
		List<Recipe> list=null;
		try {
			list = mapper.readValue(new File(filePath),
					TypeFactory.defaultInstance().constructCollectionType(List.class,Recipe.class));
		} catch (IOException e) {
			throw new InvalidFileExcpetion("Cannot open JSON file:" + filePath);
		}
		return list;
    }	
}
