package com.crm.qa.util;

public class WaitUtils {
	
	public static void sleep(long seconds) {
		
		try {
			Thread.sleep(seconds * 1000);
		} 
		catch (InterruptedException e) {
//			e.printStackTrace();
			Thread.currentThread().interrupt(); 	// Restore interrupted status
            System.out.println("Thread interrupted: " + e.getMessage());
		}	
	}

}
