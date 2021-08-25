package com.flodesk.pom;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class EmailPage extends EmailActionsPage {
	private Logger logger = Logger.getLogger(EmailPage.class.getSimpleName());

	private static final String NEW_EMAIL = "//a[@href='/emails/templates']";
	private static final String FOLDER_TAB = "//a[@href='/emails/folders']";
	private static final String MY_EMAIL_TAB = "//a[@href='/emails']";
	private static final String EMAIL_PAGE = ".page-campaigns";

	@Step("Click on new email button")
	public void clickNewEmail() {
		logger.info("Click on new email button");
		WebElement ele = findElementByXpath(NEW_EMAIL);
		ele.click();
	}

	@Step("Click on folder tab")
	public void clickFolderTab() {
		logger.info("Click on folder tab");
		WebElement ele = findElementByXpath(FOLDER_TAB);
		ele.click();
		waitForClassLoader();
	}

	@Step("Click on my email tab")
	public void clickMyEmailTab() {
		logger.info("Click on my email tab");
		WebElement ele = findElementByXpath(MY_EMAIL_TAB);
		ele.click();
		waitForClassLoader();
	}

	@Step("Check email page loaded")
	public boolean isEmailPageLoaded() {
		logger.info("Check email page loaded");
		return elementIsPresent(By.cssSelector(EMAIL_PAGE));
	}
}
