
package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BasePage;

public class LoginPage extends BasePage {
	
	
	@FindBy(css ="input[name='username']")
	WebElement username;

	@FindBy(css ="input[name='password']")
	WebElement password;

	@FindBy(css ="input[value*='Login']")
	WebElement loginBtn;

	@FindBy(xpath ="//a[contains(text(),'Sign Up')]")
	WebElement signUpBtn;

	@FindBy(xpath ="//img[contains(@alt,'Free CRM Software for customer relationship management')]")
	WebElement crmLogo;


	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateCrmLogo() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) {

		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();

		return new HomePage();
	}

}
