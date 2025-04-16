package Classfiles;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Select_credentials {
	
	public static String select_credentials(int row, int column) throws IOException,Exception
	{

		FileInputStream fis = new FileInputStream(".\\TestData\\Email_id.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		return sheet.getRow(row).getCell(column).getStringCellValue();
	}

	//get snowflake connection properties from excel sheet
	public static String get_snowflake_credentials(int row, int column) throws IOException, Exception {

		FileInputStream fis = new FileInputStream(".\\TestData\\Snowflake_Credentials.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		return sheet.getRow(row).getCell(column).getStringCellValue();
	}
}
