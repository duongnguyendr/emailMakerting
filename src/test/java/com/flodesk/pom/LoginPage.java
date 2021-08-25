package com.flodesk.pom;

import org.apache.log4j.Logger;

import com.flodesk.SimpleTest.base.BasePage;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	private Logger logger = Logger.getLogger(LoginPage.class.getSimpleName());

	private static final String USERNAME = "//input[@name='email']";
	private static final String PASSWORD = "//input[@name='password']";
	private static final String LOGIN_BUTTON = "//button[@data-testid='form-anonymous__login-button']";

	@Step("Input username: {0}")
	public void inputUserName(String username) {
		logger.info("Input user name: " + username);
		findElementByXpath(USERNAME).sendKeys(username);
	}

	@Step("Input password: {0}")
	public void inputPassword(String password) {
		logger.info("Input password: " + password);
		findElementByXpath(PASSWORD).sendKeys(password);
	}

	@Step("Click login button")
	public void clickLoginButton() {
		logger.info("Click login button");
		findElementByXpath(LOGIN_BUTTON).click();
		waiForBtnLoaderNotExisted();
	}

}
