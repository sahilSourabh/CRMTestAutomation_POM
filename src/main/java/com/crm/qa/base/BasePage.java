package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WaitUtils;
import com.crm.qa.util.WebEventListener;

public class BasePage {
	
	public static Properties prop;
	public static WebDriver driver;
	public static WebDriver decoratedDriver;
	public static WebDriverListener eventListener;
	
	public BasePage() {
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			driver = new ChromeDriver();
		}
		else if( browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
		}
		
//		eventListener = new WebEventListener();
//		decoratedDriver = new EventFiringDecorator<>(eventListener).decorate(driver);
//		driver = decoratedDriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds( TestUtil.PAGE_LOAD_TIMEOUT ));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds( TestUtil.IMPLICIT_WAIT ));
		
		driver.get(prop.getProperty("url"));

		
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {

		WaitUtils.sleep(TestUtil.THREAD_SLEEP);
		driver.quit();
	}
	
	// Lazy initialization of LoginPage
   /* public LoginPage getLoginPage() {
    	
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    } */

}
