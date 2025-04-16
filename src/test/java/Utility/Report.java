package Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Classfiles.Create_Spreadsheet;
import Classfiles.Launch_Chromedriver;
//import Classfiles.sample;
import Classfiles.Sample;
import Classfiles.Snowflake_connection;
import Classfiles.Teams_Activity_Analytics;

public class Report extends Launch_Chromedriver{
	Create_Spreadsheet spreadsheet=new Create_Spreadsheet();
	Snowflake_connection snowflake=new Snowflake_connection();
	public ExtentReports extent;
	public ExtentTest logger;
	public static long answer;
	public static ArrayList<String> values=new ArrayList<String>();
	public static int rowcount=1;



	@BeforeTest
	public void startReport() {
		
		String path= System.getProperty("user.dir") +"\\test_report\\ExtentReport.html";
		extent = new ExtentReports(path, true);
		extent.addSystemInfo("User Name", System.getProperty("user.name"));
		extent.addSystemInfo("Operating System", System.getProperty("os.name"));
		extent.addSystemInfo("Java Version", System.getProperty("java.version"));
		extent.addSystemInfo("Host Name", "Power BI Dashboard");
		extent.addSystemInfo("Environment", "Automation Testing");
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}
	
	@BeforeMethod
	public void openBrowser() 
	{
	}
	
	public static ArrayList<String> getList() 
	{
		return (ArrayList<String>) values;
	} 

	
	@Test(priority=1)
	
	public void Launch_Browser() throws Exception {
		//values.clear();
		try {
		logger = extent.startTest("SPOG_RCM_AMBULATORY_DASHBOARD");
		values.add("SPOG_RCM_AMBULATORY_DASHBOARD");
		Launch_Chromedriver Lchrome=new Launch_Chromedriver();
		answer=Lchrome.Launch_Chromebrowser();
		logger.log(LogStatus.PASS, "Test Case Passed");
		logger.log(LogStatus.INFO, "[SPOG_RCM_AMBULATORY] LOAD_TIME :" + answer + " milliseconds");
		Assert.assertTrue(true);
		if (answer >= 60000) // change it to 60000(1 minute)
		{
		logger.log(LogStatus.WARNING,"[SPOG_RCM_AMBULATORY] load time exceeded more than a minute" );
		}
		}catch(Exception e)
		{e.printStackTrace();}
	    }
	
	@Test(priority=2)
	
	public void team_activity_analytics_report() throws Exception {
		values.clear();
		try {
		logger = extent.startTest("TEAM_ACTIVITY_ANALYTICS");
		values.add("TEAM_ACTIVITY_ANALYTICS");
		Teams_Activity_Analytics Tanalytics=new Teams_Activity_Analytics();
		answer=Tanalytics.team_activity_analytics_report();
		logger.log(LogStatus.PASS, "Test Case Passed");
		logger.log(LogStatus.INFO, "[TEAM_ACTIVITY_ANALYTICS] LOAD_TIME :" + answer + " milliseconds");
		Assert.assertTrue(true);
		if (answer >=60000) // change it to 60000(1 minute)
		{
		logger.log(LogStatus.WARNING,"[TEAM_ACTIVITY_ANALYTICS] load time exceeded more than a minute" );
		}
		}catch(Exception e)
		{e.printStackTrace();}
	    }

	public  String getScreenhot(WebDriver driver, String Bridge) throws Exception 
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/TestScreenshots/"+Bridge+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	@AfterMethod
	public void result(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.SUCCESS) {
			try {
				String Bridge = "Pass_Test_Case";
				logger.log(LogStatus.PASS, logger.addScreenCapture(getScreenhot(driver, Bridge)));
				values.add("Pass");
			} catch (Exception e) {
			}
			spreadsheet.add_values_to_spreadsheet(values, rowcount++);
			snowflake.add_values_to_snowflake_table(values);
			
		}

		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				String Bridge = "Failed_Test_Case";
				logger.log(LogStatus.FAIL, logger.addScreenCapture(getScreenhot(driver, Bridge)));
				values.add("Fail");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
			spreadsheet.add_values_to_spreadsheet(values, rowcount++);
			snowflake.add_values_to_snowflake_table(values);
		}
		extent.endTest(logger);
	}

	@AfterTest
	public void endReport() {
	
	extent.flush();
	extent.close();
	}
	
	
}
