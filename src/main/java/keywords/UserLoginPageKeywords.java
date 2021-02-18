package keywords;

import java.util.List;

import logger.MainLogger;
import pageobjects.UserLoginPageLocators;

/**
 * The Class UserLoginPageKeywords.
 */
public class UserLoginPageKeywords extends BasePage {

	/** The login page locators. */
	private UserLoginPageLocators loginLocators;

	/**
	 * Instantiates a new login page keywords.
	 */
	public UserLoginPageKeywords() {
		loginLocators = new UserLoginPageLocators();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public UserLoginPageKeywords enterUserName(String userName) {
		enterText(getElement(loginLocators.userName), userName);
		MainLogger.logger().info("Entered the username : "+ userName);
		return this;
	}

	public UserLoginPageKeywords enterPassword(String password) {
		enterText(getElement(loginLocators.password), password);
		MainLogger.logger().info("Entered the password : "+ password);
		return this;
	}

	public void hitLogin() {
		getElement(loginLocators.loginButton).click();
		MainLogger.logger().info("Clicked on the login button");
	}

	public void login(String userName, String password) {
		enterUserName(userName).enterPassword(password).hitLogin();
	}
	
	public List<String> getListOfLoginMenuOptions() {
		List<String> allOptions = getALLTextFromElements(getElements(loginLocators.loginMenuOptions));
		MainLogger.logger().info("All options are "+allOptions);
		return allOptions;
	}

}
