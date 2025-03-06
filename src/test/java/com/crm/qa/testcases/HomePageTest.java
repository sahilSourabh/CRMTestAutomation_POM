package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.BasePage;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WaitUtils;

public class HomePageTest extends BasePage {
	
	public HomePage homePage;
	public LoginPage loginPage;
	public ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.switchToFrame();
	}
	
	@Test(priority = 1)
	public void homePageTitleTest() {
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, "CRMPRO", "HomePage title not matched");
	}
	
	@Test(priority = 2)
	public void userNameTest() {
		boolean value = homePage.verifyUserName();
		Assert.assertTrue(value, "user name is not found");
	}
	
	@Test(priority=3)
	public void gotoContactsPageTest() {
		contactsPage = homePage.clickOnContactsLink();
	}

}
