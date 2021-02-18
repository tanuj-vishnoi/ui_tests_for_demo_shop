/*
 * 
 */
package pageobjects;

import org.openqa.selenium.By;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLoginPageLocators.
 */
public class UserLoginPageLocators {

	/** The user name. */
	public By userName = By.id("input-email");

	/** The password. */
	public By password = By.cssSelector("#input-password");

	/** The login button. */
	public By loginButton = By.cssSelector("input[value='Login']");
	
	public By loginMenuOptions = By.cssSelector("[class='list-group'] a");
}
