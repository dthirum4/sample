package Utility;

import org.testng.TestNG;

import Classfiles.Launch_Chromedriver;
import Utility.Report;

public class Testng_Class_File {
	
	public static void main(String[] args) throws Exception
	{
		Thread.sleep(5000);
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { Utility.Report.class });
		testng.run();

	}

}
