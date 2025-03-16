package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.BasePage;
import com.crm.qa.util.WaitUtils;

public class HomePage extends BasePage {

	@FindBy(xpath = "//td[contains(text(),'User: Gagan Khanna')]")
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactLink;

	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;

	@FindBy(xpath = "//a[text()='Deals']")
	WebElement dealsLink;

	@FindBy(xpath = "//a[text()='Tasks']")
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

	public void clickOnNewContactLink() {

		Actions action = new Actions(driver);
		action.moveToElement(contactLink).build().perform();

		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", newContactLink);

		//newContactLink.click();
	}

	public void clickOnDealsLink() {
		dealsLink.click();
	}

	public void clickOnTasksLink() {
		tasksLink.click();

	}
}
