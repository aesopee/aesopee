package com.slickcode.baseframework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	
	private static Properties prop = null;
	static {

		String configFile = System.getenv("CONFIG_FILE");
		prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(configFile);

			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getPropertyValue(String properntyKey) {
		String value = prop.getProperty(properntyKey);
		
		if (null == value) {
			System.out.println("No "+properntyKey+" property exists");
		}
		return value;
	}
	
}
