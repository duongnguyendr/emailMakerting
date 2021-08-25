package com.flodesk.services;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.flodesk.pom.EmailPage;
import com.flodesk.pom.FolderDetailsPage;
import com.flodesk.pom.FolderPage;

import io.qameta.allure.Step;

public class CreateFolderService {
	private Logger logger = Logger.getLogger(CreateFolderService.class.getSimpleName());

	private static final String NEW_FOLDER_TITLE = "Name your folder";
	private static final String ADD_EMAIL_TITLE = "Add some emails to your folder";
	private static final String DELETE_FOLDER_TITLE = "Delete folder";
	private static final String DELETE_FOLDER_BODY_MSG = "Are you sure you want to delete this folder? This action cannot be undone and will only delete the folder (emails will not be deleted).";
	private static final String DELETE_FOLDER_SUCCESS_MSG = "Your folder has been deleted.";
	private String duplicatedNameMsg = "Your %s folder already exists. Try updating it instead or enter a different name to create a new folder.";
	private String emptyNameMsg = "\"Name\" is not allowed to be empty";

	FolderPage folderPage = new FolderPage();
	EmailPage emailPage = new EmailPage();
	FolderDetailsPage folderDetailsPage = new FolderDetailsPage();

	@Step("Go to folder page")
	public void goToFolderPage() {
		logger.info("Go to folder page");
		emailPage.clickFolderTab();
		boolean isLoaded = folderPage.isFolderPageLoaded();
		Assert.assertTrue("Cannot load folder page", isLoaded);
	}

	@Step("Verify folder {0} existed.")
	public void verifyFolderHasExisted(String folderName) {
		logger.info(String.format("Verify folder: %s has existed.", folderName));
		boolean existed = folderPage.isFolderExisted(folderName);
		Assert.assertTrue(String.format("Folder: %s not existed.", folderName), existed);
	}

	@Step("Verify folder {0} not existed.")
	public void verifyFolderHasNotExisted(String folderName) {
		logger.info(String.format("Verify folder: %s has not existed.", folderName));
		boolean existed = folderPage.isFolderExisted(folderName);
		Assert.assertFalse(String.format("Folder: %s still existed.", folderName), existed);
	}

	@Step("Verify title of create new folder popup")
	public void verifyCreateNewFolderTitle() {
		logger.info("Verify title of create new folder popup");
		String currentTitle = folderPage.getTitleOfNewFolderForm();
		String message = String.format("Title not match, expected: %s, but actual: %s.", NEW_FOLDER_TITLE,
				currentTitle);
		Assert.assertEquals(message, NEW_FOLDER_TITLE, currentTitle);
	}

	@Step("Verify title of Add email to folder popup")
	public void verifyAddEmailToFolderTitle() {
		logger.info("Verify title of Add email to folder popup");
		String currentTitle = folderPage.getTitleOfAddEmailToFolderForm();
		String message = String.format("Title not match, expected: %s, but actual: %s.", ADD_EMAIL_TITLE, currentTitle);
		Assert.assertEquals(message, ADD_EMAIL_TITLE, currentTitle);
	}

	@Step("Create folder with name: {0}")
	public void createNewFolder(String folderName) {
		logger.info("Create new foker with name: " + folderName);
		folderPage.clickNewFolder();
		verifyCreateNewFolderTitle();
		folderPage.inputFolderNameOnPopUp(folderName);
		folderPage.clickNextButton();
		verifyAddEmailToFolderTitle();
		folderPage.clickSkipButton();
	}

	@Step("Create new folder with existed name: {0}")
	public void createDuplicatedFolder(String folderName) {
		logger.info("Create new folder with existed name: " + folderName);
		folderPage.clickNewFolder();
		verifyCreateNewFolderTitle();
		folderPage.inputFolderNameOnPopUp(folderName);
		folderPage.clickNextButton();
		verifyDuplicatedFolderNameMessage(folderName);
	}

	@Step("Create new folder with empty name")
	public void createEmptyNameFolder() {
		logger.info("Create new folder with empty name");
		folderPage.clickNewFolder();
		verifyCreateNewFolderTitle();
		folderPage.inputFolderNameOnPopUp("");
		folderPage.clickAnyWhereElse();
		verifyEmptyNameMessage();
		verifyNextButtonHasDisabled();
	}

	@Step("Verify error message when create folder with duplicated name")
	public void verifyDuplicatedFolderNameMessage(String folderName) {
		logger.info("Verify error message when create folder with duplicated name");
		String msg = folderPage.getDuplicatedFolderMessage();
		Assert.assertEquals("Message not displayed", String.format(duplicatedNameMsg, folderName), msg);
	}

	@Step("Verify error message when create folder with empty name")
	public void verifyEmptyNameMessage() {
		logger.info("Verify error message when create folder with empty name");
		String msg = folderPage.getEmptyNameMessage();
		Assert.assertEquals("Message not displayed", emptyNameMsg, msg);
	}

	@Step("Verify next button has disabled")
	public void verifyNextButtonHasDisabled() {
		logger.info("Verify next button has disabled");
		boolean rs = folderPage.isNextButtonDisabled();
		Assert.assertTrue("Next button should be disabled.", rs);
	}

	@Step("Verify folder: {0} contains email: {1}")
	public void verifyFolderContainsEmail(String folder, String email) {
		logger.info("Verify folder: + " + folder + " contains email: " + email);
		folderPage.clickViewFolderByName(folder);
		boolean isExisted = folderDetailsPage.isEmailExisted(email);
		System.out.println("----- isExisted: " + isExisted);
		Assert.assertTrue("Folder not contains email: " + email, isExisted);

	}

	@Step("Verify folder: {0} not contains email: {1}")
	public void verifyFolderNotContainsEmail(String folder, String email) {
		logger.info("Verify folder: + " + folder + " not contains email: " + email);
		folderPage.clickViewFolderByName(folder);
		boolean isNotExisted = folderDetailsPage.isEmailExisted(email);
		Assert.assertFalse("Folder should not contains email: " + email, isNotExisted);
	}

	@Step("Click back button in folder page")
	public void clickBackToFolderPage() {
		logger.info("Click back button");
		folderDetailsPage.clickBackButton();
	}

	@Step("Rename folder {0} to {1}")
	public void renameFolder(String currentName, String newName) {
		logger.info(String.format("Rename folder %s to %s", currentName, newName));
		folderPage.clickOnFolderActions(currentName);
		folderPage.clickRenameFolderOption();
		folderPage.inputFolderName(newName);
	}

	@Step("Delete folder name: {0}")
	public void deleteFolder(String folderName) {
		logger.info("Delete folder name: " + folderName);
		folderPage.clickOnFolderActions(folderName);
		folderPage.clickDeleteFolderOption();
		verifyDeleteFolderPopup();
		folderPage.clickConfirmDeleteFolderButton();
		verifyDeleteFolderSuccessMessage();
	}

	@Step("Verify message when delete folder successfully")
	public void verifyDeleteFolderSuccessMessage() {
		logger.info("Verify message when delete folder successfully");
		String msg = folderPage.getDeleteFolderSuccessMsg();
		Assert.assertEquals("Message not display", DELETE_FOLDER_SUCCESS_MSG, msg);
	}

	@Step("Verify delete folder popup display with correct title and message")
	public void verifyDeleteFolderPopup() {
		logger.info("Verify delete folder popup");
		String title = folderPage.getDeleteFolderPopUpTitle();
		Assert.assertEquals(DELETE_FOLDER_TITLE, title);

		String msg = folderPage.getDeleteFolderMessage();
		Assert.assertEquals(DELETE_FOLDER_BODY_MSG, msg);
	}
}
