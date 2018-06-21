package com.mattdamon.core.exception;

/**
 * 
 * @author MATTDAMON
 * 
 */
public interface ErrorCode {

	/********************** SYSTEM ERROR ***************************/
	/**
	 * 系统错误-未知异常。
	 */
	int ERROR_OTHER = 10000000;

	/**
	 * 参数个数错误。
	 */
	int ERROR_METHOD_PARAMETER_NUMBER = 10000001;

	/**
	 * 参数值错误。
	 */
	int ERROR_METHOD_PARAMETER_VALUE = 10000002;

	/**
	 * 数据库异常.
	 */
	int ERROR_SQLEXCEPTION_OCCURRED = 10000003;

	/**
	 * 网络连接异常.
	 */
	int ERROR_HTTPEXCEPTION_OCCURRED = 10000004;

	/********************** SEARCH ERROR ***************************/

	/**
	 * _bulkInsertData error
	 */
	int ERROR_SEARCH_BULK_INSERT_OCCURRED = 11000000;

	/**
	 * createRangeQueryBuilder error
	 */
	int ERROR_SEARCH_RANGE_QUERY_OCCURRED = 11000001;
}
