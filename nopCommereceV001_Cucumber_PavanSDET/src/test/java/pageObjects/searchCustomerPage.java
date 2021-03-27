package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.BaseClass;
import utilities.WaitHelper;

public class searchCustomerPage extends BaseClass {
	
	WaitHelper waithelper;
	
	public searchCustomerPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waithelper = new WaitHelper(driver);
		
	}
	
	@FindBy(how = How.XPATH, using = "//input[@id='SearchEmail']")
	WebElement textEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	WebElement textFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	WebElement textLastName;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Search']")
	WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;
	
	
	//Actions
	public void setEmail(String email) {
		waithelper.WaitForElement(textEmail, 20);
		textEmail.clear();
		textEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname) {
		waithelper.WaitForElement(textFirstName, 20);
		textFirstName.clear();
		textFirstName.sendKeys(fname);
	}
	
	public void setLastName(String fname) {
		waithelper.WaitForElement(textLastName, 20);
		textLastName.clear();
		textLastName.sendKeys(fname);
	}
	
	public void clickSearchbtn() {
		waithelper.WaitForElement(btnSearch, 20);
		btnSearch.click();
	}
	
	public int getNoOfRows() {
		return(tableRows.size());
	}
	
	public int getNoOfColumns() {
		return(tableColumns.size());
	}
	
	public boolean searchCustomerbyEmail(String email) {
		boolean flag = false;
		
		for(int i = 1;i<=getNoOfRows();i++) {
			String emailId = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td["+2+"]")).getText();
			if(emailId.equals(email)) {
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerbyName(String Name) {
		boolean flag = false;
		
		for(int i = 1;i<=getNoOfRows();i++) {
			String name = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td["+3+"]")).getText();
			String names[] = name.split(" ");
			String Names[] = Name.split(" ");
			
			if(names[0].equals(Names[0]) && names[1].equals(Names[1])) {
				flag = true;
			}
		}
		return flag;
	}

}
