package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import amazon.factories.GenericWrappers;

public class ResultPage extends GenericWrappers {

	public ResultPage(WebDriver driver) {
		super(driver);

	}

	String secondItemText = "//div[@data-index='2']//span[@class='rush-component']//a";
	By sortButton = By.xpath("//span[contains(@id,'a-autoid') and contains(@aria-label,'Sort by:')]");
	By samsungSelection = By.xpath("//li//a[contains(text(),'Price: High to Low')]");
	By verifyTelevisionSection = By.xpath("//select[@id='searchDropdownBox']//option[1]");
	By secondItemSection = By.xpath(secondItemText);
	By getSecondItem = By.xpath(secondItemText + "//ancestor::div[contains(@cel_widget_id,'2')]//h2//span");
	private String secondItemName = "";

	private static Logger log = LoggerFactory.getLogger(ResultPage.class);

	public boolean verifyResultPage() {

		if (verifyTextByXpath(verifyTelevisionSection, "Televisions"))

		{

			log.info("This is Television Result page !!");
			return true;
		}
		return false;
	}

	public void navigateSamsungResult() {

		clickByXpath(sortButton);
		clickByXpath(samsungSelection);
		waitTillPageLoads();
		clickByXpath(secondItemSection);
		setSecondItemName(getTextByXpath(getSecondItem));
		switchTabs();

	}

	public String getSecondItemName() {
		return secondItemName;
	}

	public void setSecondItemName(String secondItemName) {
		this.secondItemName = secondItemName;
	}

}
