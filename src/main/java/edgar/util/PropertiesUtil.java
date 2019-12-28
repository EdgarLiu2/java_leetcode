package edgar.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private String env;
	private Properties props = new Properties();

	public PropertiesUtil() {
		this(System.getProperty("ENV", "dev"));
	}
	
	public PropertiesUtil(String env) {
		this.env = env;
		String propFileName = String.format("%s.config.properties", this.env);
		
		InputStream input = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (input != null) {
			try {
				props.load(input);
			} catch (IOException e) {
				logger.error("Can't load from props file " + propFileName, e);
			}
		} else {
			logger.warn("Fail to find props file: {}", propFileName);
		}
	}
	
	public String getProperty(String key) {
		if (props == null) {
			return "";
		}
		return props.getProperty(key, "");
	}
	
	public boolean getBooleanProperty(String key) {
		String value = getProperty(key);
		boolean result = false;
		
		if(!value.isEmpty()) {
			result = Boolean.parseBoolean(value);
		}
		
		return result;
	}
	
	public int getIntProperty(String key) {
		String value = getProperty(key);
		int result = 0;
		
		if(!value.isEmpty()) {
			result =Integer.parseInt(value);
		}
		
		return result;
	}
}
