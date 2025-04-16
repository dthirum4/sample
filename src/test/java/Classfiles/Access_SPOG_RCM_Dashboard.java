package Classfiles;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Access_SPOG_RCM_Dashboard {
	
	public static WebDriver driver;

	public static void launch_spog_rcm_report() throws Exception, InterruptedException {
		// TODO Auto-generated method stub
		// set chrome driver path
		System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\dthirum4\\OneDrive - UHG\\Migrated\\Devibala_New\\Payer Products\\Drivers\\chromedriver.exe");
		
		//launch chrome browser
		driver = new ChromeDriver();
		
		//maximize the browser window
		driver.manage().window().maximize();
		
		//launch the Power BI report
		driver.get("https://app.powerbi.com/groups/me/reports/2f7b6c4d-1e09-4a3e-bc9b-1b5a6d5c8f0e/ReportSection");
		Thread.sleep(5000);
		
		//wait for the page to load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//check if the page is loaded
		if (driver.getTitle().contains("Power BI")) {
			System.out.println("Page loaded successfully");
		} else {
			System.out.println("Page not loaded");
		}
		Thread.sleep(5000);
		
		//close the browser
		driver.close();
		System.out.println("Chrome Driver closed successfully");
		Thread.sleep(5000);
		//quit the driver
		driver.quit();
		System.out.println("Driver quit successfully");
		Thread.sleep(5000);
			
		

	}

public static void main(String[] args)
{
    try {
        launch_spog_rcm_report();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        driver.quit();
    }
}
}
