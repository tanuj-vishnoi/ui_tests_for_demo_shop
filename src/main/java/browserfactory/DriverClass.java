package browserfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import logger.MainLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class DriverClass.
 */
public class DriverClass {

	/** The configuration. */
	private Map<String, String> configuration;
	private WebDriver webDriver = null;

	/**
	 * Gets the browser.
	 *
	 * @param configuration the configuration
	 * @return the browser
	 */
	public WebDriver getBrowser(Map<String, String> configuration) {
		this.configuration = configuration;
		WebDriver driver = initiliazeDriver();
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
		
		String browserName = configuration.get("browser").toString();
		String browerVersion = configuration.get("browserversion");
		switch (browserName) {
		case "C":
		case "chrome":
		case "Chrome":
		case "CHROME":
			return getChromeDriver();
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
	
	
	@SuppressWarnings("deprecation")
	private WebDriver getChromeDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		String browerVersion = configuration.get("browserversion");
		if(browerVersion.isEmpty())
			WebDriverManager.chromedriver().setup();
		else
			WebDriverManager.chromedriver().version(browerVersion).setup();
		if(Boolean.valueOf(configuration.get("proxy"))){
			Proxy proxy = new Proxy();
			String proxyA = System.getProperty("proxy_address").replace("http://", "").trim();
			MainLogger.logger().info("Adding proxy at "+proxyA);
			proxy.setHttpProxy(proxyA);
			//proxy.setHttpProxy(proxyA);
			proxy.setSslProxy(proxyA);
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			options.addArguments("--always-authorize-plugins=true");
			options.addArguments("start-maximized");
			options.addArguments("--disable--extensions");
			options.addArguments("disable--extensions");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--allow-insecure-localhost");
			options.addArguments("--ssl-version-max");
			options.addArguments("--allow-running-insecure-content");
			 options.addArguments("--ignore-urlfetcher-cert-requests");
			options.setExperimentalOption("prefs", chromePrefs);
			String[] ex = {"ignore-certificate-errors"};
			options.setExperimentalOption("excludeSwitches", ex);
			capabilities. setCapability(CapabilityType. ACCEPT_SSL_CERTS, false);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			capabilities.setCapability("acceptInsecureCerts", true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
			
			//capabilities.addArgument("--ignore-certificate-errors");
			webDriver = new ChromeDriver(capabilities);
			return webDriver;
		}else {
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			options.addArguments("--always-authorize-plugins=true");
			options.addArguments("start-maximized");
			options.addArguments("--disable--extensions");
			options.addArguments("disable--extensions");
			options.addArguments("--disable-backgrounding-occluded-windows");
			options.addArguments("--ignore-certificate-errors");
			options.setExperimentalOption("prefs", chromePrefs);
			capabilities. setCapability(CapabilityType. ACCEPT_SSL_CERTS, false);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability("acceptInsecureCerts", true);
			//capabilities.addArgument("--ignore-certificate-errors");
			webDriver = new ChromeDriver(capabilities);
			return webDriver;
		}
	}
}
