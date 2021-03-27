package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.searchCustomerPage;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public searchCustomerPage searchCust;
	public static Logger logger;
	public Properties Prop;
	
	//Function created for generating random string  for unique email 
	public static String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(6);
		return(generatedString);
		
	}

}
