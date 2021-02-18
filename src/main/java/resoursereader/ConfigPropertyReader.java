package resoursereader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;


// TODO: Auto-generated Javadoc
/**
 * This is the utility class for data read write.
 * It reads config property file.
 * placed in project directory by default
 *
 */
public final class ConfigPropertyReader {

     /**
	 *This varaible contains the name of default config property file.
	 *
	 */
	private static String defaultConfigFile = System.getProperty("user.dir")+File.separator+"Config.properties";
	/**
	 * construtor of this class.
	 * 
	 */
	
	public static HashMap<String, String> configSettings;
	
	private ConfigPropertyReader() {
	
	}

	/**
	 * Get all property form config file.
	 * @return map of all key value
	 */
	private static HashMap<String, String> readAllPropertyVlauesFromConfigFile() {
		HashMap<String, String> mymap = new HashMap<String, String>();
		Properties prop;
		try {
			prop = ResourceLoader.loadProperties(defaultConfigFile);
			for (final Entry<Object, Object> entry : prop.entrySet()) {
				mymap.put((String) entry.getKey(), (String) entry.getValue());
			}
			return mymap;
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * Method reads all the global property from the config file present in the project directory
	 *
	 * @return the hash map
	 */
	public static HashMap<String, String> _getSessionConfig() {
		configSettings = ConfigPropertyReader.readAllPropertyVlauesFromConfigFile();
		Properties prop = System.getProperties();
		for (Object ob : configSettings.keySet()) {
			if (prop.keySet().contains(ob) && !prop.get(ob).toString().isEmpty()) {
				System.out.println("Replaced " + ob.toString() + "with " + prop.get(ob).toString());
				configSettings.replace(ob.toString(), prop.get(ob).toString());
			}
		}
		return configSettings;
	}
}
