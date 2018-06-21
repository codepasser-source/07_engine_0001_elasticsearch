package com.mattdamon.core.log;

import java.io.Serializable;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author MATTDAMON
 * 
 */
public class CoreLogger {

	private static final String LOG_CATEGORY = "CATEGORY";
	private static final String LOG_CONTENT = "CONTENT";

	private static final String LOG_CATEGORY_CONSOLE = "[NEU-ECOMMERCE-CONSOLE-LOG]";
	private static final String LOG_CATEGORY_SYSTEM = "[NEU-ECOMMERCE-SYSTEM-LOG]";
	private static final String LOG_CATEGORY_ACCESS = "[NEU-ECOMMERCE-ACCESS-LOG]";
	private static final String LOG_CATEGORY_CONTROLLER = "[NEU-ECOMMERCE-CONTROLLER-LOG]";
	private static final String LOG_CATEGORY_SERVICE = "[NEU-ECOMMERCE-SERVICE-LOG]";

	/**
	 * 系统console日志， Level DEBUG
	 * 
	 * @param classze
	 * @param log
	 */
	public static synchronized <T> void consoleLog(Class<T> classze,
			Serializable debug) {
		Logger logger = LoggerFactory.getLogger(classze);
		HashMap<String, Serializable> log = new HashMap<String, Serializable>();
		log.put(LOG_CATEGORY, LOG_CATEGORY_CONSOLE);
		log.put(LOG_CONTENT, debug);
		logger.debug(JSONObject.toJSONString(log));
	}

	/**
	 * 系统错误日志,service.log, Level ERROR
	 * 
	 * @param classze
	 * @param exception
	 */
	public static synchronized <T> void systemLog(Class<T> classze,
			Serializable exception) {
		Logger logger = LoggerFactory.getLogger(classze);
		HashMap<String, Serializable> log = new HashMap<String, Serializable>();
		log.put(LOG_CATEGORY, LOG_CATEGORY_SYSTEM);
		log.put(LOG_CONTENT, exception);
		logger.error(JSONObject.toJSONString(log));
	}

	/**
	 * 系统访问日志，数据库存储, level INFO
	 * 
	 * @param classze
	 * @param log
	 */
	public static synchronized <T> void accessLog(Class<T> classze,
			Serializable userBehavior) {
		Logger logger = LoggerFactory.getLogger(classze);
		HashMap<String, Serializable> log = new HashMap<String, Serializable>();
		log.put(LOG_CATEGORY, LOG_CATEGORY_ACCESS);
		log.put(LOG_CONTENT, userBehavior);
		logger.info(JSONObject.toJSONString(log));
	}

	public static synchronized <T> void controllerLog(Class<T> classze,
			Serializable info) {
		Logger logger = LoggerFactory.getLogger(classze);
		HashMap<String, Serializable> log = new HashMap<String, Serializable>();
		log.put(LOG_CATEGORY, LOG_CATEGORY_CONTROLLER);
		log.put(LOG_CONTENT, info);
		logger.info(JSONObject.toJSONString(log));
	}

	public static synchronized <T> void serviceLog(Class<T> classze,
			Serializable info) {
		Logger logger = LoggerFactory.getLogger(classze);
		HashMap<String, Serializable> log = new HashMap<String, Serializable>();
		log.put(LOG_CATEGORY, LOG_CATEGORY_SERVICE);
		log.put(LOG_CONTENT, info);
		logger.info(JSONObject.toJSONString(log));
	}

}
