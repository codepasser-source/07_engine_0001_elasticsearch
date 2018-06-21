package com.mattdamon.core.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.common.Nullable;

import com.mattdamon.core.exception.CoreException;
import com.mattdamon.core.search.SearchOption.SearchLogic;

/**
 * Elastic search engine provider
 * 
 * @author mattdamon
 *
 */
public interface SearchProvider {

	/**
	 * 批量插入数据
	 * 
	 * @param indexName
	 *            索引名称
	 * @param contentMap
	 *            数据
	 * @return
	 */
	boolean bulkInsertData(String indexName,
			HashMap<String, Object[]> contentMap) throws CoreException;

	/**
	 * 批量删除数据，危险
	 * 
	 * @param indexName
	 *            索引名称
	 * @param contentMap
	 *            删除数据
	 * @return
	 */
	boolean bulkDeleteData(String indexName,
			HashMap<String, Object[]> contentMap) throws CoreException;

	/**
	 * 批量更新数据，先删除，再插入，需要传递新数据的完整数据
	 * 
	 * @param indexName
	 *            索引名称
	 * @param oldContentMap
	 *            旧数据
	 * @param newContentMap
	 *            新数据
	 * @return
	 */
	boolean bulkUpdateData(String indexName,
			HashMap<String, Object[]> oldContentMap,
			HashMap<String, Object[]> newContentMap) throws CoreException;

	/**
	 * 批量更新数据，先删除，再插入，只需要传递新数据的差异键值
	 * 
	 * @param indexName
	 *            索引名称
	 * @param oldContentMap
	 *            旧数据
	 * @param newContentMap
	 *            新数据
	 * @return
	 */
	boolean autoBulkUpdateData(String indexName,
			HashMap<String, Object[]> oldContentMap,
			HashMap<String, Object[]> newContentMap) throws CoreException;

	/**
	 * 获得搜索结果总数（支持json版本）
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param queryString
	 *            所搜字符串
	 * @return
	 */
	long getCount(String[] indexNames, byte[] queryString) throws CoreException;

	/**
	 * 获得搜索结果总数
	 * 
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param searchContentMap
	 *            搜索内容HashMap
	 * @param filterContentMap
	 *            过滤内容HashMap
	 * @return
	 */
	long getCount(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			@Nullable HashMap<String, Object[]> filterContentMap)
			throws CoreException;

	/**
	 * 获得搜索结果总数
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param searchContentMap
	 *            搜索内容HashMap
	 * @param searchLogic
	 *            搜索条件之间的逻辑关系（must表示条件必须都满足，should表示只要有一个条件满足就可以）
	 * @param filterContentMap
	 *            过滤内容HashMap
	 * @param filterLogic
	 *            过滤条件之间的逻辑关系（must表示条件必须都满足，should表示只要有一个条件满足就可以）
	 * @return
	 */
	long getCount(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			SearchLogic searchLogic,
			@Nullable HashMap<String, Object[]> filterContentMap,
			@Nullable SearchLogic filterLogic) throws CoreException;

	/**
	 * 获得搜索结果总数（复杂）
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param mustSearchContentMap
	 *            must内容HashMap
	 * @param shouldSearchContentMap
	 *            should内容HashMap
	 * @return
	 */
	long getComplexCount(String[] indexNames,
			@Nullable HashMap<String, Object[]> mustSearchContentMap,
			@Nullable HashMap<String, Object[]> shouldSearchContentMap)
			throws CoreException;

	/**
	 * 搜索（支持json版本）
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param queryString
	 *            搜索字符串
	 * @param from
	 *            从第几条记录开始（必须大于等于0）
	 * @param offset
	 *            一共显示多少条记录（必须大于0）
	 * @param sortField
	 *            排序字段名称
	 * @param sortType
	 *            排序方式（asc，desc）
	 * @return
	 */
	List<Map<String, Object>> simpleSearch(String[] indexNames,
			byte[] queryString, int from, int offset,
			@Nullable String sortField, @Nullable String sortType)
			throws CoreException;

	/**
	 * 搜索（去掉排序参数的简化版本）
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param searchContentMap
	 *            搜索内容HashMap
	 * @param filterContentMap
	 *            过滤内容HashMap
	 * @param from
	 *            从第几条记录开始（必须大于等于0）
	 * @param offset
	 *            一共显示多少条记录（必须大于0）
	 * @return
	 */
	List<Map<String, Object>> simpleSearch(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			@Nullable HashMap<String, Object[]> filterContentMap, int from,
			int offset) throws CoreException;

	/**
	 * 搜索
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param searchContentMap
	 *            搜索内容HashMap
	 * @param filterContentMap
	 *            过滤内容HashMap
	 * @param from
	 *            从第几条记录开始（必须大于等于0）
	 * @param offset
	 *            一共显示多少条记录（必须大于0）
	 * @param sortField
	 *            排序字段名称
	 * @param sortType
	 *            排序方式（asc，desc）
	 * @return
	 */
	List<Map<String, Object>> simpleSearch(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			@Nullable HashMap<String, Object[]> filterContentMap, int from,
			int offset, @Nullable String sortField, @Nullable String sortType)
			throws CoreException;

	/**
	 * 搜索
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param searchContentMap
	 *            搜索内容HashMap
	 * @param searchLogic
	 *            搜索条件之间的逻辑关系（must表示条件必须都满足，should表示只要有一个条件满足就可以）
	 * @param filterContentMap
	 *            过滤内容HashMap
	 * @param filterLogic
	 *            过滤条件之间的逻辑关系（must表示条件必须都满足，should表示只要有一个条件满足就可以）
	 * @param from
	 *            从第几条记录开始（必须大于等于0）
	 * @param offset
	 *            一共显示多少条记录（必须大于0）
	 * @param sortField
	 *            排序字段名称
	 * @param sortType排序方式
	 *            （asc，desc）
	 * @return
	 */
	List<Map<String, Object>> simpleSearch(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			SearchLogic searchLogic,
			@Nullable HashMap<String, Object[]> filterContentMap,
			@Nullable SearchLogic filterLogic, int from, int offset,
			@Nullable String sortField, @Nullable String sortType)
			throws CoreException;

	/**
	 * 搜索（复杂）
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param mustSearchContentMap
	 *            must内容HashMap
	 * @param shouldSearchContentMap
	 *            should内容HashMap
	 * @param from
	 *            从第几条记录开始（必须大于等于0）
	 * @param offset
	 *            一共显示多少条记录（必须大于0）
	 * @param sortField
	 *            排序字段名称
	 * @param sortType
	 *            排序方式（asc，desc）
	 * @return
	 */
	List<Map<String, Object>> complexSearch(String[] indexNames,
			@Nullable HashMap<String, Object[]> mustSearchContentMap,
			@Nullable HashMap<String, Object[]> shouldSearchContentMap,
			int from, int offset, @Nullable String sortField,
			@Nullable String sortType) throws CoreException;

	/**
	 * 获得推荐列表
	 * 
	 * @param indexNames
	 *            索引名称
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            值
	 * @param count
	 *            数量
	 * @return
	 */
	List<String> getSuggest(String[] indexNames, String fieldName,
			String value, int count) throws CoreException;

	/**
	 * 分组统计
	 * 
	 * @param indexName
	 *            索引名称
	 * @param mustSearchContentMap
	 *            must内容HashMap
	 * @param shouldSearchContentMap
	 *            should内容HashMap
	 * @param groupFields
	 *            分组字段
	 * @return
	 */
	Map<String, String> group(String indexName,
			@Nullable HashMap<String, Object[]> mustSearchContentMap,
			@Nullable HashMap<String, Object[]> shouldSearchContentMap,
			String[] groupFields) throws CoreException;
}