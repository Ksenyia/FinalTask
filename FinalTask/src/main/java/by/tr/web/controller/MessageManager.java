package by.tr.web.controller;

import java.util.ResourceBundle;

public class MessageManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("localization.locale");
	private MessageManager() { }
	public static String getProperty(String key) {
	return resourceBundle.getString(key);
	}
}
