package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;
	private static String excelFilePath;

	public static void createExcelFile(String fileName, String sheetName){
		try{
		FileOutputStream excelFile= new FileOutputStream(fileName);
		excelWBook = new XSSFWorkbook();
		excelWSheet = excelWBook.createSheet(sheetName);
		excelWBook.write(excelFile);
		excelFile.close();
		}catch (Exception e){
			System.out.println("Error in Creating ExcelFile.");
		}
	}
	
	// This method is to set the File path and open the Excel file, 
	// Pass Excel Path and Sheetname as Arguments to this method
	public static void openExcelFile(String path, String sheetName) {
		excelFilePath = path;
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);
			// Access the required test data sheet
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public static String getCellData(int rowNum, int colNum) {
		String cellData;
		try {
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			cellData = cell.toString();
		} catch (Exception e) {
			
			cellData = "";
		}
		
		return cellData;
	}
	
	public static String[] getExcelDataInAColimn(String ExcelSheetPath, String sheetname) {
		
		String[] arrayExcelData = null;
		try {
			ExcelUtils.openExcelFile(ExcelSheetPath, sheetname);
			int totalNoOfRows = excelWSheet.getPhysicalNumberOfRows();
			arrayExcelData = new String[totalNoOfRows];
			for (int i= 0 ; i < totalNoOfRows; i++) {
				arrayExcelData[i] = excelWSheet.getRow(i).getCell(0).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	
	

	// This method is to write in the Excel cell, Row num and Col num are the parameters
	public static void setCellData(String value, int rowNum, int colNum) {
		try {
			row = excelWSheet.getRow(rowNum);
			if(row==null){
				row=excelWSheet.createRow(rowNum);
			}

			cell = row.getCell(colNum);

			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(excelFilePath);
			excelWBook.write(fileOut);

			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("there is no value");
		}
	}

	public static int getUsedRowsCount() {
		try {
			int rowCount = excelWSheet.getPhysicalNumberOfRows();
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public static int getUsedCellCount(int rowNum) {
		try {
			int cellCount = excelWSheet.getRow(rowNum).getPhysicalNumberOfCells();
			return cellCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// this method is for to get current time
	public static String getCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		return date1;
	}
	
	// this method is to set color of excel columns
	public static void setCellColor() {
		CellStyle style = excelWBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
	}

}
