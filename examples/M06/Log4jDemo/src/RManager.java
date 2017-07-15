import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

// Note: this is a very detailed log
// Before run the project you have to create and include the log4j user library
public class RManager {

	// this way we obtain the logger
	private static final Logger log = Logger.getLogger(RManager.class);

	private static ResourceBundle bundle;

	private static String baseName;

	public static void init(String baseName) {
		if (RManager.baseName != null) {
			log.warn("RManager has already been initialized");
			return;
		}

		RManager.baseName = baseName;
		log.trace("baseName --> " + baseName);

		Locale defaultLocale = Locale.getDefault();
		log.trace("defaultLocale --> " + defaultLocale);

		bundle = ResourceBundle.getBundle(baseName, defaultLocale);
		log.debug("Set bundle with the default locale, defaultLocale --> "
				+ defaultLocale);

		log.info("RManager has been successfully initialized");
	}

	public static String getValue(String key) {
		if (bundle == null) {
			log.error("RManager not initialized yet. You must run init method first.");
			throw new IllegalStateException("RManager not initialized yet.");
		}

		log.trace("key --> " + key);

		String value = bundle.getString(key);
		log.trace("value --> " + value);

		if (Level.DEBUG.isGreaterOrEqual(log.getEffectiveLevel()))
			log.debug("Found value for key=" + key + " --> " + value);

		return value;
	}

	public static void setLocale(Locale locale) {
		if (bundle == null) {
			log.error("RManager not initialized yet. You must run init method first.");
			throw new IllegalStateException("RManager not initialized yet.");
		}

		log.trace("locale --> " + locale);

		bundle = ResourceBundle.getBundle(baseName, locale);
		if (Level.DEBUG.isGreaterOrEqual(log.getEffectiveLevel()))
			log.debug("Set bundle with the new locale, locale --> " + locale);
	}

	public static void main(String[] args) {
		// initialize log4j system from the configuration file log4j.properties
		PropertyConfigurator.configure("log4j.properties");

		RManager.init("resource");
		System.out.println(RManager.getValue("hanger"));

		RManager.setLocale(new Locale("ru", "UA", "Kharkov"));
		System.out.println(RManager.getValue("hanger"));
	}

}
