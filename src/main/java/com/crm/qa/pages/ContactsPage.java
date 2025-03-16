package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import com.crm.qa.base.BasePage;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends BasePage {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactLabel;

	@FindBy(css = "select[name='title']")
	WebElement titleDropdown;

	@FindBy(css = "input[id='first_name']")
	WebElement fName;

	@FindBy(css = "input[id='surname']")
	WebElement lName;

	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveButton;

	@FindBy(xpath = "//input[@name='client_lookup']")
	WebElement company;

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactLabel() {
		return contactLabel.isDisplayed();
	}

	public void selectContactByName(String name) {

		driver.findElement(By.xpath("//a[text()='"+ name +"']/parent::td[@class='datalistrow']"
				+ "/preceding-sibling::td[@class='datalistrow']/input[@type='checkbox' and @name='contact_id']"))
				.click();
	}

	public void createNewContact(String title, String firstName, String lastName, String companyName) {

		TestUtil.selectByValue(titleDropdown, title);
		fName.sendKeys(firstName);
		lName.sendKeys(lastName);
		company.sendKeys(companyName);
		saveButton.click();
	}
	
	

}
