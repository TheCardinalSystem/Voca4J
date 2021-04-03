package com.cardinal.voca4j.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardinal.voca4j.api.Builder.State;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.RequireSet;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class BuilderFactory implements MethodInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(BuilderFactory.class);

	@SuppressWarnings("unchecked")
	public <U extends Request, T extends StateControlledBuilder<U>> Class<T> findBuilder(Class<U> requestClass) {
		LOGGER.info("Searching for builder corresponding to type: " + ((Type) requestClass).getTypeName());
		String canonicalName = requestClass.getCanonicalName();
		String name = canonicalName.substring(canonicalName.lastIndexOf(".") + 1);
		name = canonicalName + "$" + name + "Builder";
		try {
			Class<T> clazz = (Class<T>) Class.forName(name);
			LOGGER.info("Found builder: " + ((Type) clazz).getTypeName());
			return clazz;
		} catch (ClassNotFoundException e) {
			LOGGER.info("No builder found.", e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends StateControlledBuilder<?>> T create(Class<T> builderClass) {
		LOGGER.info("Creating new builder: " + ((Type) builderClass).getTypeName());
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(builderClass);
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}

	private final Set<String> DEFAULT_OBJECT_METHODS = Stream
			.concat(Arrays.stream(Object.class.getMethods()), Arrays.stream(Object.class.getDeclaredMethods()))
			.map(Method::getName).collect(Collectors.toSet());

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		boolean bypassState = false, bypassArgs = false;

		if (method.isAnnotationPresent(BypassChecks.class)) {
			BypassChecks bypass = method.getAnnotation(BypassChecks.class);
			bypassState = bypass.state();
			bypassArgs = bypass.args();
		}

		if (!bypassState) {
			String name = method.getName();
			if (!name.equals("getState") && !DEFAULT_OBJECT_METHODS.contains(name)) {
				State state = ((StateControlledBuilder<?>) obj).getState();

				if (name.equals("recycle") && !state.equals(State.BUILT)) {
					throw new IllegalStateException(state.name() + " | Please call Builder#build first!");
				} else if (state.equals(State.BUILT)) {
					throw new IllegalStateException(state.name() + " | Please call Builder#recycle first!");
				}
			}
		}
		if (!bypassArgs) {
			Parameter[] params = method.getParameters();
			for (int i = 0; i < params.length; i++) {
				boolean allowZero = params[i].isAnnotationPresent(AllowZero.class);
				Class<?> paramType = params[i].getType();
				if (paramType.equals(Integer.class)) {
					Integer tmp = (Integer) args[i];
					if (tmp < 1 && !allowZero)
						throw new IllegalArgumentException(tmp + " | Expected value greater than 0.");
					else if (tmp < 0 && allowZero)
						throw new IllegalArgumentException(tmp + " | Expected value greater than or equal to 0.");
				} else if (paramType.equals(int.class)) {
					int tmp = (int) args[i];
					if (tmp < 1 && !allowZero)
						throw new IllegalArgumentException(tmp + " | Expected value greater than 0.");
					else if (tmp < 0 && allowZero)
						throw new IllegalArgumentException(tmp + " | Expected value greater than or equal to 0.");
				} else if (paramType.isArray()) {
					if (paramType.getComponentType().equals(int.class)) {
						int[] tmp = (int[]) args[i];
						for (int j = 0; j < tmp.length; j++) {
							if (tmp[j] < 1 && !allowZero)
								throw new IllegalArgumentException(
										tmp[j] + " at index " + j + " | Expected value greater than 0.");
							else if (tmp[j] < 0 && allowZero)
								throw new IllegalArgumentException(
										tmp[j] + " at index " + j + " | Expected value greater than or equal to 0.");
						}
					} else if (paramType.getComponentType().equals(Integer.class)) {
						Integer[] tmp = (Integer[]) args[i];
						for (int j = 0; j < tmp.length; j++) {
							if (tmp[j] < 1 && !allowZero)
								throw new IllegalArgumentException(
										tmp[j] + " at index " + j + " | Expected value greater than 0.");
							else if (tmp[j] < 0 && allowZero)
								throw new IllegalArgumentException(
										tmp[j] + " at index " + j + " | Expected value greater than or equal to 0.");
						}
					} else {
						Object[] tmp = (Object[]) args[i];
						if (tmp != null && tmp.length == 0)
							args[i] = null;
					}
				}
			}
		}

		if (method.isAnnotationPresent(RequireSet.class)) {
			RequireSet requireSet = method.getAnnotation(RequireSet.class);
			String field = requireSet.value();
			Field requestField = null, requiredField = null;
			Object get = null;
			try {
				requestField = StateControlledBuilder.class.getDeclaredField("built");
				requestField.setAccessible(true);
				get = requestField.get(obj);
				requiredField = get.getClass().getDeclaredField(field);
			} finally {
				if (requestField != null)
					requestField.setAccessible(false);
			}
			if (requiredField != null) {
				Object value = null;
				try {
					requiredField.setAccessible(true);
					value = requiredField.get(get);
				} finally {
					if (requiredField != null)
						requiredField.setAccessible(false);
				}
				if (value == null)
					throw new IllegalStateException(field + " must be set before you can invoke this method.");
			}
		}

		return proxy.invokeSuper(obj, args);
	}

}
