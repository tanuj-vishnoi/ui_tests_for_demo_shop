package keywords;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import logger.MainLogger;
import pageobjects.HomePageLocators;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePageKeywords.
 */
public class HomePageKeywords extends BasePage {

	/** The home page locators. */
	private HomePageLocators homePageLocators;

	/**
	 * Instantiates a new home page keywords.
	 */
	public HomePageKeywords() {
		homePageLocators = new HomePageLocators();
	}

	/**
	 * Launch application.
	 *
	 * @param url the url
	 */
	public void launchApplication(String url) {
		MainLogger.logger().info("Launching url "+url);
		driver.get(url);
	}

	/**
	 * Enter text in search box.
	 *
	 * @param text the text
	 */
	public void enterTextInSearchBox(String text) {
	//	enterText(getElement(homePageLocators.searchBox), text);
		//getElement(homePageLocators.searchButton).click();
	}

	/**
	 * Gets the search result header result.
	 *
	 * @return the search result header result
	 */
	public String getSearchResultHeaderResult() {
		return getElement(homePageLocators.searchResultHeader).getText();
	}

	/**
	 * Gets the number of search result.
	 *
	 * @return the number of search result
	 */
	public int getNumberOfSearchResult() {
		return getElements(homePageLocators.resultProductImages).size();
	}

	/**
	 * Click on first product image.
	 */
	public void clickOnFirstProductImage() {
		getElements(homePageLocators.resultProductImages).get(0).click();
	}

	/**
	 * Gets the face book contact link.
	 *
	 * @return the face book contact link
	 */
	public String getFaceBookContactLink() {
		return getElement(homePageLocators.faceBookFooterLink).getText();
	}

	/**
	 * Gets the twitter contact link.
	 *
	 * @return the twitter contact link
	 */
	public String getTwitterContactLink() {
		return getElement(homePageLocators.twitterLink).getText();
	}
	
	/**
	 * Gets the email contact text.
	 *
	 * @return the email contact text
	 */
	public String getEmailContactText() {
		return getElement(homePageLocators.mailLink).getText();
	}
	
	/**
	 * Click on cart icon.
	 */
	public void clickOnCartIcon() {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(getElement(homePageLocators.cartIcon)));
		getElement(homePageLocators.cartIcon).click();
	}

}
