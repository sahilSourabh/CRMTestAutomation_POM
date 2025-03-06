package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.BasePage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends BasePage {

	public LoginPage loginPage;
	public HomePage homePage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initialization();	
		loginPage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM software for customer relationship management, sales, and support.");
	}

	@Test(priority=2)
	public void crmLogoTest() {
		
		boolean value = loginPage.validateCrmLogo();
		Assert.assertTrue(value);
	}

	@Test(priority=3)
	public void loginTest() {
		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

}
