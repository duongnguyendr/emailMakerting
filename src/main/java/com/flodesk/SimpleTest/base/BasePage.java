package com.flodesk.SimpleTest.base;

import static com.flodesk.SimpleTest.helpers.DriverHelper.getDriver;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private Logger logger = Logger.getLogger(BasePage.class.getSimpleName());
	private static Integer timeOut = 15;
	private static Integer pollingTime = 1;
	
	private static final String BTN_LOADER = ".btn__loader";
	private static final String CLASS_LOADER = "//div[@class='loader is-hidden is-grow']";
	
	
	public void hoverOnElement(WebElement element) {
		Actions action = new Actions(getDriver());
		action.moveToElement(element).perform();
	}
	
	public void clearTextElement(WebElement element) {
		String os = System.getProperty("os.name");
		System.out.println(os);
		if (os.contains("Mac")) {
			Actions action = new Actions(getDriver());
			action.click(element)
			.keyDown(Keys.COMMAND).sendKeys("a")
			.keyUp(Keys.COMMAND).sendKeys(Keys.BACK_SPACE)
			.build().perform();
		}else {
			Actions action = new Actions(getDriver());
			action.click(element)
			.keyDown(Keys.CONTROL).sendKeys("a")
			.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE)
			.build().perform();
		}
	}
	
	public void getPage(String url) {
		System.out.println("Navigate to URL: " + url);
		getDriver().get(url);
	}
	
	public void waitForClassLoader() {
		logger.info("Waiting for loader");
		waitForElementVisible(By.xpath(CLASS_LOADER), timeOut);
	}
	
	public void waiForBtnLoaderNotExisted() {
		logger.info("Waiting for button loader");
		waitForElementInvisibled(By.cssSelector(BTN_LOADER));
	}
	
	public void waitUntilElementVisibility(By element) {
		waitElement(ExpectedConditions.visibilityOf(getDriver().findElement(element)), pollingTime, timeOut);
	}
	
	public WebElement findElementByCss(String element) {
		return waitForElementVisible(By.cssSelector(element), timeOut);
	}
	
	public List<WebElement> findElementsByCss(String elements){
		return getDriver().findElements(By.cssSelector(elements));
	}
	
	public WebElement findElementByXpath(String element) {
		return waitForElementVisible(By.xpath(element), timeOut);
	}
	
	public List<WebElement> findElementsByXpath(String elements){
		return getDriver().findElements(By.xpath(elements));
	}
	
	public boolean elementIsPresent(By element) {
		WebElement ele = waitForVisibilityOf(element, timeOut);
		return ele == null ? false : true;
	}
	
	protected WebElement waitForVisibilityOf(By element, Integer... timeOutInSeconds) {
		int attempts = 0;
		WebElement ele = null;
		while (attempts < 2) {
			try {
				ele = waitFor(ExpectedConditions.visibilityOf(getDriver().findElement(element)), (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			attempts ++;
		}
		return ele;
	}
	
	public void waitForAttributeOf(WebElement element, String attr, String value) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.attributeContains(element, attr, value));
			} catch (Exception e) {
				System.out.println(e);
			}
			attempts ++;
		}
	}
	
	protected void waitForClickableElement( By element, Integer...timeOutInSeconds) {
		int atempts = 0;
		while(atempts > 2) {
			try {
				waitFor(ExpectedConditions.elementToBeClickable(getDriver().findElement(element)), (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void waitForElementInvisibled(By element) {
		waitFor(ExpectedConditions.invisibilityOf(getDriver().findElement(element)));
	}
	
	private void waitFor(ExpectedCondition<Boolean> expectedCondition) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
		wait.until(expectedCondition);
	}
	
	private WebElement waitFor(ExpectedCondition<WebElement> expectedCondition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : timeOut;
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		return wait.until(expectedCondition);
	}
	
	public WebElement waitForElementVisible(By element, Integer timeOut) {
		return waitElement(ExpectedConditions.presenceOfElementLocated(element), pollingTime, timeOut);
	}
	
	private WebElement waitElement(ExpectedCondition<WebElement> condition, Integer polling, Integer timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(polling))
				.ignoring(Exception.class);
		return wait.until(condition);
	}
}
