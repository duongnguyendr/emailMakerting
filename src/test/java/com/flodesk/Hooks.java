package com.flodesk;

import static com.flodesk.SimpleTest.helpers.DriverHelper.closeAllWebDriver;
import static com.flodesk.SimpleTest.helpers.DriverHelper.getDriver;
import static com.flodesk.SimpleTest.helpers.DriverHelper.initChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.flodesk.SimpleTest.base.BaseTest;

public class Hooks extends BaseTest{
	
	private static final String URL = "https://app.staging.flodesk.com/";
	
	@BeforeMethod
	public void setup() {
		initChromeDriver();
		getDriver().get(URL);
	}
	
	@AfterMethod
	public void tearDown() {
		closeAllWebDriver();
	}
}
