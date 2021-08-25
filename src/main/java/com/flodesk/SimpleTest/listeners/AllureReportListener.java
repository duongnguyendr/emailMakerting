package com.flodesk.SimpleTest.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import static com.flodesk.SimpleTest.helpers.DriverHelper.getDriver;

public class AllureReportListener implements ITestListener {

	public static String getTestMethodName(ITestResult testResult) {
		return testResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenShot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveLog(String msg) {
		return msg;
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
	   System.out.println("Failed step: " + getTestMethodName(result));
	   
	   WebDriver driver = getDriver();
	   if(driver instanceof WebDriver) {
		   System.out.println("screenshot has taken");
		   saveScreenShot(driver);
	   }
	   saveLog(getTestMethodName(result) + " failed and screenshot taken!");
	 }
}
