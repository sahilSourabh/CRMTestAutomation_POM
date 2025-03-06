package com.crm.qa.util;

import com.crm.qa.base.BasePage;

public class TestUtil extends BasePage{

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
	public static long THREAD_SLEEP = 2;
	
	
	public static void switchToFrame() {
		driver.switchTo().frame("mainpanel");	
	}
}
