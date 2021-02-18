package pageobjects;

import org.openqa.selenium.By;

public class SearchPageLocators {

	public By listOfItems = By.cssSelector("div[class*=\"product-layout product-grid\"]>div");

	public By productPrice = By.cssSelector("[class='price']");

	public By productTitle = By.cssSelector("[class=\"caption\"] h4 a");
	
	public By addToCart = By.cssSelector("[class=\"button-group\"] [class=\"fa fa-shopping-cart\"]");
	
	public By successLabelAddToCart = By.cssSelector("[class*='alert-success']");
}
