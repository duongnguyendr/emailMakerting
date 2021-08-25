package com.flodesk.pom;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.flodesk.SimpleTest.base.BasePage;

import io.qameta.allure.Step;

public class EmailActionsPage extends BasePage {
	private Logger logger = Logger.getLogger(EmailActionsPage.class.getSimpleName());

	private static final String EMAIL_LIST = ".campaign-list__item";
	private static final String EMAIL_NAME = ".text-editable__button";

	private static final String EMAIL_EDIT_BTN = "//a[@class='campaign-item__button']";
	private static final String OPACITY_VALUE = "opacity: 1;";

	// Email actions
	private static final String EMAIL_ACTIONS = "following-sibling::div[@class='campaign-item__actions']";
	private static final String ORGANIZE_ACTION = "//li[@role='presentation']//span[text()='Organize']";
	private static final String ADD_TO_FOLDER_ACTION = "//li[@class='noactive']/a[contains(@href, 'action=addToFolder')]";
	private static final String MOVE_TO_FOLDER_ACTION = "//li[@class='noactive']/a[contains(@href, 'action=moveToFolder')]";
	private static final String REMOVE_FROM_FOLDER_ACTION = "//li[@class='noactive']/a[contains(@href, 'action=removeFromFolder')]";

	@Step("Click on ... of email: {0}")
	public void clickEmailActions(String emailTitle) {
		logger.info("Click on ... of email: " + emailTitle);
		List<WebElement> elements = findElementsByXpath(EMAIL_EDIT_BTN);
		for (WebElement ele : elements) {
			String label = ele.getAttribute("aria-label");
			if (label.endsWith(emailTitle)) {
				hoverOnElement(ele);
				WebElement actions = ele.findElement(By.xpath(EMAIL_ACTIONS));
				actions.click();
				break;
			}
		}
	}

	@Step("Click add to folder option")
	public void clickAddToFolderOption() {
		logger.info("Click add to folder option");
		WebElement organizeEle = findElementByXpath(ORGANIZE_ACTION);
		hoverOnElement(organizeEle);
		findElementByXpath(ADD_TO_FOLDER_ACTION).click();
	}

	@Step("Click move to folder option")
	public void clickMoveToFolderOption() {
		logger.info("Click move to folder option");
		WebElement organizeEle = findElementByXpath(ORGANIZE_ACTION);
		hoverOnElement(organizeEle);
		findElementByXpath(MOVE_TO_FOLDER_ACTION).click();
	}

	@Step("Click remove email from folder option")
	public void clickRemoveFromFolderOption() {
		logger.info("Click remove email from folder option");
		WebElement organizeEle = findElementByXpath(ORGANIZE_ACTION);
		hoverOnElement(organizeEle);
		findElementByXpath(REMOVE_FROM_FOLDER_ACTION).click();
	}

	@Step("Check email: {0} is existed")
	public boolean isEmailExisted(String email) {
		logger.info("Check email is existed: " + email);
		List<WebElement> elements = findElementsByCss(EMAIL_LIST);
		for (WebElement ele : elements) {
			waitForAttributeOf(ele, "style", OPACITY_VALUE);
			WebElement emailEle = ele.findElement(By.cssSelector(EMAIL_NAME));
			String text = emailEle.getText();
			System.out.println("Email name: " + text);
			if (emailEle.getText().equals(email)) {
				return true;
			}
		}
		return false;
	}
}
