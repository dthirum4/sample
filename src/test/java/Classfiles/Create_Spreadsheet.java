package Classfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Create_Spreadsheet {
	
	public void add_values_to_spreadsheet(List<String> values,int rowcount) throws IOException
	{
		String currentDir=System.getProperty("user.dir");
		FileInputStream fis2 = new FileInputStream(currentDir+"\\test-output\\Spreadsheet.xlsx");
		XSSFWorkbook workbook1 = new XSSFWorkbook(fis2);
		XSSFSheet Spreadsheet = workbook1.getSheet("Execution Details");
		Font headerFont = workbook1.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 11);

		XSSFCellStyle headerCellStyle = (XSSFCellStyle) workbook1.createCellStyle();

		headerCellStyle.setFont(headerFont);
		Row headerRow = Spreadsheet.createRow(rowcount);

		for(int index=0;index<values.size();index++)
		{
			Cell cell = headerRow.createCell(index);
			cell.setCellValue(values.get(index));
			cell.setCellStyle(headerCellStyle);
			
		}

	

		for(int index=0;index<values.size();index++)
			Spreadsheet.autoSizeColumn(index);

		FileOutputStream fileOut = new FileOutputStream(".//test-output//Spreadsheet.xlsx");
		workbook1.write(fileOut);

		fis2.close();


	}

}
