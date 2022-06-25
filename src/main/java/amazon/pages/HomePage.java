package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import amazon.factories.GenericWrappers;

public class HomePage extends GenericWrappers {

	public HomePage(WebDriver driver) {
		super(driver);

	}

	String shopByDeptSection = "//div[@id='hmenu-content']//li//div[contains(text(),'shop by department')]";
	By allHambButton = By.id("nav-hamburger-menu");
	By shopByDept = By.xpath(shopByDeptSection);
	By shopTV = By.xpath(shopByDeptSection + "//ancestor::*//div[contains(text(),'TV, Appliances, Electronics')]");
	By tvSectionLink = By.xpath("//a[contains(text(),'Televisions')]");

	private static Logger log = LoggerFactory.getLogger(HomePage.class);

	public boolean verifyHomePage() {

		if (verifyTitle(
				"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in")) {

			log.info("This is Home page !!");
			return true;
		}
		return false;
	}

	public void enterHomePage() {
		clickById(allHambButton);
		scrollOverByXpath(shopByDept);
		clickByJavascriptExecutor(shopTV);
		mouseOverByXpath(tvSectionLink);
		clickByXpath(tvSectionLink);
		waitTillPageLoads();
	}

}
