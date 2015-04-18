package com.atif.recipefinder.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.atif.recipefinder.model.FridgeItem;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class CVSReaderService {
	
	public static List<FridgeItem> getFridgeItemsFromCSV(String filePath) throws InvalidFileExcpetion{
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			throw new InvalidFileExcpetion("Cannot open CVS file:" + filePath);
		}
		
		return getFridgeItemsFromCSV(reader);
	}
	
	public static List<FridgeItem> getFridgeItemsFromCSV(CSVReader reader){    	
    	ColumnPositionMappingStrategy<FridgeItem> strat = new ColumnPositionMappingStrategy<FridgeItem>();
        strat.setType(FridgeItem.class);
        String[] columns = new String[] {"item", "amount", "unit","useByAsString"}; // the fields to bind do in your JavaBean
        strat.setColumnMapping(columns);

        CsvToBean<FridgeItem> csv = new CsvToBean<FridgeItem>();
        List<FridgeItem> list = csv.parse(strat, reader);
        return list;
    }
}
