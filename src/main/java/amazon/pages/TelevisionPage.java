package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import amazon.factories.GenericWrappers;

public class TelevisionPage extends GenericWrappers {

	public TelevisionPage(WebDriver driver) {
		super(driver);

	}

	By brandSection = By.xpath("//span[text()='Brands']");
    By samsungSelection = By.xpath("//span[text()='Samsung']//parent::*//input");

	private static Logger log = LoggerFactory.getLogger(TelevisionPage.class);

	public boolean verifyTelevisionPage() {

		if (verifyTitle(
				"Buy the latest LED TVs, 4K TVs and Android TVs online at Best Prices in India-Amazon.in | Shop by size, price, features and more")) {

			log.info("This is Television page !!");
			return true;
		}
		return false;
	}

	public void navigateToSamsung() {

		scrollOverByXpath(brandSection);
		clickByJavascriptExecutor(samsungSelection);

	}

}
