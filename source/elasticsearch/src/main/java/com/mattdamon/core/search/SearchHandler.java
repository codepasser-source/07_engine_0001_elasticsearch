package com.mattdamon.core.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.client.Client;

import com.mattdamon.core.search.SearchOption.SearchLogic;
import com.mattdamon.model.BaseModel;

public class SearchHandler<T extends BaseModel> {

	private SearchSupport searchProvider;

	public SearchHandler(Client searchClient) {
		searchProvider = new SearchSupport(searchClient);
	}

	public SearchSupport getSearchProvider() {
		return searchProvider;
	}

	public void setSearchProvider(SearchSupport searchProvider) {
		this.searchProvider = searchProvider;
	}

	public boolean bulkInsertData(String indexName, String typeName,
			List<T> contentList) {
		List<HashMap<String, Object[]>> contentListMap = new ArrayList<HashMap<String, Object[]>>();
		for (T contentItem : contentList) {
			HashMap<String, Object[]> contentMap = preasModelToHashMap(contentItem);
			contentListMap.add(contentMap);
		}
		return false;
	}

	private HashMap<String, Object[]> preasModelToHashMap(T base) {
		HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
		return contentMap;
	}

	public boolean bulkDeleteData(String indexName,
			HashMap<String, Object[]> contentMap) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean autoBulkUpdateData(String indexName,
			HashMap<String, Object[]> oldContentMap,
			HashMap<String, Object[]> newContentMap) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bulkUpdateData(String indexName,
			HashMap<String, Object[]> oldContentMap,
			HashMap<String, Object[]> newContentMap) {
		// TODO Auto-generated method stub
		return false;
	}

	public long getCount(String[] indexNames, byte[] queryString) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getCount(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			HashMap<String, Object[]> filterContentMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getCount(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			SearchLogic searchLogic,
			HashMap<String, Object[]> filterContentMap, SearchLogic filterLogic) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getComplexCount(String[] indexNames,
			HashMap<String, Object[]> mustSearchContentMap,
			HashMap<String, Object[]> shouldSearchContentMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Map<String, Object>> simpleSearch(String[] indexNames,
			byte[] queryString, int from, int offset, String sortField,
			String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> simpleSearch(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			HashMap<String, Object[]> filterContentMap, int from, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> simpleSearch(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			HashMap<String, Object[]> filterContentMap, int from, int offset,
			String sortField, String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> simpleSearch(String[] indexNames,
			HashMap<String, Object[]> searchContentMap,
			SearchLogic searchLogic,
			HashMap<String, Object[]> filterContentMap,
			SearchLogic filterLogic, int from, int offset, String sortField,
			String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> complexSearch(String[] indexNames,
			HashMap<String, Object[]> mustSearchContentMap,
			HashMap<String, Object[]> shouldSearchContentMap, int from,
			int offset, String sortField, String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getSuggest(String[] indexNames, String fieldName,
			String value, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> group(String indexName,
			HashMap<String, Object[]> mustSearchContentMap,
			HashMap<String, Object[]> shouldSearchContentMap,
			String[] groupFields) {
		// TODO Auto-generated method stub
		return null;
	}

}
