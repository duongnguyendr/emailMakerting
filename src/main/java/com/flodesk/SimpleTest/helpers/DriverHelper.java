package com.flodesk.SimpleTest.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHelper {
	protected static WebDriver driver;
	
	protected static int defaultTimeOut = 10;
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	protected static List<WebDriver> listDrivers = new ArrayList<WebDriver>();
		
	
	public static void initChromeDriver() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		listDrivers.add(driver);
	}
	
	public static void closeAllWebDriver() {
		for (WebDriver dv : listDrivers) {
			dv.close();
			dv.quit();
		}
		listDrivers.clear();
	}
	
	public static int getDefaultTimeout() {
		return defaultTimeOut;
	}
}
