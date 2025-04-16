package Classfiles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample_Launch_Browser {

		
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
			System.out.println("Inside driver func()");
			Thread.sleep(5000);
			driver.manage().window().maximize();
			driver.get("https://google.com/");
		}
			finally 
			{
			Thread.sleep(5000);
			driver.close();
			System.out.println("Driver closed successfully");
			}
		}


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		func();

	}

}
