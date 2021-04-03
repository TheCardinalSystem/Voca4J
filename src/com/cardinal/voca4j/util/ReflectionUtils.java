package com.cardinal.voca4j.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);

	public static void copyFields(Object source, Object destination) {
		LOGGER.info("Reflectively copying object");
		Field[] fields = destination.getClass().getDeclaredFields();
		Class<?> destClass = destination.getClass();

		for (Field srcField : fields) {
			Field destField = null;
			try {
				destField = destClass.getDeclaredField(srcField.getName());
			} catch (NoSuchFieldException | SecurityException e1) {
				LOGGER.info("Field " + srcField.getName() + " not present in target class: " + destClass.getName());
				continue;
			}

			srcField.setAccessible(true);
			destField.setAccessible(true);

			try {
				srcField.set(destination, srcField.get(source));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				LOGGER.warn("Error trying to set field: " + srcField.getName());
			} finally {
				srcField.setAccessible(false);
				destField.setAccessible(false);
			}
		}
	}

}
