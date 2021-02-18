package browserfactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

// TODO: Auto-generated Javadoc
/**
 * The Class DriverClass.
 */
public class DriverClass {

	/** The configuration. */
	private Map<String, String> configuration;
	
	/**
	 * Gets the browser.
	 *
	 * @param configuration the configuration
	 * @return the browser
	 */
	public WebDriver getBrowser(Map<String, String> configuration) {
		this.configuration = configuration;
		WebDriver driver  = initiliazeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.valueOf(configuration.get("implicitwait")), TimeUnit.SECONDS);
		return driver;
	}
	
	
	/**
	 * Initiliaze driver.
	 *
	 * @return the web driver
	 */
	@SuppressWarnings("deprecation")
	private WebDriver initiliazeDriver(){
		WebDriver webDriver = null;
		String browserName = configuration.get("browser").toString();
		String browerVersion = configuration.get("browserversion");
		switch (browserName) {
		case "C":
		case "chrome":
		case "Chrome":
		case "CHROME":
			if(browerVersion.isEmpty())
				WebDriverManager.chromedriver().setup();
			else
				WebDriverManager.chromedriver().version(browerVersion).setup();
			ChromeOptions options = new ChromeOptions();
			webDriver = new ChromeDriver(options);
			return webDriver;
		case "F":
		case "firefox":
		case "FIREFOX":
		case "Firefox":
			if(browerVersion.isEmpty())// this line check the browser driver version given in config file
				WebDriverManager.firefoxdriver().setup();// if the browser version is empty then it automatically pick the latest
			else
				WebDriverManager.firefoxdriver().version(browerVersion).setup();//otherwise download driver as per the given version
			LoggingPreferences pref = new LoggingPreferences();
		    pref.enable(LogType.BROWSER, Level.OFF);
		    pref.enable(LogType.CLIENT, Level.OFF);
		    pref.enable(LogType.DRIVER, Level.OFF);
		    pref.enable(LogType.PERFORMANCE, Level.OFF);
		    pref.enable(LogType.PROFILER, Level.OFF);
		    pref.enable(LogType.SERVER, Level.OFF);
		    DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
		    desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, pref);
		    desiredCapabilities.setCapability("marionette", true);
		    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
		    webDriver = new FirefoxDriver(desiredCapabilities);
			return webDriver;
		default:{
			return webDriver;
			}
		}
	}
}
