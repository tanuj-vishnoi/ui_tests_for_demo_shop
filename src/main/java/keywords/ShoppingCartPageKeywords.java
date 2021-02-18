package keywords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import logger.MainLogger;
import pageobjects.ShoppingCartLocators;

public class ShoppingCartPageKeywords extends BasePage {

	/** The order page locators. */
	private ShoppingCartLocators shoppingCartLocator;
	
	/**
	 * Instantiates a new order page keywords.
	 */
	public ShoppingCartPageKeywords() {
		shoppingCartLocator = new ShoppingCartLocators();
	}
	
	public void waitPageTitleIsShoppingCart() {
		wait.until(ExpectedConditions.titleContains("Shopping Cart"));
	}
	
	public List<HashMap<String, String>> getShoppingCartTableData() {
		List<HashMap<String, String>> listOfRecords = new ArrayList<HashMap<String, String>>();
		List<String> headers = new ArrayList<String>();
		for (WebElement e : getElements(shoppingCartLocator.shoppingCartTableHeading))
			headers.add(e.getText().trim());
		MainLogger.logger().debug(headers);
		List<WebElement> rows = getNumberOfRowsInShoppingcartTable();
		MainLogger.logger().debug("Numbers of rows are "+rows.size());
		for (int i = 0; i < rows.size(); i++) {
			HashMap<String, String> row = new HashMap<String, String>();
			List<WebElement> cells = rows.get(i).findElements(By.cssSelector("td"));
			MainLogger.logger().debug("Numbers of cells are "+rows.size());
			for (int j = 0; j < cells.size(); j++) {
				row.put(headers.get(j), cells.get(j).getText().trim());
			}
			listOfRecords.add(row);
		}
		MainLogger.logger().info("Table data is "+listOfRecords);
		return listOfRecords;
	}

	
	private List<WebElement> getNumberOfRowsInShoppingcartTable() {
		return getElement(shoppingCartLocator.shoppingCartTable).findElements(By.cssSelector("tbody tr"));
	}

}
