package com.flodesk.services;

import org.junit.Assert;

import com.flodesk.SimpleTest.model.User;
import com.flodesk.pom.EmailPage;
import com.flodesk.pom.LoginPage;

import io.qameta.allure.Step;

public class LoginService {
	LoginPage loginPage = new LoginPage();
	EmailPage emailPage = new EmailPage();

	@Step("Login into system")
	public void login(User user) {
		loginPage.inputUserName(user.getUsername());
		loginPage.inputPassword(user.getPassword());
		loginPage.clickLoginButton();
		boolean isPresent = emailPage.isEmailPageLoaded();
		Assert.assertTrue("Cannot load email page", isPresent);
	}

}
