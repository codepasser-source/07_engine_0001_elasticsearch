package com.mattdamon.core.search;

import java.io.Serializable;

/**
 * Elastic search engine option
 * 
 * @author mattdamon
 *
 */
public class SearchOption implements Serializable {

	private static final long serialVersionUID = 658467190883022073L;

	/**
	 * 搜索类型
	 * 
	 * @author mattdamon
	 *
	 */
	public enum SearchType {
		/* 按照quert_string搜索，搜索非词组时候使用 */
		querystring,
		/* 按照区间搜索 */
		range,
		/* 按照词组搜索，搜索一个词时候使用 */
		term
	}

	/**
	 * 搜索逻辑
	 * 
	 * @author mattdamon
	 *
	 */
	public enum SearchLogic {
		/* 逻辑must关系 */
		must,
		/* 逻辑should关系 */
		should
	}

	/**
	 * 数据过滤
	 * 
	 * @author mattdamon
	 *
	 */
	public enum DataFilter {
		/* 只显示有值的 */
		exists,
		/* 显示没有值的 */
		notExists,
		/* 显示全部 */
		all
	}

	private SearchLogic searchLogic = SearchLogic.must;
	private SearchType searchType = SearchType.querystring;
	private DataFilter dataFilter = DataFilter.exists;
	/* querystring精度，取值[1-100]的整数 */
	private String queryStringPrecision = "100";
	/* 排名权重 */
	private float boost = 1.0f;
	private boolean highlight = false;

	public SearchOption() {
	}

	public SearchOption(SearchType searchType, SearchLogic searchLogic,
			String queryStringPrecision, DataFilter dataFilter, float boost,
			int highlight) {
		this.setSearchLogic(searchLogic);
		this.setSearchType(searchType);
		this.setQueryStringPrecision(queryStringPrecision);
		this.setDataFilter(dataFilter);
		this.setBoost(boost);
		this.setHighlight(highlight > 0 ? true : false);
	}

	public DataFilter getDataFilter() {
		return this.dataFilter;
	}

	public void setDataFilter(DataFilter dataFilter) {
		this.dataFilter = dataFilter;
	}

	public boolean isHighlight() {
		return this.highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public float getBoost() {
		return this.boost;
	}

	public void setBoost(float boost) {
		this.boost = boost;
	}

	public SearchLogic getSearchLogic() {
		return this.searchLogic;
	}

	public void setSearchLogic(SearchLogic searchLogic) {
		this.searchLogic = searchLogic;
	}

	public SearchType getSearchType() {
		return this.searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	public String getQueryStringPrecision() {
		return this.queryStringPrecision;
	}

	public void setQueryStringPrecision(String queryStringPrecision) {
		this.queryStringPrecision = queryStringPrecision;
	}

	public static long getSerialversionuid() {
		return SearchOption.serialVersionUID;
	}

}
