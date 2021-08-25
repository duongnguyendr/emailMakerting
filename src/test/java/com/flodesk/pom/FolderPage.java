package com.flodesk.pom;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.flodesk.SimpleTest.base.BasePage;

import io.qameta.allure.Step;

public class FolderPage extends BasePage {
	private Logger logger = Logger.getLogger(FolderPage.class.getSimpleName());

	private static final String FOLDER_PAGE = ".page-folders";
	private static final String NEW_FOLDER_BTN = "//a[contains(@href,'/emails/folders#action=add')]";
	private static final String FOLDER_LIST = "//div[@class='folder-list__item']";
	private static final String VIEW_FOLDER_BTN = ".//a[@class='folder-item__button']";
	private static final String FOLDER_NAME_BTN = ".//div[@class='folder-item__name']//h4[contains(@style, 'width')]/button";
	private static final String OPACITY_VALUE = "opacity: 1;";
	private static final String NEXT_BUTTON = ".form__footer__item button[type='submit']";
	private static final String CANCEL_BUTTON = ".form__footer__item button[type='button']";
	private static final String SKIP_BUTTON = ".modal__footer__item button[type='button']";
	private static final String SAVE_BUTTON = ".modal__footer__item button[type='submit']";
	private static final String FOLDER_NAME_FIELD = "input#name";
	private static final String ADD_EMAIL_TO_FOLDER_MODAL_TITLE = ".modal__title";
	private static final String MODAL_TITLE = ".modal.is-open.has-form .section__title";
//	private static final String EMAIL_NAME = ".campaign-item__name__value";
	private static final String DUPLICATED_ERR_MSG = ".notification.is-type-error";
	private static final String EMPTY_NAME_MSG = ".form-feedback.has-animation";
	private static final String INPUT_FOLDER_NAME = "input.text-editable__input.focus-visible";
	private static final String DELETE_FOLDER_POPUP = ".modal.is-open";
	private static final String DELETE_FOLDER_SUBTITLE = ".section__subtitle div";
	private static final String DELETE_FODLER_BTN = "button[data-testid='confirm-box__confirm']";
	private static final String DELETE_FOLDER_SUCCESS_MESSAGE = ".notification.is-type-success";

	// Folder actions
	private static final String FOLDER_ACTIONS = ".folder-item__actions button";
	private static final String RENAME_OPTION = "//div[@class='dropdown__menu is-open']//li/a[@href='/']";
	private static final String DELETE_OPTION = "//div[@class='dropdown__menu is-open']//li/a[contains(@href,'action=delete')]";

	public boolean isFolderPageLoaded() {
		return elementIsPresent(By.cssSelector(FOLDER_PAGE));
	}

	@Step("Get message after delete folder successfully")
	public String getDeleteFolderSuccessMsg() {
		logger.info("Get message after delete folder successfully");
		waitUntilElementVisibility(By.cssSelector(DELETE_FOLDER_SUCCESS_MESSAGE));
		WebElement ele = findElementByCss(DELETE_FOLDER_SUCCESS_MESSAGE);
		String msg = ele.getText();
		System.out.println(msg);
		return msg;
	}

	@Step("Click new folder button")
	public void clickNewFolder() {
		logger.info("Click new folder button");
		WebElement ele = findElementByXpath(NEW_FOLDER_BTN);
		ele.click();
	}

	@Step("Click on ... of folder name: {0}")
	public void clickOnFolderActions(String folderName) {
		logger.info("Click on ... of folder name: " + folderName);
		List<WebElement> elements = findElementsByXpath(FOLDER_LIST);
		for (WebElement ele : elements) {
			waitForAttributeOf(ele, "style", OPACITY_VALUE);
			WebElement fName = ele.findElement(By.xpath(FOLDER_NAME_BTN));
			String name = fName.getText();
			if (name.equals(folderName)) {
				hoverOnElement(ele);
				ele.findElement(By.cssSelector(FOLDER_ACTIONS)).click();
				break;
			}
		}
	}

	@Step("Click rename option")
	public void clickRenameFolderOption() {
		logger.info("Click rename option");
		findElementByXpath(RENAME_OPTION).click();
	}

	@Step("Click delete option")
	public void clickDeleteFolderOption() {
		logger.info("Click delete option");
		findElementByXpath(DELETE_OPTION).click();
	}

	@Step("Check folder: {0} is existed")
	public boolean isFolderExisted(String folderName) {
		logger.info("Check folder: " + folderName + " is existed");
		List<WebElement> elements = findElementsByXpath(FOLDER_LIST);
		for (WebElement ele : elements) {
			waitForAttributeOf(ele, "style", OPACITY_VALUE);
			WebElement fName = ele.findElement(By.xpath(FOLDER_NAME_BTN));
			String name = fName.getText();
			if (name.equals(folderName)) {
				System.out.println(name);
				return true;
			}
		}
		return false;
	}

	@Step("Click on folder name: {0}")
	public void clickViewFolderByName(String folderName) {
		logger.info("Click on folder name: " + folderName);
		List<WebElement> elements = findElementsByXpath(FOLDER_LIST);
		for (WebElement ele : elements) {
			waitForAttributeOf(ele, "style", OPACITY_VALUE);
			WebElement fName = ele.findElement(By.xpath(FOLDER_NAME_BTN));
			String name = fName.getText();
			if (name.equals(folderName)) {
				hoverOnElement(ele);
				ele.findElement(By.xpath(VIEW_FOLDER_BTN)).click();
				break;
			}
		}
	}

	@Step("Input folder name: {0}")
	public void inputFolderNameOnPopUp(String fName) {
		logger.info("Input folder name: " + fName);
		WebElement ele = findElementByCss(FOLDER_NAME_FIELD);
		ele.sendKeys(fName);
	}

	@Step("Click next button")
	public void clickNextButton() {
		logger.info("Click next button");
		WebElement ele = findElementByCss(NEXT_BUTTON);
		ele.click();
		waiForBtnLoaderNotExisted();
	}

	@Step("Click cancel button")
	public void clickCancelButton() {
		logger.info("Click cancel button");
		WebElement ele = findElementByCss(CANCEL_BUTTON);
		ele.click();
	}

	@Step("Click save button")
	public void clickSaveButton() {
		logger.info("Click save button");
		WebElement ele = findElementByCss(SAVE_BUTTON);
		ele.click();
		waiForBtnLoaderNotExisted();
	}

	@Step("Click skip button")
	public void clickSkipButton() {
		logger.info("Click skip button");
		WebElement ele = findElementByCss(SKIP_BUTTON);
		ele.click();
	}

	@Step("Get title of add email to folder popup")
	public String getTitleOfNewFolderForm() {
		logger.info("Get title of add email to folder popup");
		WebElement ele = findElementByCss(MODAL_TITLE);
		String text = ele.getText();
		return text;
	}

	@Step("Get title of Add email to folder popup")
	public String getTitleOfAddEmailToFolderForm() {
		logger.info("Get title of Add email to folder popup");
		WebElement ele = findElementByCss(ADD_EMAIL_TO_FOLDER_MODAL_TITLE);
		String text = ele.getText();
		return text;
	}

	@Step("Get error message when create folder with duplicated name")
	public String getDuplicatedFolderMessage() {
		logger.info("Get error message when create folder with duplicated name");
		waitUntilElementVisibility(By.cssSelector(DUPLICATED_ERR_MSG));
		WebElement ele = findElementByCss(DUPLICATED_ERR_MSG);
		String msg = ele.getText();
		return msg;
	}

	@Step("Get error message when create folder with empty name")
	public String getEmptyNameMessage() {
		logger.info("Get error message when create folder with empty name");
		waitUntilElementVisibility(By.cssSelector(EMPTY_NAME_MSG));
		WebElement ele = findElementByCss(EMPTY_NAME_MSG);
		String msg = ele.getText();
		return msg;
	}

	public void clickAnyWhereElse() {
		logger.info("Click out of folder name to save");
		findElementByCss(MODAL_TITLE).click();
	}

	@Step("Check next button has disable")
	public boolean isNextButtonDisabled() {
		logger.info("Check next button has disabled");
		WebElement nextBtn = findElementByCss(NEXT_BUTTON);
		String className = nextBtn.getAttribute("class");
		if (className.contains("is-disabled")) {
			return true;
		}
		return false;
	}

	@Step("Input folder name: {0}")
	public void inputFolderName(String fName) {
		logger.info("Input folder name: " + fName);
		WebElement ele = findElementByCss(INPUT_FOLDER_NAME);
		clearTextElement(ele);
		ele.sendKeys(fName);
		ele.sendKeys(Keys.ENTER);
	}

	@Step("Waiting until delete folder popup loaded")
	public void waitForDeleteFolderPopup() {
		logger.info("Waiting until delete folder popup loaded");
		waitForAttributeOf(findElementByCss(DELETE_FOLDER_POPUP), "class", "confirm-box");
	}

	@Step("Get title of delete folder popup")
	public String getDeleteFolderPopUpTitle() {
		logger.info("Get title of delete folder popup");
		waitForDeleteFolderPopup();
		WebElement ele = findElementByCss(MODAL_TITLE);
		String text = ele.getText();
		return text;
	}

	@Step("Get warning message in delete folder popup")
	public String getDeleteFolderMessage() {
		logger.info("Get warning message in delete folder popup");
		WebElement ele = findElementByCss(DELETE_FOLDER_SUBTITLE);
		String text = ele.getText();
		return text;
	}

	@Step("Click Yes, delete button")
	public void clickConfirmDeleteFolderButton() {
		logger.info("Click Yes, delete button");
		WebElement ele = findElementByCss(DELETE_FODLER_BTN);
		ele.click();
	}

}
