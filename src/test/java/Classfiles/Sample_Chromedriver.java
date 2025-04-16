package Classfiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample_Chromedriver {
	
	static int value=110;
	public static WebDriver driver;
	
	
	public static void func() throws Exception
	{
		System.out.println("Welcome!");
		System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe" );
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\dthirum4\\OneDrive - UHG\\Migrated\\Devibala_New\\Payer Products\\Drivers\\chromedriver.exe");
		//System.setProperty("webdriver.edge.driver","C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
		driver=new ChromeDriver();
		
		try {
			
		//launch_chrome
		System.out.println("Inside chromedriver func()");
		Thread.sleep(5000);
		driver.manage().window().maximize();
		driver.get("https://app.powerbi.com/");
	}
		finally 
		{
		Thread.sleep(5000);
		driver.close();
		System.out.println("Chrome Driver closed successfully");
		}
	}

	public static void main (String a[]) throws InterruptedException, Exception
	{
		//System.out.println(func());
		func();
	}

}

