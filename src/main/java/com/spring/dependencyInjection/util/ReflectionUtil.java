package com.spring.dependencyInjection.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

	private static ReflectionUtil refObj;

	@SuppressWarnings("serial")
	private static HashMap<String, String> objectBeanMap = new HashMap<String, String>() {
		{
			put("Commits", "com.spring.dependencyInjection.entity.Commits");

		}
	};

	private static HashMap<String, HashMap> objGetterPropsMap = new HashMap<String, HashMap>();
	private static HashMap<String, HashMap> objSetterPropsMap = new HashMap<String, HashMap>();

	/**
	 * Create private constructor
	 */
	private ReflectionUtil() {
	}

	/**
	 * Create a static method to get instance.
	 */
	public static ReflectionUtil getInstance() {
		if (refObj == null) {
			logger.debug("Inside singleton contructor ::");
			refObj = new ReflectionUtil();
			setupMethods();
		}
		return refObj;
	}

	public static void setupMethods() {
		logger.debug("Start method :: setupMethods");
		// iterate over all objects to get the corresponding bean class
		for (Iterator iterator = objectBeanMap.keySet().iterator(); iterator.hasNext();) {

			String objName = (String) iterator.next();
			HashMap<String, Method> propGetMethodsMap = new HashMap<String, Method>();
			HashMap<String, Method> propSetMethodsMap = new HashMap<String, Method>();

			try {
				Class<?> cls = Class.forName(objectBeanMap.get(objName));
				for (PropertyDescriptor pd : Introspector.getBeanInfo(cls).getPropertyDescriptors()) {
					if (!"class".equals(pd.getName())) {
						if (pd.getReadMethod() != null)
							propGetMethodsMap.put(pd.getName(), pd.getReadMethod());
						if (pd.getWriteMethod() != null)
							propSetMethodsMap.put(pd.getName(), pd.getWriteMethod());
					}
				}
			} catch (ClassNotFoundException | IntrospectionException e) {
				logger.error("Exception occurred in setupMethods for Reflection::", e);
			}
			objGetterPropsMap.put(objName, propGetMethodsMap);
			objSetterPropsMap.put(objName, propSetMethodsMap);
		}
		logger.debug("End method :: setupMethods");
	}

	public HashMap<String, HashMap> getObjGetterPropsMap() {
		return objGetterPropsMap;
	}

	public HashMap<String, HashMap> getObjSetterPropsMap() {
		return objSetterPropsMap;
	}

	public Method getSetterMethod(String objectName, String propName) {
		HashMap propMethods = getObjSetterPropsMap().get(objectName);
		return (Method) propMethods.get(propName);
	}

	public Method getGetterMethod(String objectName, String propName) {
		HashMap propMethods = getObjGetterPropsMap().get(objectName);
		return (Method) propMethods.get(propName);
	}

}
