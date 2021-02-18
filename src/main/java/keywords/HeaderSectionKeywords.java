package keywords;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import logger.MainLogger;
import pageobjects.HeaderSectionLocators;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePageKeywords.
 */
public class HeaderSectionKeywords extends BasePage {

	/** The home page locators. */
	private HeaderSectionLocators headerLocators;

	/**
	 * Instantiates a new home page keywords.
	 */
	public HeaderSectionKeywords() {
		headerLocators = new HeaderSectionLocators();
	}

	/**
	 * Launch application.
	 *
	 * @param url the url
	 */
	public void launchApplication(String url) {
		driver.get(url);
	}

	/**
	 * Enter text in search box.
	 *
	 * @param text the text
	 */
	public void searchProduct(String text) {
		enterProductName(text);
		clickOnSearchButton();
	}
	
	public void enterProductName(String text) {
		enterText(getElement(headerLocators.searchBox), text);
		MainLogger.logger().info("User enters search term "+text);
	}
	
	public void clickOnSearchButton() {
		getElement(headerLocators.searchButton).click();
		MainLogger.logger().info("User clicks on search button");
	}
	
	public HeaderSectionKeywords clickOnMyAccountIcon() {
		getElement(headerLocators.myAccountOption).click();
		MainLogger.logger().info("User clicks on the myaccount option present in header section");
		return this;
	}
	
	public void clickOnLoginPageOptionfromAccountDropDown() {
		getElement(headerLocators.loginPageOption).click();
		MainLogger.logger().info("User clicks on the login option present in header myaccount dropdown");
	}
	
	public HeaderSectionKeywords clickOnCart() {
		clickUsingJS(getElement(headerLocators.cartButton));
		MainLogger.logger().info("User clicks on the cart icon");
		return this;
	}
	
	public String getNumberOfItemsInCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocators.cartButton));
		String numberOfItems = getElement(headerLocators.cartButton).getText().trim().split(" ")[0];
		MainLogger.logger().info("Number of Items in Cart "+numberOfItems);
		return numberOfItems;
	}
	
	public String getTotalPriceDisplayInCart() {
		String[] tokens = getElement(headerLocators.cartButton).getText().trim().split(" ");
		String totalPrice = tokens[tokens.length-1];
		MainLogger.logger().info("Price in Cart "+totalPrice);
		return totalPrice;
	}
	
	public void clickOnViewCartOption() {
		clickUsingJS(getElement(headerLocators.viewCartLink));
	}
	


}
