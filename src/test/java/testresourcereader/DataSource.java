package testresourcereader;

public class DataSource {

	public YamlReader yamlData;
	
	public DataSource() {
		yamlData = new YamlReader(); 
	}
	
	public String getApplicationLandingPageUrl() {
		return yamlData.getData("app_url");
	}
	
	public String getLoginPagePath() {
		return yamlData.getData("app_url") + yamlData.getData("app_login_path");
	}
	
	public String getCustomerUserName() {
		return yamlData.getData("users.user1");
	}
	
	public String getCustomerPassword() {
		return yamlData.getData("users.password1");
	}
	
	public String getProductForSearch() {
		return yamlData.getData("products.product1");
	}
	
}
