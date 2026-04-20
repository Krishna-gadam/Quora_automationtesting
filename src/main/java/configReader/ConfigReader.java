package configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;


public class ConfigReader {

	Properties pro;
	FileInputStream fis;
	
	public ConfigReader() throws IOException
	
	{ 
	 fis= new FileInputStream("C:\\Users\\2478571\\eclipse-workspace\\Interim_project\\src\\main\\resources\\ObjectRepo\\Object.properties");
     pro =new Properties();
	 pro.load(fis);
	}
	
public String get_Url()
{
	return pro.getProperty("BaseUrl");
	
}

public String get_InitialSearch()
{
	return pro.getProperty("Initialtesting");
}

public String get_IdofEmail()
{
	return pro.getProperty("Emailby_id");
	
}

public String get_CorrectEmail()
{
	return pro.getProperty("correctEmail");
}


public String get_IdofPassword()
{
	return pro.getProperty("password_id");
	
}


public String get_correctPassword()
{
	return pro.getProperty("correctPassword");
}

public String get_InitialLoginbutton()
{
	return pro.getProperty("Loginbutton");
	
}

public String get_Content()
{
	return pro.getProperty("content");
	
}

public String get_Expectedcontent()
{
	return pro.getProperty("EXtext");
	
}

public String get_Nametag()
{
	return pro.getProperty("Nametag");
	
}

public String get_Logout()
{
	return pro.getProperty("Logout");
	
}

public String get_SignupwithEmail()
{
	return pro.getProperty("signupwithemail");
	
}

public String get_Nameattforsignup()
{
	return pro.getProperty("signup_name");
	
}

public String get_Signupnamecontent()
{
	return pro.getProperty("sign_name_content");
	
}

public String get_ResultText()
{
	return pro.getProperty("Result");
	
}

public String get_search()
{
	return pro.getProperty("SearchQuora");
}

public String get_CheckLoginButton()
{
	return pro.getProperty("check_login");
}


public String get_signupEmailButton()
{
	return pro.getProperty("signupwithemail");
}

public String get_SecondEmail()
{
	return pro.getProperty("Email_second");
}

public String get_Incorrect_email()
{
	return pro.getProperty("Incorrect_email");
}


public String get_FirstSuggestion()
{
	return pro.getProperty("FirstSuggestion");
}


	
	
}
