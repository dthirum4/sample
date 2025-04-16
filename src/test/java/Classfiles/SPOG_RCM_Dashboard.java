package Classfiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Classfiles.Launch_Chromedriver;

public class SPOG_RCM_Dashboard extends Launch_Chromedriver{
	public static int ans=0;
/*	public int spog_rcm_dashboard() throws Exception 
	{
		try 
		{
			Launch_Chromedriver lc=new Launch_Chromedriver();
			//click_browse
			
			Thread.sleep(35000);
			System.out.println("going inside browser button");
			WebElement browser=driver.findElement(By.xpath("//button[contains(@aria-label, 'Browse')]"));
			browser.click();
			
			//submit_SPOG_RCM_link and find load time
			
			Thread.sleep(10000);
			System.out.println("going inside SPOG link");
			long startTime_SPOG_RCM_Ambulatory_Page = System.currentTimeMillis();
			WebElement SPOG_LINK=driver.findElement(By.linkText("SPoG - RCM Ambulatory"));
			SPOG_LINK.click();
			
			new WebDriverWait(driver, 10).until(ExpectedConditions.
					presenceOfElementLocated(By.linkText("SPoG - RCM Ambulatory")));
			long endTime_SPOG_RCM_Ambulatory_Page = System.currentTimeMillis();

			long totalTime_SPOG_RCM_Ambulatory_Page = endTime_SPOG_RCM_Ambulatory_Page - startTime_SPOG_RCM_Ambulatory_Page;

			System.out.println("Total Page [SPOG_RCM_Ambulatory_Page] Load Time: " + totalTime_SPOG_RCM_Ambulatory_Page + " milliseconds");
			ans=1;
			
		}
		finally 
		{
		//driver.close();
		}
		return ans;
	} */
}

