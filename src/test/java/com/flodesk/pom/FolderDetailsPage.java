package com.flodesk.pom;

import org.openqa.selenium.WebElement;

public class FolderDetailsPage extends EmailActionsPage{
//	private static final String EMAIL_LIST = ".campaign-list__item";
	private static final String BACK_BUTTON = ".prefixbar__item button";
	
	public void clickBackButton() {
		WebElement ele = findElementByCss(BACK_BUTTON);
		ele.click();
	}
}
