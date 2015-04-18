package com.atif.RecipeFinder.Service;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.atif.recipefinder.Service.CVSReaderService;
import com.atif.recipefinder.model.FridgeItem;
import com.atif.recipefinder.model.Unit;
import com.opencsv.CSVReader;

public class CVSReaderServiceTest {

	CSVReader csvr;
	
	@Before
	public void setUp() throws Exception {
		StringBuilder sb = new StringBuilder();
          sb.append("bread,10,slices,25/12/2015");
          csvr = new CSVReader(new StringReader(sb.toString()));
      }

	
	@Test
	public void testGetFridgeItemsFromCSV() throws ParseException{
		List<FridgeItem> fi = CVSReaderService.getFridgeItemsFromCSV(csvr);
		assertEquals(fi.size(), 1);
		
		FridgeItem fItem = fi.get(0);
		assertEquals(fItem.getItem(),"bread");
		assertEquals(fItem.getAmount(),10);
		assertEquals(fItem.getUnit(),Unit.slices);
		assertEquals(fItem.getUseBy(),(new SimpleDateFormat("dd/MM/yyyy")).parse("25/12/2015"));
	}

}
