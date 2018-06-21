package com.mattdamon.core.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author MATTDAMON
 * 
 */
public class CoreException extends Exception {

	private static final long serialVersionUID = 4564704984419423245L;

	/**
	 * local
	 */
	private Locale defaultLocale = Locale.CHINA;
	private static final String RESOURCE_NAME = "exception";
	private static final String MESSAGE_PREFIX = "ERROR";

	/**
	 * header 分隔符
	 */
	private static final String HEADER_DELIMITER = "-";
	/**
	 * 文字分隔符
	 */
	private static final String MESSAGE_DELIMITER = ": ";

	/**
	 * 异常消息类型
	 */
	private String category = null;

	/**
	 * 异常代码
	 */
	private String errorCode = null;

	/**
	 * 构造器
	 * 
	 * @param errorDescription
	 *            错误定义
	 */
	public CoreException(final ErrorDescription errorDescription) {
		errorCode = errorDescription.getErrorCode().substring(2);
		category = errorDescription.getErrorCode().substring(0, 2);
	}

	/**
	 * 构造器
	 * 
	 * @param errorDescription
	 *            错误定义
	 * @param args
	 *            参数
	 */
	public CoreException(final ErrorDescription errorDescription,
			final Object... args) {
		errorCode = errorDescription.getErrorCode().substring(2);
		category = errorDescription.getErrorCode().substring(0, 2);
		arguments = args;
		if (args.length > 0) {
			if (args[0] instanceof CoreException) {
				CoreException exception = (CoreException) args[0];
				errorCode = exception.errorCode;
				category = exception.category;
				arguments = exception.arguments;
			}
		}
	}

	/**
	 * 构造器
	 * 
	 * @param throwable
	 *            Throwable
	 */
	public CoreException(final Throwable throwable) {
		this(ErrorDescription.ERROR_OTHER_1, throwable);
	}

	public final String getCategory() {
		return category;
	}

	public final String getErrorCode() {
		return errorCode;
	}

	private Object[] arguments;

	public String getCode() {
		return MESSAGE_PREFIX + HEADER_DELIMITER + category + HEADER_DELIMITER
				+ errorCode;
	}

	@Override
	public String getMessage() {
		return getLocalizedMessage();
	}

	private ResourceBundle getBundle(final Locale locale) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(getBaseName(),
				locale);
		if (resourceBundle.getLocale().getLanguage()
				.equals(locale.getLanguage())) {
			return resourceBundle;
		}

		return ResourceBundle.getBundle(getBaseName());
	}

	private String getBaseName() {
		return RESOURCE_NAME;
	}

	@Override
	public final String getLocalizedMessage() {
		return getLocalizedMessage(getDefaultLocale());
	}

	public final String getLocalizedMessage(final Locale locale) {
		ResourceBundle resourceBundle = this.getBundle(locale);
		String resouceBundleLanguage = resourceBundle.getLocale().getLanguage();

		if (resouceBundleLanguage.length() == 0) {
			resouceBundleLanguage = Locale.getDefault().getLanguage();
		}

		if (!resouceBundleLanguage.equals(locale.getLanguage())) {
			return null;
		}
		String message = "";
		message += MESSAGE_PREFIX;
		message += HEADER_DELIMITER;
		message += category;
		message += HEADER_DELIMITER;
		message += errorCode;
		String key = message;// ERROR-10000001
		message += MESSAGE_DELIMITER;
		String detailFormatString = null;
		detailFormatString = resourceBundle.getString(key);
		message += MessageFormat.format(detailFormatString, arguments);
		return message;
	}

	public final Locale getDefaultLocale() {
		return this.defaultLocale;
	}

}
