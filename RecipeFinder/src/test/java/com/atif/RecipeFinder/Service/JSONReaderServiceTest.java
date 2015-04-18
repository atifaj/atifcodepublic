package com.atif.RecipeFinder.Service;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.atif.recipefinder.Service.InvalidFileExcpetion;
import com.atif.recipefinder.Service.JSONReaderService;
import com.atif.recipefinder.model.Recipe;

public class JSONReaderServiceTest {

	Reader fr;
	
	@Before
	public void setUp() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("[{\"name\": \"grilled cheese on toast\", \"ingredients\": [{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}, { \"item\":\"cheese\", \"amount\":\"2\", \"unit\":\"slices\"}]}]");
		fr = new StringReader(sb.toString());		
    }
	
	@Test
	public void testGetRecipesFromJSON() throws InvalidFileExcpetion {
		List<Recipe> ri = JSONReaderService.getRecipesFromJSON(fr);
		assertEquals(ri.size(), 1);
		
		Recipe rItem = ri.get(0);
		assertEquals(rItem.getName(),"grilled cheese on toast");
		assertEquals(rItem.getIngredients().size(),2);
	}

}
