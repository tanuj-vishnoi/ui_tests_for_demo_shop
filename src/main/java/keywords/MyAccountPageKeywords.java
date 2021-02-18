package keywords;

import java.util.List;

import logger.MainLogger;
import pageobjects.MyAccountPageLocators;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePageKeywords.
 */
public class MyAccountPageKeywords extends BasePage {

	/** The home page locators. */
	private MyAccountPageLocators accountLocators;

	/**
	 * Instantiates a new home page keywords.
	 */
	public MyAccountPageKeywords() {
		accountLocators = new MyAccountPageLocators();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public List<String> getListOfAllMyAccountOptions() {
		List<String> allOptions = getALLTextFromElements(getElements(accountLocators.allMyAccountLinks));
		MainLogger.logger().info("All options are "+allOptions);
		return allOptions;
	}
	

}
