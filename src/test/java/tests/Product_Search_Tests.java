package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Product_Search_Tests extends BaseTests {

	@BeforeClass
	public void setUP() {
		setUpSession();// this set driver session
		initKeywords();// this initialize required keywords
	}

	@BeforeMethod
	public void openApplicationLandingPage() {
		homePageKeywords.launchApplication(testdata.getApplicationLandingPageUrl());
	}

	@Test(description = "Test Verify in case of valid search system display the product or list of product")
	public void Test1_User_Can_Search_A_Valid_Product() {
		String productToSearch = testdata.getProductForSearch();
		headerKeywords.searchProduct(productToSearch);
		softly.assertThat(searchKeywords.getCountOfProductsAfterSearch()).isGreaterThan(0);
		softly.assertAll();
	}
	
	@Test(description = "Test Verify searched product has the search term in their product title")
	public void Test2_Verify_Search_Results_Has_The_Searched_Keyword_In_Their_Name() {
		String productToSearch = testdata.getProductForSearch();
		headerKeywords.searchProduct(productToSearch);
		softly.assertThat(searchKeywords.getTitleOfALLSearchProducts()).allSatisfy(title-> {
			softly.assertThat(title).containsIgnoringCase(productToSearch);
		});
		softly.assertAll();
	}
}
