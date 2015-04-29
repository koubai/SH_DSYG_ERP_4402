package com.cn.common.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @name PropertiesConfig.java
 * @author Frank
 * @time 2013-9-24下午8:58:09
 * @version 1.0
 */
public class PropertiesConfig {
	
	private static final Logger log = LogManager.getLogger(PropertiesConfig.class);
	private static final Map<String, String> map = new HashMap<String, String>();
	
	static {
		loadPropertiesMap();
	}
	
	/**
	 * 加载属性文件
	 */
	private static void loadPropertiesMap() {
		ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
		Resource res = resourceResolver.getResource("classpath:/config/config.properties");
		Properties props = null;
		try {
			props = new Properties();
			props.load(res.getInputStream());
		} catch (IOException e) {
			log.error("Loading properties failed", e);
		}
		if(props != null) {
			Enumeration<?> en = props.propertyNames();
			while(en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String value = props.getProperty(key);
				map.put(key.trim(), value.trim());
			}
		}
	}
	
	/**
	 * 根据key获取Properties里的value
	 * @param key
	 * @return
	 */
	public static String getPropertiesValueByKey(String key) {
		return map.get(key);
	}
}
