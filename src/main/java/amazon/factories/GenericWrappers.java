package amazon.factories;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericWrappers {

	private static Logger log = LoggerFactory.getLogger(DriverFactory.class);
	protected WebDriver driver;
	WebDriverWait wait;

	public GenericWrappers(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method will verify the title of the browser
	 * 
	 * @param title - The expected title of the browser
	 * @author Manoj
	 */
	public boolean verifyTitle(String title) {
		boolean bReturn = false;
		try {
			if (driver.getTitle().equals(title)) {
				log.info("The title of the page matches with the value :" + title);
				bReturn = true;
			} else
				log.error("The title of the page:" + driver.getTitle() + " did not match with the value :" + title);

		} catch (Exception e) {
			log.error("The title did not match ,failed in Exception", e);
		}

		return bReturn;
	}

	/**
	 * This method will verify the given text
	 * 
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Manoj
	 */
	public boolean verifyTextByXpath(By xpath, String text) {
		boolean bReturn = false;
		String sText = "";
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement textVerify = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
			sText = textVerify.getText();
			if (sText.trim().equals(text)) {
				log.info("The text: " + sText + " matches with the value :" + text);
				bReturn = true;
			} else {
				log.error("The text: " + sText + " did not match with the value :" + text);
			}
		} catch (Exception e) {
			log.error("The text: " + sText + " did not match with the value :" + text, e);
		}
		return bReturn;
	}

	/**
	 * This method will move current window to next tab
	 * 
	 * @author Manoj
	 */

	public void switchTabs() {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			log.info("Window moved to next tab");
		} catch (Exception e) {
			log.error("Window not moved to next tab", e);
		}
	}

	/**
	 * This method will click element by id
	 * 
	 * @param xpath - The locator of the object in id
	 * @author Manoj
	 */
	public void clickById(By id) {

		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement clickID = wait.until(ExpectedConditions.visibilityOfElementLocated(id));
			clickID.click();
			log.info("The element with id: " + id + " is clicked.");

		} catch (Exception e) {
			log.error("The element with id: " + id + " could not be clicked.", e);
		}

	}

	/**
	 * This method will click element by xpath
	 * 
	 * @param xpath - The locator of the object in xpath
	 * @author Manoj
	 */
	public void clickByXpath(By xpathVal) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			WebElement clickByxpath = wait.until(ExpectedConditions.visibilityOfElementLocated(xpathVal));
			clickByxpath.click();
			log.info("The element : " + xpathVal + " is clicked.");

		} catch (Exception e) {
			log.error("The element with xpath: " + xpathVal + " could not be clicked.");
		}

	}

	/**
	 * This method will mouseover element by xpath
	 * 
	 * @param xpath - The locator of the object in xpath
	 * @author Manoj
	 */
	public void mouseOverByXpath(By xpathVal) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			WebElement mouseOverByxpath = wait.until(ExpectedConditions.visibilityOfElementLocated(xpathVal));
			new Actions(driver).moveToElement(mouseOverByxpath).build().perform();
			log.info("The mouse over by xpath : " + xpathVal + " is performed.");

		} catch (Exception e) {
			log.error("The mouse over by xpath : " + xpathVal + " could not be performed.", e);
		}

	}

	/**
	 * This method will click element with JavascriptExecutor by xpath
	 * 
	 * @param xpath - The locator of the object in xpath
	 * @author Manoj
	 */
	public void clickByJavascriptExecutor(By xPath) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			WebElement clickJavascript = wait.until(ExpectedConditions.visibilityOfElementLocated(xPath));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", clickJavascript);
			log.info("The mouse over by xpath : " + xPath + " is performed. : ");
		} catch (Exception e) {
			log.error("Click javascript by xpath : " + xPath + " could not be performed.");
		}

	}

	/**
	 * This method will scrollover element by xpath
	 * 
	 * @param xpath - The locator of the object in xpath
	 * @author Manoj
	 */
	public void scrollOverByXpath(By xPath) {

		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement scrollOver = wait.until(ExpectedConditions.visibilityOfElementLocated(xPath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollOver);
			log.info("scrollOverByXpath: " + xPath + " is performed.");

		} catch (Exception e) {
			log.error("scrollOverByXpath " + xPath + " could not be performed.");
		}

	}

	/**
	 * This method will wait till page loads
	 * 
	 * @author Manoj
	 */
	public void waitTillPageLoads() {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * This method will extract text by xpath
	 * 
	 * @param xpath - The locator of the object in xpath
	 * @author Manoj
	 */
	public String getTextByXpath(By xpathVal) {
		String bReturn = "";
		try {
			return driver.findElement((xpathVal)).getText();
		} catch (Exception e) {
			log.error("The element with xpath: " + xpathVal + " could not be found.");
		}
		return bReturn;
	}

}
