package tests;

import java.lang.reflect.Method;

import org.assertj.core.api.SoftAssertions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import browserfactory.DriverManager;
import keywords.HeaderSectionKeywords;
import keywords.HomePageKeywords;
import keywords.MyAccountPageKeywords;
import keywords.SearchPageKeywords;
import keywords.ShoppingCartPageKeywords;
import keywords.UserLoginPageKeywords;
import logger.MainLogger;
import mobserverproxy.StartMobServer;
import testresourcereader.DataSource;

public class BaseTests {
	
	
	public StartMobServer proxyServer;

	public HomePageKeywords homePageKeywords;
	public UserLoginPageKeywords loginKeywords;
	public MyAccountPageKeywords myAccountKeywords;
	public HeaderSectionKeywords headerKeywords;
	public SearchPageKeywords searchKeywords;
	public ShoppingCartPageKeywords shoppingCartPage;
	public DataSource testdata;
	public SoftAssertions softly;
	
	public void setUpSession() {
		DriverManager.setDriver(this.getClass().getSimpleName());
		testdata = new DataSource();
	}
	
	public void initKeywords() {
		homePageKeywords = new HomePageKeywords();
		loginKeywords = new UserLoginPageKeywords();
		myAccountKeywords = new MyAccountPageKeywords();
		headerKeywords = new HeaderSectionKeywords();
		searchKeywords = new SearchPageKeywords();
		shoppingCartPage = new ShoppingCartPageKeywords();
	}
	
	@BeforeMethod
	public void methodName(Method method) {
		softly = new SoftAssertions();
		MainLogger.logger().info("");
		MainLogger.logger().info("****************** Starting method : "+method.getName() + "*************************");
		MainLogger.logger().info("");
	}
	
	@AfterClass
	public void setUpDown() {
		//DriverManager.closeDriver();
	}
	
	@AfterMethod
	public void captureScreenShot(ITestResult result) {
		if(result.getStatus()==2) {
			//final byte[] imgBytes = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		}
		MainLogger.logger().info("");
		MainLogger.logger().info("**************** Ending method : "+result.getName() +" ******************************8");
		MainLogger.logger().info("");
	}
}
