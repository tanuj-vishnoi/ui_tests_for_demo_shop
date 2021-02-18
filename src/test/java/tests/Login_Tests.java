package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_Tests extends BaseTests {

	@BeforeClass
	public void setUP() {
		setUpSession();// this set driver session
		initKeywords();// this initialize required keywords
	}
	
	@Test(description="Verify User Can navigate to the Login Page from the Landing using myaccount login option")
	public void Test1_Verify_Login_Page_Accessible_From_Landing() {
		homePageKeywords.launchApplication(testdata.getApplicationLandingPageUrl());//// base condition
		headerKeywords.clickOnMyAccountIcon().clickOnLoginPageOptionfromAccountDropDown();// execution
		//Assertions
		softly.assertThat(loginKeywords.getListOfLoginMenuOptions()).containsSequence("Register").withFailMessage("Assertion Failed :On Login page there is no option of Register");
		softly.assertThat(loginKeywords.getPageTitle()).isEqualTo("Account Login");
		softly.assertAll();
	}
	
	@Test(description="Verify a valid registered user can login into the system using valid set of credentials")
	public void Test2_Verify_A_Valid_User_Having_Account_Can_Login_Using_Valid_UserName_And_Password() {
		// base condition
		homePageKeywords.launchApplication(testdata.getLoginPagePath());
		// execution
		loginKeywords.login(testdata.getCustomerUserName(), testdata.getCustomerPassword());
		//assertions
		softly.assertThat(myAccountKeywords.getListOfAllMyAccountOptions()).containsSequence("View your order history").
				withFailMessage("Assertion Failed : After login user can see the View your order history");
		softly.assertThat(myAccountKeywords.getPageTitle()).isEqualTo("My Account");
		softly.assertAll();
	}
	
	
	
}
