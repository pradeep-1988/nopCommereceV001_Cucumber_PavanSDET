package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import stepDefinitions.BaseClass;

public class AddCustomerPage extends BaseClass {

	
	public AddCustomerPage(WebDriver driver) {
		this.driver=driver;
	}
	
	By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuItem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	By btnAddnew = By.xpath("//a[normalize-space()='Add new']");
	By textEmail = By.xpath("//input[@id='Email']");
	By textPassword = By.xpath("//input[@id='Password']");
	By textFName = By.xpath("//input[@id='FirstName']");
	By textLname = By.xpath("//input[@id='LastName']");
	
	By rdMaleGender = By.xpath("//input[@id='Gender_Male']");
	By rdFemaleGender = By.xpath("//input[@id='Gender_Female']");
	By textDOB = By.xpath("//input[@id='DateOfBirth']");
	
	By textCompany = By.xpath("//input[@id='Company']");
	By chkBoxtax = By.xpath("//input[@id='IsTaxExempt']");
	
	By textCustomerRoles = By.xpath("//div[@class='k-widget k-multiselect k-multiselect-clearable k-state-hover k-state-focused k-state-border-down']//div[@role='listbox']");
	
	
	By listItemAdmin = By.xpath("//li[normalize-space()='Administrators']");
	By listItemModerator = By.xpath("//li[normalize-space()='Forum Moderators']");
	By listItemGuest = By.xpath("//li[normalize-space()='Guests']");
	By listItemRegistered = By.xpath("//li[@id='e75be4cd-8818-4af4-ab8c-c2315a43d2ae']");
	By listItemVendors = By.xpath("//li[contains(text(),'Vendors')]");
	
	By drpdnManager = By.xpath("//select[@id='VendorId']");
	By textAdminContent = By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave = By.xpath("//button[@name='save']");
	
	//Actions/Methods for each locator
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void click_CustomersMenu() {
		driver.findElement(lnkCustomers_menu).click();
	}
	
	public void click_CustomersMenuItem() {
		driver.findElement(lnkCustomers_menuItem).click();
	}
	
	public void click_AddNewButton() {
		driver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email) {
		driver.findElement(textEmail).sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		driver.findElement(textPassword).sendKeys(pwd);
	}
	
	public void setFirstName(String name) {
		driver.findElement(textFName).sendKeys(name);
	}
	
	public void setLastName(String name) {
		driver.findElement(textLname).sendKeys(name);
	}
	
	public void setCustomerRole(String role) {
		
		driver.findElement(textCustomerRoles).click();
		WebElement listItem = null;
		if(role.equals("Administrators")) {
			listItem = driver.findElement(listItemAdmin);
		}else if(role.equals("Guests")) {
			listItem = driver.findElement(listItemGuest);
		}else if(role.equals("Registered")) {
			listItem = driver.findElement(listItemRegistered);
		}else if(role.equals("Moderator")) {
			listItem = driver.findElement(listItemModerator);
		}else if(role.equals("Vendors")) {
			listItem = driver.findElement(listItemVendors);
		}
		listItem.click();
		/*
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", listItem);
		*/
		
	}
	
	public void setManagerOfVendor(String value) {
		Select drpdwn = new Select(driver.findElement(drpdnManager));
		drpdwn.selectByVisibleText(value);
		
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male")) {
			driver.findElement(rdMaleGender).click();
		}else {
			driver.findElement(rdFemaleGender).click();
		}
	}
	
	public void setDOB(String DOB) {
		driver.findElement(textDOB).sendKeys(DOB);
	}
	
	public void setCompany(String company) {
		driver.findElement(textCompany).sendKeys(company);
	}
	
	public void setAdminContent(String content) {
		driver.findElement(textAdminContent).sendKeys(content);
	}
	
	public void clickOnSave() {
		driver.findElement(btnSave).click();;
	}
	

}
