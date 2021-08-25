package com.flodesk.services;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.flodesk.pom.AddEmailToFolderModalPage;
import com.flodesk.pom.EmailPage;

import io.qameta.allure.Step;

public class EmailService {
	private Logger logger = Logger.getLogger(EmailService.class.getSimpleName());
	private static final String ADD_TO_FOLDER_TITLE = "Add to folder";
	private static final String MOVE_TO_FOLDER_TITLE = "Move to folder";
	private static final String REMOVE_FROM_FOLDER_TITLE = "Remove from folder";
	private String addEmailSuccessMsg = "Your email has been added to the folder %s.";
	private String moveEmailSuccessMsg = "Your email has been moved to the folder %s.";
	private String removeEmailSuccessMsg = "Your email has been removed from the folder %s.";
	private String removeEmailMsg = "This email will be removed from the %s folder. You will be able to access it only in My Emails. Are you sure you want to remove it?";

	EmailPage emailPage = new EmailPage();
	AddEmailToFolderModalPage addModal = new AddEmailToFolderModalPage();

	@Step("Go to My Email page")
	public void goToMyEmailPage() {
		logger.info("Go to my email page");
		emailPage.clickMyEmailTab();
	}

	@Step("Add email: {0} to folder: {1}")
	public void addEmailToFolder(String email, String folder) {
		logger.info(String.format("Add email: %s to folder: %s", email, folder));
		emailPage.clickEmailActions(email);
		emailPage.clickAddToFolderOption();
		verifyEmailActionsPopUpDisplay(ADD_TO_FOLDER_TITLE);
		addModal.inputSearchField(folder);
		addModal.clickOnFolderNameInList(folder);
		verifyAddEmailToFolderSuccessMessage(folder);
	}

	@Step("Verify message after add email to folder successfully")
	public void verifyAddEmailToFolderSuccessMessage(String fName) {
		logger.info("Verify message after add email to folder successfully");
		String msg = addModal.getAddEmailToFolderSuccessMsg();
		Assert.assertEquals("Message not display", String.format(addEmailSuccessMsg, fName), msg);
	}

	@Step("Verify message after move email to another folder successfully")
	public void verifyMoveEmailToFolderSuccessMessage(String fName) {
		logger.info("Verify message after move email to another folder successfully");
		String msg = addModal.getAddEmailToFolderSuccessMsg();
		Assert.assertEquals("Message not display", String.format(moveEmailSuccessMsg, fName), msg);
	}

	@Step("Verify message after remove email from folder successfully")
	public void verifyRemoveEmailFromFolderSuccessMessage(String fName) {
		logger.info("Verify message after remove email from folder successfully");
		String msg = addModal.getAddEmailToFolderSuccessMsg();
		Assert.assertEquals("Message not display", String.format(removeEmailSuccessMsg, fName), msg);
	}

	@Step("Verify popup display")
	public void verifyEmailActionsPopUpDisplay(String title) {
		logger.info("Verify popup display");
		boolean isDisplay = addModal.isModalDisplay();
		Assert.assertTrue("Popup Not Appear.", isDisplay);
		String popupTitle = addModal.getTitleOfPopUp();
		Assert.assertEquals("Title not match.", title, popupTitle);
	}

	@Step("Add email: {0} to folder: {1} by created new one")
	public void addEmailToFolderByCreateNewFolder(String email, String folder) {
		logger.info("Add email to folder");
		emailPage.clickEmailActions(email);
		emailPage.clickAddToFolderOption();
		verifyEmailActionsPopUpDisplay(ADD_TO_FOLDER_TITLE);
		addModal.inputSearchField(folder);
		addModal.clickCreateNewFolderButton();
		verifyAddEmailToFolderSuccessMessage(folder);
	}

	@Step("Move email: {0} to from folder: {1} to folder: {2}")
	public void moveEmailToFolder(String email, String currentFolder, String newFolder) {
		logger.info("Move email to folder");
		emailPage.clickEmailActions(email);
		emailPage.clickMoveToFolderOption();
		verifyEmailActionsPopUpDisplay(MOVE_TO_FOLDER_TITLE);
		verifyCurrentFolder(currentFolder);
		addModal.inputSearchField(newFolder);
		addModal.clickOnFolderNameInList(newFolder);
		verifyMoveEmailToFolderSuccessMessage(newFolder);
	}

	@Step("Move email: {0} from folder: {1} to created new folder: {2}")
	public void moveEmailToNewFolder(String email, String currentFolder, String newFolder) {
		logger.info("Move email to folder");
		emailPage.clickEmailActions(email);
		emailPage.clickMoveToFolderOption();
		verifyEmailActionsPopUpDisplay(MOVE_TO_FOLDER_TITLE);
		verifyCurrentFolder(currentFolder);
		addModal.inputSearchField(newFolder);
		addModal.clickCreateNewFolderButton();
		verifyMoveEmailToFolderSuccessMessage(newFolder);
	}

	@Step("Remove email: {0} from folder: {1}")
	public void removeEmailFromFolder(String email, String folder) {
		logger.info("Remove email from folder");
		emailPage.clickEmailActions(email);
		emailPage.clickRemoveFromFolderOption();
		addModal.waitForRemoveEmailPopup();
		verifyEmailActionsPopUpDisplay(REMOVE_FROM_FOLDER_TITLE);
		verifyRemoveEmailMessage(folder);
		addModal.clickRemoveButton();
		verifyRemoveEmailFromFolderSuccessMessage(folder);
	}

	@Step("Verify current folder is: {0}")
	public void verifyCurrentFolder(String folder) {
		logger.info("Verify current folder is: " + folder);
		String currentFolder = addModal.getCurrentFolder();
		Assert.assertEquals("Current folder not match.", folder, currentFolder);
	}

	@Step("Verify warning message in remove email from folder popup")
	public void verifyRemoveEmailMessage(String folder) {
		logger.info("Verify warning message in remove email from folder popup");
		String msg = addModal.removeEmailFromFolderMessage();
		Assert.assertEquals("Message incorrect", String.format(removeEmailMsg, folder), msg);
	}
}
