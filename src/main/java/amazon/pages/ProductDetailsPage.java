package amazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import amazon.factories.GenericWrappers;

public class ProductDetailsPage extends GenericWrappers {

	public ProductDetailsPage(WebDriver driver) {
		super(driver);

	}

	By aboutSection = By.xpath("//h1[normalize-space(text())='About this item']");
	By showMoreLink = By.xpath("//span[contains(text(),'Show More')]");
	By productTitle = By.xpath("//span[@id='productTitle']");
	By displayItem = By.xpath("//ul[contains(@class,'spacing-mini')]//li");
	By moreItem = By.xpath("//ul[contains(@class,'spacing-none')]//li");
	private static Logger log = LoggerFactory.getLogger(ProductDetailsPage.class);

	public boolean verifyProdDetailsPage(String expectedString) {

		if (verifyTextByXpath(productTitle, expectedString)) {

			log.info("This is Television Details page !!");
			return true;
		}
		return false;
	}

	public boolean verifyAboutSection() {
		if (getTextByXpath(aboutSection).trim().equals("About this item"))
		{
			log.info("This is Samsung Details About Section !!");
			return true;
		}
		else
			return false;

	}

	public void navigateDetailResult() {

		scrollOverByXpath(aboutSection);
		if (verifyTextByXpath(showMoreLink, "Show More")) {
			clickByXpath(showMoreLink);
			getDetailsonItem();
		}

	}

	public void getDetailsonItem() {
		List<WebElement> showItem = driver.findElements(displayItem);
		List<WebElement> nondisplayItem = driver.findElements(moreItem);

		for (WebElement product : showItem) {

			log.info("Displayed List : -->" + product.getText());
		}
		System.out.println("<-----------> Showing Hidden items<----------------->");

		for (WebElement product1 : nondisplayItem) {

			log.info("NonDisplayed List : -->" + product1.getText());
		}
	}
}
