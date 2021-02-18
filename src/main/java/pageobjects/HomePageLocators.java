package pageobjects;

import org.openqa.selenium.By;

public class HomePageLocators {
	
	public By searchResultHeader =  By.cssSelector("div[id='title'] h1");
	
	public By resultProductImages = By.cssSelector("[class=\"view-product-by-category\"] img");
	
	public By faceBookFooterLink =  By.cssSelector("#footer a[href*='facebook']");
	
	public By twitterLink =  By.cssSelector("#footer a[href*='twitter']");
	
	public By mailLink =  By.cssSelector("#footer a[href*='mailto']");
	
	public By cartIcon = By.cssSelector("a[class='cart-button']");
}
