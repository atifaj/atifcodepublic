package com.atif.recipefinder.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.atif.recipefinder.model.Recipe;

public class JSONReaderService {

	public static List<Recipe> getRecipesFromJSON(String filePath) throws InvalidFileExcpetion {    	
		File file = new File(filePath);
		try {
			Reader fr = new FileReader(file);
			return getRecipesFromJSON(fr);
		} catch (IOException e) {
			throw new InvalidFileExcpetion("Cannot open JSON file:" + file.getAbsolutePath());
		}		
    }
	
	public static List<Recipe> getRecipesFromJSON(Reader reader) throws InvalidFileExcpetion {
    	ObjectMapper mapper = new ObjectMapper();
    			
		List<Recipe> list=null;
		
		try {
			list = mapper.readValue(reader,
					TypeFactory.defaultInstance().constructCollectionType(List.class,Recipe.class));
		} catch (IOException e) {
			throw new InvalidFileExcpetion("Invalid JSON file");
		}
		
		return list;
    }	
}
