package by.tr.web.controller;

import java.util.ResourceBundle;

public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("localization.config");
	
	private ConfigurationManager() { }
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
