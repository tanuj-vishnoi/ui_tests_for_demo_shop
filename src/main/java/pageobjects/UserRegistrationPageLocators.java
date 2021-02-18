package pageobjects;

import org.openqa.selenium.By;

public class UserRegistrationPageLocators {

	public By userName = By.id("input-email");

	public By password = By.cssSelector("#input-password");

	public By loginButton = By.cssSelector("input[value='Login']");
}
