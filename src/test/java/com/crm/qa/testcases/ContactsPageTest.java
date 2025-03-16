package com.crm.qa.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.BasePage;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WaitUtils;

public class ContactsPageTest extends BasePage{
	
	public LoginPage loginPage;
	public HomePage homePage;
	public TestUtil testUtil;
	public ContactsPage contactsPage;
	
	String sheetName ="contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
		//WaitUtils.sleep(2);
	}
	
	@Test(priority=1)
	public void contactLabelTest() {
		boolean value = contactsPage.verifyContactLabel();
		Assert.assertTrue(value,"Contact label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactTest() {
		contactsPage.selectContactByName("01TestAutoFname 01TestAutoFname");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactByName("01TestAutoFname 01TestAutoFname");
		contactsPage.selectContactByName("AADavid Cris");
	}
	
	
	@Test(priority=4, dataProvider="getData")
	public void createContactTest(String title, String firstName, String lastName, String companyName) {
		
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title,firstName,lastName,companyName);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		return TestUtil.getTestData(sheetName);
	}
	

}
