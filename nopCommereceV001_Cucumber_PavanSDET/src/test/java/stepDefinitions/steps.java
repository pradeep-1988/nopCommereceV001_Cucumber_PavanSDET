package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.searchCustomerPage;

public class steps extends BaseClass {
	
	@Before
	public void setup() throws IOException {
		
		Prop = new Properties();
		FileInputStream configFile = new FileInputStream("config.properties");
		Prop.load(configFile);
		
		logger = Logger.getLogger("nopCommerece"); //Added logger
		PropertyConfigurator.configure("log4j.properties");
		
		String browser = Prop.getProperty("browser");
		
		if(browser.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
			System.setProperty("webdriver.chrome.driver", Prop.getProperty("chromePath"));
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", Prop.getProperty("ffPath"));
			driver = new FirefoxDriver();
		}
		logger.info("*************Launching browser***************");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("*************Opening url***************");
		driver.get(url);
		
	}

	@When("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("*************Entering login details***************");
		lp.setUsername(email);
		lp.setPassword(password);
		
	}

	@When("Clicks on login button")
	public void clicks_on_login_button() throws InterruptedException {
		logger.info("*************Clicking login button***************");
		lp.clickLogin();
		Thread.sleep(3000);
		
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String title) {
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);	
		}else {
			Assert.assertEquals(title, driver.getTitle());	
		}
		
	}

	@When("User clicks on logout")
	public void user_clicks_on_logout() throws InterruptedException {
		logger.info("*************Clicking logout button***************");
		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("Closes browser")
	public void closes_browser() {
		logger.info("*************closing browser***************");
		driver.close();
		
	}
	
	//Customer feature step definitions-------
	
	@Then("User can view dashboard")
	public void user_can_view_dashboard() {
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());

	}
	@When("User cliks on Customers menu")
	public void user_cliks_on_customers_menu() {
		addCust.click_CustomersMenu();

	}
	@When("cliks on Customers menu item")
	public void cliks_on_customers_menu_item() {
		addCust.click_CustomersMenuItem();

	}
	@When("cliks on Add new button")
	public void cliks_on_add_new_button() {
		addCust.click_AddNewButton();

	}
	@Then("User can view add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());

	}
	@When("user enters customer info")
	public void user_enters_customer_info() {
		String email = randomString()+"@gamil.com";
		addCust.setEmail(email);
		addCust.setPassword("123456");
		addCust.setFirstName("Rohit");
		addCust.setLastName("Kumar");
		addCust.setGender("Male");
		addCust.setDOB("3/27/2021");
		addCust.setCompany("XYZ Info Ltd");
		//addCust.setCustomerRole("Guest");
		addCust.setManagerOfVendor("Vendor 1");
		addCust.setAdminContent("This is new customer");

	}
	@When("clicks on save button")
	public void clicks_on_save_button() throws InterruptedException {
		addCust.clickOnSave();
		Thread.sleep(3000);
	}
	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("Body")).getText().contains(string));
	}
	
	@When("Enters customer email")
	public void enters_customer_email() {
		searchCust = new searchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");

	}
	@When("click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearchbtn();
		Thread.sleep(3000);

	}
	@Then("User should found email in the search table")
	public void user_should_found_email_in_the_search_table() {
		Assert.assertEquals(true, searchCust.searchCustomerbyEmail("victoria_victoria@nopCommerce.com"));

	}
	
	@When("Enters customer first name")
	public void enters_customer_first_name() {
		searchCust = new searchCustomerPage(driver);
		searchCust.setFirstName("Victoria");

	}
	@When("Enters customer last name")
	public void enters_customer_last_name() {
		searchCust.setLastName("Terces");

	}
	
	@Then("User should found name in the search table")
	public void user_should_found_name_in_the_search_table() {
		Assert.assertEquals(true, searchCust.searchCustomerbyName("Victoria Terces"));

	}

}
