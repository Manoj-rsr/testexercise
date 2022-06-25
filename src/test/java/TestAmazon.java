import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import amazon.pages.HomePage;
import amazon.pages.ProductDetailsPage;
import amazon.pages.ResultPage;
import amazon.pages.TelevisionPage;
import com.typesafe.config.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAmazon {
	private static Config config = EnvFactory.getInstance().getConfig();
	private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
	private WebDriver driver = DriverFactory.getDriver();

	HomePage ObjHomePage;
	TelevisionPage objTelvPage;
	ResultPage objResultPage;
	ProductDetailsPage objProdDetPage;

	@BeforeAll

	void invokeApp() {
		driver.get(HOME_PAGE_URL);
		driver.navigate().to(HOME_PAGE_URL.replace("com", "in"));
		driver.manage().window().maximize();

	}

	@Tag("smokeTest")
	@DisplayName("Amazon-#Automation Test Exercise Code#")
	@Test
	@Order(1)
	void assertThatHomePageTitle() {

		ObjHomePage = new HomePage(driver);
		assertEquals(true, ObjHomePage.verifyHomePage());

	}

	@Test
	@Order(2)
	void assertThatTVSectionPageTitle() {

		objTelvPage = new TelevisionPage(driver);
		ObjHomePage.enterHomePage();
		assertEquals(true, objTelvPage.verifyTelevisionPage());

	}

	@Test
	@Order(3)
	void assertThatSamsungResultPage() {

		objTelvPage = new TelevisionPage(driver);
		objResultPage = new ResultPage(driver);
		objTelvPage.navigateToSamsung();
		assertEquals(true, objResultPage.verifyResultPage());

	}

	@Test
	@Order(4)
	void assertThatSamsungDetailPage() {

		objResultPage = new ResultPage(driver);
		objProdDetPage = new ProductDetailsPage(driver);
		objResultPage.navigateSamsungResult();
		assertEquals(true,objProdDetPage.verifyAboutSection());
		objProdDetPage.verifyProdDetailsPage(objResultPage.getSecondItemName());
		objProdDetPage.navigateDetailResult();
	}

	@AfterAll
	void closeBrowsers() {
		driver.quit();
	}

}
