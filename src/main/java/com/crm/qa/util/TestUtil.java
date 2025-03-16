package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.BasePage;

public class TestUtil extends BasePage{

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
	public static long THREAD_SLEEP = 4;
	
	public static String TESTDATA_SHEET_PATH = "S://Automation//TestData.xlsx";
	
	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");	
	}
	
	public static void selectByValue(WebElement ele, String value) {
		Select options = new Select(ele);
		options.selectByValue(value);
	}
	
	public static Object[][] getObjectTestData(String[][] data) {
		
		return data;
	}
	
	//Get the data from an Excel file
	public static Object[][] getTestData(String sheetName) throws IOException {
		
		FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH);
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int totalRows = sheet.getLastRowNum()+1;             // Convert index to count
		int totalCoumns = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[totalRows-1][totalCoumns];  //excluding header row in the data set
		
		for(int i=1; i< totalRows; i++) {         // Start from 1 to skip the header row
			
			for(int j=0; j< totalCoumns; j++) {
				
				Cell cell = sheet.getRow(i).getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); // Handle null cells
				data[i-1][j] = getCellValue(cell);
				
//				[i-1] because we skipped the header row, the first row of data should start from index 0 in your array
				
//				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		workbook.close();
		file.close();
		
		return data;
	}
	
	//Handles different cell types (e.g., STRING, NUMERIC, BOOLEAN, FORMULA, BLANK)
	public static Object getCellValue(Cell cell) {
		
		switch (cell.getCellType()) {
		
		case STRING:
			return cell.getStringCellValue();
			
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				return cell.getNumericCellValue();
			}
			
		case BOOLEAN:
			return cell.getBooleanCellValue();
		
		case FORMULA:
			return cell.getCellFormula();
		
		case BLANK:
			return ""; // Return empty string for blank cells
		
		default:
			return "";  // Default to empty string for unknown types
		}
	}
	

}

