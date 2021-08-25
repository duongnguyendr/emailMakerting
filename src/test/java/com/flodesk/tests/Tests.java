package com.flodesk.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flodesk.Hooks;
import com.flodesk.SimpleTest.listeners.AllureReportListener;
import com.flodesk.SimpleTest.model.User;
import com.flodesk.services.CreateFolderService;
import com.flodesk.services.EmailService;
import com.flodesk.services.LoginService;

import io.qameta.allure.Description;

@Listeners(AllureReportListener.class)
public class Tests extends Hooks {
	LoginService loginService;
	CreateFolderService folderService;
	EmailService emailService;

	@BeforeMethod
	public void loginSystem() {
		loginService = new LoginService();
		User user = new User("huy+qa@flodesk.com", "Huy@Flodesk*");
		loginService.login(user);
	}

	@Test(priority = 1)
	@Description("Create new folder")
	public void _1createNewFolder() {
		folderService = new CreateFolderService();

		folderService.goToFolderPage();
		folderService.createNewFolder("Folder Test911");
		folderService.verifyFolderHasExisted("Folder Test911");
	}

	@Test(priority = 2)
	@Description("Create new folder with existed name")
	public void _2createNewFolderWithExistedName() {
		folderService = new CreateFolderService();

		folderService.goToFolderPage();
		folderService.createDuplicatedFolder("Folder Test1");
	}

//	@Test(priority = 3)
//	@Description("Create new folder with empty name")
//	public void _3createNewFolderWithEmptyName() {
//		folderService = new CreateFolderService();
//
//		folderService.goToFolderPage();
//		folderService.createEmptyNameFolder();
//	}
//
//	@Test(priority = 4)
//	@Description("Add email to folder")
//	public void _4addEmailToFolder() {
//		emailService = new EmailService();
//		folderService = new CreateFolderService();
//
//		emailService.addEmailToFolder("Email Demo", "Folder Test1");
//		folderService.goToFolderPage();
////		folderService.verifyFolderHasExisted("Folder Test1");
//		folderService.verifyFolderContainsEmail("Folder Test1", "Email Demo");
//	}
//
//	@Test(priority = 5)
//	@Description("Add email to new folder by create new one")
//	public void _5addEmailToFolderByCreateNewFolder() {
//		emailService = new EmailService();
//		folderService = new CreateFolderService();
//
//		emailService.addEmailToFolderByCreateNewFolder("Email Demo1", "Folder Test2");
//		folderService.goToFolderPage();
//		folderService.verifyFolderHasExisted("Folder Test2");
//		folderService.verifyFolderContainsEmail("Folder Test2", "Email Demo1");
//	}
//
//	@Test(priority = 6)
//	@Description("Move email from folder to another folder")
//	public void _6moveEmailToAnotherFolder() {
//		emailService = new EmailService();
//		folderService = new CreateFolderService();
//
//		emailService.moveEmailToFolder("Email Demo", "Folder Test1", "Folder Test2");
//		folderService.goToFolderPage();
//		folderService.verifyFolderNotContainsEmail("Folder Test1", "Email Demo");
//		folderService.clickBackToFolderPage();
//		folderService.verifyFolderContainsEmail("Folder Test2", "Email Demo");
//	}
//
//	@Test(priority = 7)
//	@Description("Move email from folder to new folder by create new one")
//	public void _7moveEmailToNewFolderByCreateOne() {
//		emailService = new EmailService();
//		folderService = new CreateFolderService();
//
//		emailService.moveEmailToNewFolder("Email Demo1", "Folder Test2", "Folder Test3");
//		folderService.goToFolderPage();
//		folderService.verifyFolderHasExisted("Folder Test3");
//		folderService.verifyFolderContainsEmail("Folder Test3", "Email Demo1");
//		folderService.clickBackToFolderPage();
//		folderService.verifyFolderNotContainsEmail("Folder Test2", "Email Demo1");
//	}
//
//	@Test(priority = 8)
//	@Description("Remove email from folder")
//	public void _8removeEmailFromFolder() {
//		emailService = new EmailService();
//		folderService = new CreateFolderService();
//
//		emailService.removeEmailFromFolder("Email Demo1", "Folder Test3");
//		folderService.goToFolderPage();
//		folderService.verifyFolderNotContainsEmail("Folder Test3", "Email Demo1");
//	}
//
//	@Test(priority = 9)
//	@Description("Rename folder")
//	public void _9renameFolder() {
//		folderService = new CreateFolderService();
//		emailService = new EmailService();
//
//		folderService.goToFolderPage();
//		folderService.renameFolder("Folder Test3", "Folder Test4");
//		emailService.goToMyEmailPage();
//		folderService.goToFolderPage();
//		folderService.verifyFolderHasNotExisted("Folder Test3");
//		folderService.verifyFolderHasExisted("Folder Test4");
//	}
//
//	@Test(priority = 10)
//	@Description("Delete folder")
//	public void _9zdeleteFolder() {
//		folderService = new CreateFolderService();
//
//		folderService.goToFolderPage();
//		folderService.verifyFolderHasExisted("Folder Test4");
//		folderService.deleteFolder("Folder Test4");
//		folderService.verifyFolderHasNotExisted("Folder Test4");
//	}

}
