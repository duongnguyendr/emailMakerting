package com.flodesk.pom;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.flodesk.SimpleTest.base.BasePage;

import io.qameta.allure.Step;

public class AddEmailToFolderModalPage extends BasePage {
	private Logger logger = Logger.getLogger(AddEmailToFolderModalPage.class.getSimpleName());

	private static final String EMAIL_ACTIONS_MODAL = ".modal.is-open";
	private static final String EMAIL_ACTION_MODAL_TITLE = ".section__title";
	private static final String SEARCH_FOLDER = ".form-search input";
	private static final String CREATE_FOLDER_BTN = ".folder-picker__body ul>li>button";
	private static final String LIST_FOLDER = ".folder-picker__body ul>div button";
	private static final String INPUT_FOLDER_NAME = ".folder-picker__body ul>li.is-create-input input";
	private static final String ADD_SUCCESS_MESSAGE = ".notification.is-type-success";
	private static final String CREATE_FOLDER_BUTTON = ".folder-picker__item__name.is-create-button";
	private static final String CURRENT_FOLDER = ".folder-picker__item__indicator";
	private static final String FOLDER_NAME = ".folder-picker__item__name";
	private static final String REMOVE_POPUP_MESSAGE = ".section__subtitle div";
	private static final String CONFRIM_BUTTON = "button[data-testid='confirm-box__confirm']";

	@Step("Click create new folder button")
	public void clickCreateNewFolderButton() {
		logger.info("Click create new folder button");
		WebElement ele = findElementByCss(CREATE_FOLDER_BUTTON);
		ele.click();
	}

	@Step("Check Popup displayed")
	public boolean isModalDisplay() {
		logger.info("Check popup display");
		waitUntilElementVisibility(By.cssSelector(EMAIL_ACTIONS_MODAL));
		return elementIsPresent(By.cssSelector(EMAIL_ACTIONS_MODAL));
	}

	@Step("Input into search field with value: {0}")
	public void inputSearchField(String value) {
		logger.info("Input into search field with value: " + value);
		WebElement ele = findElementByCss(SEARCH_FOLDER);
		ele.sendKeys(value);
	}

	@Step("Click add new folder button")
	public void clickAddNewFolder() {
		logger.info("Click add new folder");
		WebElement ele = findElementByCss(CREATE_FOLDER_BTN);
		ele.click();
	}

	@Step("Get title of popup")
	public String getTitleOfPopUp() {
		logger.info("Get title of popup");
		WebElement popupEle = findElementByCss(EMAIL_ACTIONS_MODAL);
		WebElement titleEle = popupEle.findElement(By.cssSelector(EMAIL_ACTION_MODAL_TITLE));
		String title = titleEle.getText();
		return title;
	}

	@Step("Waiting until remove email popup display")
	public void waitForRemoveEmailPopup() {
		logger.info("Waiting until remove email popup display");
		waitForAttributeOf(findElementByCss(EMAIL_ACTIONS_MODAL), "class", "confirm-box");
	}

	@Step("Input folder name with: {0}")
	public void inputFolderName(String fName) {
		logger.info("Input folder name: " + fName);
		WebElement ele = findElementByCss(INPUT_FOLDER_NAME);
		ele.sendKeys(fName);
	}

	@Step("Click on folder name: {0}")
	public void clickOnFolderNameInList(String fName) {
		logger.info("Click on folder name: " + fName);
		List<WebElement> elements = findElementsByCss(LIST_FOLDER);
		for (WebElement ele : elements) {
			WebElement folderName = ele.findElement(By.cssSelector("span"));
			if (folderName.getText().equals(fName)) {
				ele.click();
				break;
			}
		}
	}

	@Step("Get current folder name")
	public String getCurrentFolder() {
		logger.info("Get current folder name");
		List<WebElement> elements = findElementsByCss(LIST_FOLDER);
		for (WebElement ele : elements) {
			WebElement currentFolder = ele.findElement(By.cssSelector(CURRENT_FOLDER));
			if (currentFolder.getText().equals("Current folder")) {
				WebElement folderName = ele.findElement(By.cssSelector(FOLDER_NAME));
				return folderName.getText();

			}
		}
		return "";
	}

	@Step("Get message after add email to folder successfully")
	public String getAddEmailToFolderSuccessMsg() {
		logger.info("Get message after add email to folder successfully");
		waitUntilElementVisibility(By.cssSelector(ADD_SUCCESS_MESSAGE));
		WebElement ele = findElementByCss(ADD_SUCCESS_MESSAGE);
		String msg = ele.getText();
		return msg;
	}

	@Step("Get message in remove email from folder popup")
	public String removeEmailFromFolderMessage() {
		logger.info("Get message in remove email from folder popup");
		WebElement ele = findElementByCss(REMOVE_POPUP_MESSAGE);
		return ele.getText();
	}

	@Step("Click remove button")
	public void clickRemoveButton() {
		logger.info("Click remove button");
		WebElement ele = findElementByCss(CONFRIM_BUTTON);
		ele.click();
	}
}
