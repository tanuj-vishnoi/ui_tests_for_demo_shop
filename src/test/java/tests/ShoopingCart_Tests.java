package tests;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import logger.MainLogger;

public class ShoopingCart_Tests extends BaseTests {

	@BeforeClass
	public void setUP() {
		setUpSession();// this set driver session
		initKeywords();// this initialize required keywords
	}

	@BeforeMethod
	public void openApplicationLandingPage() {
		homePageKeywords.launchApplication(testdata.getApplicationLandingPageUrl());
	}

	@Test(description = "Test data and product details on shopping cart page")
	public void Test1_Verify_Profuct_Details_On_Shopping_Cart_Part() {
		String productToSearch = testdata.getProductForSearch();
		headerKeywords.searchProduct(productToSearch);
		softly.assertThat(searchKeywords.getCountOfProductsAfterSearch()).isGreaterThan(0);
		String productPrice = searchKeywords.getPriceOfSearchedProduct(0);
		String productTitle = searchKeywords.getTitleOfSearchedProduct(0);
		searchKeywords.clickOnAddToCartOptionOfProduct(0);

		softly.assertThat(searchKeywords.getSuccessText()).contains(productTitle)
				.withFailMessage("product name is incorrect in success message");
		MainLogger.logger().info(headerKeywords.getTotalPriceDisplayInCart());
		softly.assertThat(productPrice).isEqualTo(headerKeywords.getTotalPriceDisplayInCart())
				.withFailMessage("The product price in cart is incorrect");
		softly.assertThat(headerKeywords.getNumberOfItemsInCart()).isEqualTo("1")
				.withFailMessage("The product count in cart is incorrect");
		headerKeywords.clickOnCart().clickOnViewCartOption();
		shoppingCartPage.waitPageTitleIsShoppingCart();
		List<HashMap<String, String>> allProductsInCart = shoppingCartPage.getShoppingCartTableData();
		softly.assertThat(allProductsInCart.size()).isEqualTo(1)
				.withFailMessage("There are more than 1 entry of product in table");
		

		softly.assertThat(allProductsInCart.get(0).get("Product Name")).isEqualTo(productTitle)
				.withFailMessage("The product title in cart is incorrect");

		softly.assertAll();

	}

}
