package org.uqbar.vainilla.utils;

import java.awt.Color;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Tuning {

	static Properties properties = new Properties();

	static public void load(String file) {
		try {
			properties.load(Tuning.class.getClassLoader().getResourceAsStream(
					file));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static public void load() {
		load("values.properties");
	}

	static public String getString(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	static public String getString(String key) {
		return properties.getProperty(key);
	}

	static public Long getLong(String key, Long defaultValue) {
		String outString = properties.getProperty(key);
		return (outString == null) ? defaultValue : Long.parseLong(outString);

	}

	static public Long getLong(String key) {
		return getLong(key, null);
	}

	static public Integer getInteger(String key, Integer defaultValue) {
		String outString = properties.getProperty(key);
		return (outString == null) ? defaultValue : Integer.parseInt(outString);

	}

	static public Integer getInteger(String key) {
		return getInteger(key, null);
	}

	static public Double getDouble(String key, Double defaultValue) {
		String outString = properties.getProperty(key);
		return (outString == null) ? defaultValue : Double
				.parseDouble(outString);

	}

	static public Double getDouble(String key) {
		return getDouble(key, null);
	}

	static public Color getColor(String key, Color defaultValue) {
		String outString = getString(key);
		try {
			if (outString == null) {
				return defaultValue;
			}
			Field field = Color.class.getField(outString);
			return (Color) field.get(null);
		} catch (Exception e) {
			throw new RuntimeException("No existe el color " + outString, e);
		}
	}

	static public <T> Class<T> getClass(String key, Class<T> defaultValue) {
		try {
			String outString = properties.getProperty(key);
			return (outString == null) ? defaultValue : (Class<T>)Class
					.forName(outString);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	static public Class getClass(String key) {
		return getClass(key, null);
	}
	
	static public <T> T newInstance(String key, Class<T> defaultClass) {
		try {
			return getClass(key, defaultClass).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	static public <T> T newInstance(String key, Class<T> defaultClass, Class[] parameterTypes, Object...params) {
		try {
			return getClass(key, defaultClass).getConstructor(parameterTypes).newInstance(params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
