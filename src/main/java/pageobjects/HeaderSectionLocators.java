package pageobjects;

import org.openqa.selenium.By;

public class HeaderSectionLocators {

	public By searchBox = By.cssSelector("input[placeholder='Search']");

	public By searchButton = By.cssSelector("input[placeholder='Search']+span button i");
	
	public By myAccountOption = By.cssSelector("[title='My Account'] span[class='caret']");
	
	public By loginPageOption = By.cssSelector("[title='My Account']+ul a[href*='login']");
	
	public By cartButton = By.cssSelector("#cart-total");
	
	public By viewCartLink = By.cssSelector("[id=\"cart\"] a[href*='cart']");

}
