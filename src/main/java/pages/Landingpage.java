package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browserImplementation.BrowserConfig_Reader;
import configReader.ConfigReader;

public class Landingpage {

       ConfigReader cr;
       WebDriver driver;
	   BrowserConfig_Reader br;
	  // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
// Initilize the browser
   public Landingpage(WebDriver driver)
   {
	   this.driver=driver;
   }
   
   //used for succesful sign in user and returns the current Url
	public String  Sign_In() throws IOException, InterruptedException
	{
		
		br=new BrowserConfig_Reader(driver);
	    cr=new ConfigReader();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    WebElement searchbox = driver.findElement( (By.xpath(cr.get_InitialSearch())));
		searchbox.sendKeys("Testing");
		searchbox.sendKeys(Keys.ENTER);
		System.out.println("Opened Application...");
		driver.findElement(By.cssSelector(cr.get_IdofEmail())).sendKeys(cr.get_CorrectEmail());
		WebElement password= driver.findElement(By.cssSelector(cr.get_IdofPassword()));	
		password.sendKeys(cr.get_correctPassword());
		WebElement loginbtn=driver.findElement((By.xpath(cr.get_InitialLoginbutton())));
		loginbtn.click();
		String currentUrl=driver.getCurrentUrl();
		return currentUrl;
	}
	//return the text coming after searching the content
	public String Text() throws InterruptedException
	{
		Thread.sleep(3000);
		WebElement Insearch= driver.findElement(By.xpath(cr.get_search()));
		Insearch.sendKeys(cr.get_Content());
		Insearch.sendKeys(Keys.ENTER);
		WebElement result= driver.findElement(By.xpath(cr.get_ResultText()));
		String ACtext= result.getText();
	    return ACtext;
		
	}
	//Logout activity executed
	public void Logout() throws InterruptedException
	{
		Thread.sleep(3000);
		WebElement Nametag= driver.findElement(By.xpath(cr.get_Nametag()));
		Nametag.click();
		 WebElement logout= driver.findElement(By.xpath(cr.get_Logout()));
		 logout.click();	 
	}
	
	//returns the login button
	public WebElement get_Loginbutton() throws InterruptedException
	{
		Thread.sleep(3000);
	    WebElement checklogin=driver.findElement(By.xpath(cr.get_CheckLoginButton()));
	    return checklogin; 
	}
	//Used to take the screenshot for invalid email.
	public void checkEmailText() throws InterruptedException, IOException
	{
		 WebElement signupButton = driver.findElement( By.xpath(cr.get_signupEmailButton()));
         signupButton.click();
		 driver.findElement(By.name(cr.get_Nameattforsignup())).sendKeys(cr.get_Signupnamecontent());
		 driver.findElement(By.xpath(cr.get_SecondEmail())).sendKeys(cr.get_Incorrect_email());
		 Thread.sleep(2000);
		 File src= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 File dest= new File("C:\\Users\\2478571\\eclipse-workspace\\Interim_project\\screenshot\\link.png");
		 dest.getParentFile().mkdirs();
		 FileUtils.copyFile(src,dest);
	}
	

}
