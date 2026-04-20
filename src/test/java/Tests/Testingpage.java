package Tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import browserImplementation.BrowserConfig_Reader;
import configReader.ConfigReader;
import pages.Landingpage;

//Created the objects of all classes
public class Testingpage {
	   private WebDriver driver;
	   BrowserConfig_Reader br;
	   Landingpage lp;
	   ConfigReader cr;
	   private ExtentReports extent;
	   private ExtentTest test;

	   
	   @BeforeClass
	   public void setupReport() {
	        // Create HTML report file
	        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
	        spark.config().setDocumentTitle("Automation Test Report");
	        spark.config().setReportName("Selenium Test Results");

	        // Attach reporter
	        extent = new ExtentReports();
	        extent.attachReporter(spark);
	        extent.setSystemInfo("Tester", "QA Engineer");
	        extent.setSystemInfo("Environment", "QA");
	    }
	
	@BeforeTest
	//Implement the browser based on user input and lauch the Url
public void Browser_Launch() throws InterruptedException
{
		br=new BrowserConfig_Reader(driver);
		int browser=br.get_Browser();
		if(browser==1)
		{
		ChromeOptions options;
		options=new ChromeOptions();
		options.addArguments("--disable-blink-features=AutomationControlled");
		driver=br.get_chrome(options);
		}
		else
		{
		EdgeOptions options;
		options=new EdgeOptions();
		options.addArguments("--disable-blink-features=AutomationControlled");
		driver=br.get_Edge(options);
			
		}
		br.Launch_Url();
		Thread.sleep(15000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		lp=new Landingpage(driver);
				
}
	
	@Test
	//Execute signIn and verify for successful login
	public void Check_SignIn() throws IOException, InterruptedException
	{
		 test = extent.createTest("Sign In test");
		  String currentUrl=lp.Sign_In();
		  String ExUrl="https://www.quora.com/";
		 
		  Assert.assertEquals(currentUrl,ExUrl,"Url not Matched");
		  
		  
		  test.info("sign in succesful");
		  System.out.print("sign in successful");
		
	}
	
	// check and verify the text 
  @Test(dependsOnMethods="Check_SignIn()")
	public void Check_Text() throws IOException, InterruptedException
{
	      test = extent.createTest("Verifying text coming after searhing a topic");
	      cr=new ConfigReader();
          String ACtext=lp.Text();
          String EXtext=cr.get_Expectedcontent();
          try
          {
	      Assert.assertEquals(ACtext, EXtext);
	      test.pass("Text verified successfully");
          }catch(AssertionError e)
          {
          test.fail("Text did't match"); 
          throw e;
          }
	      System.out.println("Text verified");
      // 	Reporter.log("Check text-passed- Results for _____");
}
  
  // check login button is disabled at beggining,when navigate to signwithemail option
     @Test(dependsOnMethods= {"Check_Text()"})
	public void Check_SignupButtonEnabled() throws InterruptedException
{
    	  test = extent.createTest("Checking login button is enabled before login");
	      lp.Logout();
	      WebElement checkLoginButton=lp.get_Loginbutton();
	      try
	      {
	      Assert.assertFalse(checkLoginButton.isEnabled());
	      test.pass("Login button verified");
	      }catch(AssertionError e)
	      {
	      test.fail("Login button not working properly."); 
	      throw e;
	      }
	     // Reporter.log("Login Button verified");
	      System.out.println("login button verified");
	
}

     //check text message for invalid email by clicking screenshot
@Test(dependsOnMethods="Check_SignupButtonEnabled()")     
public 	void CheckTextforInvalidEmail() throws InterruptedException, IOException
{
	   test = extent.createTest("Checking result text after writing the wrong email");
	   try
	   {
	     lp.checkEmailText();
	     test.pass("Filling Incorrect Email and  check  obtained the Result text- passed"); 
	   }catch(Exception e)
	   {
		   
		   throw e;
	   }
	     System.out.println("Filling Incorrect Email and  check  obtained the Result text- passed");
	   
	
}
//used for close the browser.
@AfterTest
public void  Browser_close()
{
	      br.Close_Browser();
	
}
	

@AfterSuite
public void flushReport() {
    extent.flush(); // Write report to file
}
	
}
