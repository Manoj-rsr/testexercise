import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import amazon.pages.HomePage;
import amazon.pages.ProductDetailsPage;
import amazon.pages.ResultPage;
import amazon.pages.TelevisionPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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

import java.io.File;

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
	ExtentTest test;
	ExtentReports report;

	@BeforeAll

	void invokeApp() {
		String separator=File.separator;
		report = new ExtentReports("."+ separator+ "reports"+separator+"testresult.html",false);
		test = report.startTest("Assignment_WebAutomation");
		driver.get(HOME_PAGE_URL);
		driver.navigate().to(HOME_PAGE_URL.replace("com", "in"));
		test.log(LogStatus.PASS, "Navigated to " + HOME_PAGE_URL.replace("com", "in"));
		driver.manage().window().maximize();

	}

	@Tag("smokeTest")
	@DisplayName("Amazon-#Automation Test Exercise Code#")
	@Test
	@Order(1)
	void assertThatHomePageTitle() {

		ObjHomePage = new HomePage(driver);
		assertEquals(true, ObjHomePage.verifyHomePage());
		test.log(LogStatus.PASS, "Navigated to Amazon HOMEPAGE");
	}

	@Test
	@Order(2)
	void assertThatTVSectionPageTitle() {

		objTelvPage = new TelevisionPage(driver);
		ObjHomePage.enterHomePage();
		assertEquals(true, objTelvPage.verifyTelevisionPage());
		test.log(LogStatus.PASS, "Navigated to Televison Section");

	}

	@Test
	@Order(3)
	void assertThatSamsungResultPage() {

		objTelvPage = new TelevisionPage(driver);
		objResultPage = new ResultPage(driver);
		objTelvPage.navigateToSamsung();
		assertEquals(true, objResultPage.verifyResultPage());
		test.log(LogStatus.PASS, "Navigated to Samusung TV Result Section");

	}

	@Test
	@Order(4)
	void assertThatSamsungDetailPage() {

		objResultPage = new ResultPage(driver);
		objProdDetPage = new ProductDetailsPage(driver);
		objResultPage.navigateSamsungResult();
		assertEquals(true, objProdDetPage.verifyAboutSection());
		test.log(LogStatus.PASS, "Navigated to Samusung TV 2nd Item Details AboutSection");
		objProdDetPage.verifyProdDetailsPage(objResultPage.getSecondItemName());
		objProdDetPage.navigateDetailResult();
		test.log(LogStatus.INFO, objProdDetPage.getDetailsonItem().toString());
	}

	@AfterAll
	void closeBrowsers() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}

}
