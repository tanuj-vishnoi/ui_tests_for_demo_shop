package keywords;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import logger.MainLogger;
import pageobjects.SearchPageLocators;

public class SearchPageKeywords extends BasePage {

	/** The order page locators. */
	private SearchPageLocators locators;
	
	/**
	 * Instantiates a new order page keywords.
	 */
	public SearchPageKeywords() {
		locators = new SearchPageLocators();
	}

	private List<WebElement> getListOfALLSearchProducts(){
		return getElements(locators.listOfItems);
	}
	
	public int getCountOfProductsAfterSearch() {
		int totalCount =  getListOfALLSearchProducts().size();
		MainLogger.logger().info("Total count of product after search "+totalCount);
		return totalCount;
	}
	
	public WebElement getSearchedItem(int index) {
		return getListOfALLSearchProducts().get(index);
	}
	
	public String getPriceOfSearchedProduct(int indexOfProduct) {
		String priceLine =  getSearchedItem(indexOfProduct).findElement(locators.productPrice).getText().trim().replace("\n", " ");
		MainLogger.logger().info(priceLine);
		String price = priceLine.split(" ")[0].trim();
		String tax = priceLine.split("Tax:")[1].trim();
		MainLogger.logger().info("Product at index "+indexOfProduct+" having a price of "+price +" and tax "+tax);
		return price;
	}
	
	public String getTitleOfSearchedProduct(int indexOfProduct) {
		String productName =  getSearchedItem(indexOfProduct).findElement(locators.productTitle).getText();
		MainLogger.logger().info("Product at index "+indexOfProduct+" has name "+productName);
		return productName;
	}
	
	public List<String> getTitleOfALLSearchProducts() {
		List<String> allProductName = new ArrayList<String>();
		List<WebElement> allProducts = getListOfALLSearchProducts();
		for(WebElement product: allProducts) {
			allProductName.add(product.findElement(locators.productTitle).getText().trim());
		}
		return allProductName;
	}
	
	public void clickOnAddToCartOptionOfProduct(int indexOfProduct) {
		getSearchedItem(indexOfProduct).findElement(locators.addToCart).click();
		MainLogger.logger().info("User add product at index "+indexOfProduct+ " in the cart");
	}
	
	public String getSuccessText() {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locators.successLabelAddToCart, "Success"));
		String successMessage =  getElement(locators.successLabelAddToCart).getText().replace("\n", " ").trim();
		MainLogger.logger().info("Success message is "+successMessage);
		return successMessage;
	}
	
	
	
	
	
}
