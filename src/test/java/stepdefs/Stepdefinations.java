package stepdefs;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import keywords.HeaderSectionKeywords;
import keywords.HomePageKeywords;
import keywords.MyAccountPageKeywords;
import keywords.SearchPageKeywords;
import keywords.ShoppingCartPageKeywords;
import keywords.UserLoginPageKeywords;
import logger.MainLogger;
import testresourcereader.DataSource;

public class Stepdefinations {

	public HomePageKeywords homePageKeywords;
	public UserLoginPageKeywords loginKeywords;
	public MyAccountPageKeywords myAccountKeywords;
	public HeaderSectionKeywords headerKeywords;
	public SearchPageKeywords searchKeywords;
	public ShoppingCartPageKeywords shoppingCartPage;
	public DataSource testdata;
	private String productTitle;

	public Stepdefinations() {
		homePageKeywords = new HomePageKeywords();
		loginKeywords = new UserLoginPageKeywords();
		myAccountKeywords = new MyAccountPageKeywords();
		headerKeywords = new HeaderSectionKeywords();
		searchKeywords = new SearchPageKeywords();
		shoppingCartPage = new ShoppingCartPageKeywords();
		testdata = new DataSource();
	}

	@Given("User launches the application url")
	public void user_launches_the_application_url() {
		homePageKeywords.launchApplication(testdata.getApplicationLandingPageUrl());
	}

	@When("user enters a valid product search term")
	public void user_enters_a_valid_product_search_term() {
		headerKeywords.enterProductName(testdata.getProductForSearch());
	}

	@When("clicks on search button")
	public void clicks_on_search_button() {
		headerKeywords.clickOnSearchButton();
	}

	@Then("more than one product is displayed")
	public void more_than_one_product_is_displayed() {
		Assert.assertTrue(searchKeywords.getCountOfProductsAfterSearch() > 0);
		MainLogger.logger().info("Assertion passed : more than one product dispaly after search");
	}

	@Then("each product name conatins the search term")
	public void each_product_name_conatins_the_search_term() {
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(searchKeywords.getTitleOfALLSearchProducts()).allSatisfy(title -> {
			softly.assertThat(title).containsIgnoringCase(testdata.getProductForSearch());
		});
		softly.assertAll();
	}

	@When("Clicks on myaccount option from header")
	public void clicks_on_myaccount_option_from_header() {
		headerKeywords.clickOnMyAccountIcon();
	}

	@When("Select the login page")
	public void select_the_login_page() {
		headerKeywords.clickOnLoginPageOptionfromAccountDropDown();
	}

	@Then("User should navigates to login page")
	public void user_should_navigates_to_login_page() {
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(loginKeywords.getListOfLoginMenuOptions()).containsSequence("Register")
				.withFailMessage("Assertion Failed :On Login page there is no option of Register");
		softly.assertThat(loginKeywords.getPageTitle()).isEqualTo("Account Login");
		softly.assertAll();
	}

	@Given("User launches the login page url")
	public void user_launches_the_login_page_url() {
		homePageKeywords.launchApplication(testdata.getLoginPagePath());
	}

	@When("User enters a valid username and password")
	public void user_enters_a_valid_username_and_password() {
		loginKeywords.enterUserName(testdata.getCustomerUserName());
		loginKeywords.enterPassword(testdata.getCustomerPassword());

	}

	@When("Clicks on the login button")
	public void clicks_on_the_login_button() {
		loginKeywords.hitLogin();
	}

	@Then("User should able to see my account option {string}")
	public void user_should_able_to_see_my_account_option(String option) {
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(myAccountKeywords.getListOfAllMyAccountOptions()).containsSequence(option)
				.withFailMessage("Assertion Failed : After login user can see the View your order history");
		softly.assertAll();
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String title) {
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(myAccountKeywords.getPageTitle()).isEqualTo(title);
		softly.assertAll();
	}

	@When("user clicks add to cart option")
	public void user_clicks_add_to_cart_option() {
		searchKeywords.clickOnAddToCartOptionOfProduct(0);
	}

	@Then("product should be added in cart")
	public void product_should_be_added_in_cart() {
		SoftAssertions softly = new SoftAssertions();
		String productPrice = searchKeywords.getPriceOfSearchedProduct(0);
		productTitle = searchKeywords.getTitleOfSearchedProduct(0);
		softly.assertThat(searchKeywords.getSuccessText()).contains(productTitle)
				.withFailMessage("product name is incorrect in success message");
		softly.assertThat(productPrice).isEqualTo(headerKeywords.getTotalPriceDisplayInCart())
				.withFailMessage("The product price in cart is incorrect");
		softly.assertThat(headerKeywords.getNumberOfItemsInCart()).isEqualTo("1")
				.withFailMessage("The product count in cart is incorrect");
		softly.assertAll();
	}

	@When("user clicks on view cart option")
	public void user_clicks_on_view_cart_option() {
		headerKeywords.clickOnCart().clickOnViewCartOption();
	}

	@Then("there should be only one product in shopping table")
	public void there_should_be_only_one_product_in_shopping_table() {
		SoftAssertions softly = new SoftAssertions();
		List<HashMap<String, String>> allProductsInCart = shoppingCartPage.getShoppingCartTableData();
		softly.assertThat(allProductsInCart.size()).isEqualTo(1)
				.withFailMessage("There are more than 1 entry of product in table");
		softly.assertAll();
	}

	@Then("product price and title should be displayed in the shopping cart table")
	public void product_price_and_title_should_be_displayed_in_the_shopping_cart_table() {
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(shoppingCartPage.getShoppingCartTableData().get(0).get("Product Name")).isEqualTo(productTitle)
				.withFailMessage("The product title in cart is incorrect");
		softly.assertAll();
	}

}
