package stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import browserfactory.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	@Before
	public void LaunchBrowser(Scenario scenario) throws Exception {
		DriverManager.setDriver(scenario.getName());
	}
	

	public final void TakeScreenshotAfterStep(Scenario scenario) {
		final byte[] imgBytes = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.embed(imgBytes, "image/png", "Image");
	}

	@After
	public void CloseBrowser(Scenario scenario) {
		if (scenario.isFailed()) {
			TakeScreenshotAfterStep(scenario);
		}
		DriverManager.closeDriver();
	}
}
