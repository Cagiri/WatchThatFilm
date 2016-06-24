package com.webinfocollector.infoCollector.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.webinfocollector.infoCollector.InfoCollector;

public class InfoCollectorFactory {

	public static InfoCollector getInfoCollector(String c) {

		try {
			@SuppressWarnings("unchecked")
			Class<InfoCollector> clazz = (Class<InfoCollector>) Class.forName(c);
			if (clazz.getSuperclass().getName().equalsIgnoreCase(InfoCollector.class.getName())) {
				Constructor<InfoCollector> constructor = clazz.getDeclaredConstructor(clazz.getClasses());
				constructor.setAccessible(true);
				return (InfoCollector) constructor.newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}