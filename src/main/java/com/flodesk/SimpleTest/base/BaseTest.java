package com.flodesk.SimpleTest.base;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static Scenario scenario;
	private static Logger logger = Logger.getLogger(BaseTest.class.getSimpleName());
	
	@BeforeSuite
	public static void setUpDriverExecutable() {
		String os = System.getProperty("os.name").toLowerCase();
		
		logger.info("****Test execute in os: " + os);
		WebDriverManager.chromedriver().setup();
	}
}
