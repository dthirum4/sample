package Classfiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.edge.EdgeDriver;

public class Sample {
	
	static int value=110;
	public static WebDriver driver;
	
	
	public static void func() throws Exception
	{
		System.out.println("Welcome!");
		System.setProperty("webdriver.edge.driver",".\\Drivers\\msedgedriver.exe" );
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\dthirum4\\OneDrive - UHG\\Migrated\\Devibala_New\\Payer Products\\Drivers\\chromedriver.exe");
		//System.setProperty("webdriver.edge.driver","C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
		driver=new EdgeDriver();
		
		try {
			
		//launch_chrome
		System.out.println("Inside func()");
		Thread.sleep(5000);
		driver.manage().window().maximize();
		driver.get("http://app.powerbi.com/");
		System.out.println("URL launched successfully");
	}
		finally 
		{
		Thread.sleep(5000);
		driver.close();
		System.out.println("Driver closed successfully");
		}
	}

	public static void main (String a[]) throws InterruptedException, Exception
	{
		//System.out.println(func());
		func();
	}

}
