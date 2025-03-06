package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BasePage;

public class HomePage extends BasePage{
	
	
	@FindBy( xpath ="//td[contains(text(),'User: Gagan Khanna')]")
	WebElement userNameLabel;

	@FindBy(xpath ="//a[text()='Contacts']")
	WebElement contactLink;
	
	@FindBy(xpath ="//a[text()='Deals']")
	WebElement dealsLink;
	
	@FindBy(xpath ="//a[text()='Tasks']")
	WebElement tasksLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean verifyUserName() {
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactLink.click();
		return new ContactsPage();
	}
	
	public void clickOnDealsLink() {
		dealsLink.click();
	}
	
	public void clickOnTasksLink() {
		
		tasksLink.click();
		
	}
}
