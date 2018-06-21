package com.mattdamon.elasticsearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mattdamon.common.Global;
import com.mattdamon.core.search.SearchManager;
import com.mattdamon.core.search.SearchOption.SearchLogic;
import com.mattdamon.core.search.SearchSupport;

/**
 * Unit test for simple App.
 */
public class SearchIndexTest {

	private static SearchSupport searcher;

	@BeforeClass
	public static void beforeClass() {
		System.out.println(">>>>>>>>>>>>>beforeClass");

	}

	@Before
	public void before() {
		System.out.println(">>>>>>>>>>>>>before");
		setSearcher(new SearchSupport(SearchManager.getClient()));
		SearchManager.checkNode();
	}

	@After
	public void after() {
		System.out.println(">>>>>>>>>>>>>after");
		SearchManager.closeClient();
	}

	@AfterClass
	public static void afterClass() {
		System.out.println(">>>>>>>>>>>>>afterClass");
	}

	/**
	 * simpleSearch(String[] indexNames, HashMap<String, Object[]>
	 * searchContentMap, HashMap<String, Object[]> filterContentMap, int from,
	 * int offset)
	 *
	 * getCount(String[] indexNames, HashMap<String, Object[]> searchContentMap,
	 * HashMap<String, Object[]> filterContentMap)
	 *
	 */
	@Test
	public void testSimpleSearch5() {

		System.out.println(">>>>>>>>>>>>>testSimpleSearch5");
		try {

			HashMap<String, Object[]> searchContentMap = new HashMap<String, Object[]>();
			searchContentMap.put("name", new Object[] { "扩展" });

			List<Map<String, Object>> searchResult = searcher.simpleSearch(
					new String[] { Global.ES_INDEX_GOODS }, searchContentMap,
					null, 0, 10);

			System.out.println(searchResult);
			System.out.println(searchResult.size());

			long count = searcher.getCount(
					new String[] { Global.ES_INDEX_GOODS }, searchContentMap,
					null);
			System.out.println(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * simpleSearch(String[] indexNames, HashMap<String, Object[]>
	 * searchContentMap, HashMap<String, Object[]> filterContentMap, int from,
	 * int offset, String sortField, String sortType)
	 * 
	 * getCount(String[] indexNames, HashMap<String, Object[]> searchContentMap,
	 * HashMap<String, Object[]> filterContentMap)
	 */
	@Test
	public void testSimpleSearch7() {

		System.out.println(">>>>>>>>>>>>>testSimpleSearch7");
		try {

			HashMap<String, Object[]> searchContentMap = new HashMap<String, Object[]>();
			searchContentMap.put("class", new Object[] { "电视" });

			List<Map<String, Object>> searchResult = searcher.simpleSearch(
					new String[] { Global.ES_INDEX_GOODS }, searchContentMap,
					null, 0, 12, "id", "asc");
			System.out.println(searchResult);
			System.out.println(searchResult.size());

			long count = searcher.getCount(
					new String[] { Global.ES_INDEX_GOODS }, searchContentMap,
					null);
			System.out.println(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * simpleSearch(String[] indexNames, HashMap<String, Object[]>
	 * searchContentMap, SearchLogic searchLogic, HashMap<String, Object[]>
	 * filterContentMap, SearchLogic filterLogic, int from, int offset, String
	 * sortField, String sortType)
	 * 
	 * getCount(String[] indexNames, HashMap<String, Object[]> searchContentMap,
	 * SearchLogic searchLogic, HashMap<String, Object[]> filterContentMap,
	 * SearchLogic filterLogic)
	 * 
	 ***/
	@Test
	public void testSimpleSearch9() {

		System.out.println(">>>>>>>>>>>>>testSimpleSearch9");
		try {

			HashMap<String, Object[]> searchContentMap = new HashMap<String, Object[]>();
			searchContentMap.put("name", new Object[] { "窄边" });

			HashMap<String, Object[]> filterContentMap = new HashMap<String, Object[]>();
			filterContentMap.put("name", new Object[] { "创维" });

			List<Map<String, Object>> searchResult = searcher.simpleSearch(
					new String[] { Global.ES_INDEX_GOODS }, searchContentMap,
					SearchLogic.must, filterContentMap, SearchLogic.should, 0,
					100, "id", "asc");

			System.out.println(searchResult);
			System.out.println(searchResult.size());

			long count = searcher.getCount(
					new String[] { Global.ES_INDEX_GOODS }, searchContentMap,
					SearchLogic.must, filterContentMap, SearchLogic.should);

			System.out.println(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * simpleSearch(String[] indexNames, byte[] queryString, int from, int
	 * offset, String sortField, String sortType)
	 * 
	 * getCount(String[] indexNames, byte[] queryString)
	 **/
	@Test
	public void testSimpleSearch() {

		System.out.println(">>>>>>>>>>>>>testSimpleSearch");
		try {
			// String query1 = "{\"term\": {\"id\":\"goods\"}}";
			String query2 = "{\"query_string\":{ \"default_field\":\"goods.class\",\"query\":\"电\"}}";

			List<Map<String, Object>> searchResult = searcher.simpleSearch(
					new String[] { Global.ES_INDEX_GOODS }, query2.getBytes(),
					0, 10, "id", "asc");

			System.out.println(searchResult);
			System.out.println(searchResult.size());

			long count = searcher.getCount(
					new String[] { Global.ES_INDEX_GOODS }, query2.getBytes());

			System.out.println(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * complexSearch(String[] indexNames, HashMap<String, Object[]>
	 * mustSearchContentMap, HashMap<String, Object[]> shouldSearchContentMap,
	 * int from, int offset, String sortField, String sortType)
	 * 
	 * getComplexCount(String[] indexNames, HashMap<String, Object[]>
	 * mustSearchContentMap, HashMap<String, Object[]> shouldSearchContentMap)
	 */
	@Test
	public void testComplexSearch() {
		System.out.println(">>>>>>>>>>>>>testSimpleSearch");
		try {

			HashMap<String, Object[]> mustSearchContentMap = new HashMap<String, Object[]>();
			mustSearchContentMap.put("class", new Object[] { "电视" });

			HashMap<String, Object[]> shouldSearchContentMap = new HashMap<String, Object[]>();
			shouldSearchContentMap.put("name", new Object[] { "康佳", "窄边" });

			List<Map<String, Object>> searchResult = searcher.complexSearch(
					new String[] { Global.ES_INDEX_GOODS },
					mustSearchContentMap, shouldSearchContentMap, 0, 100, "id",
					"asc");

			System.out.println(searchResult);
			System.out.println(searchResult.size());

			long count = searcher.getComplexCount(
					new String[] { Global.ES_INDEX_GOODS },
					mustSearchContentMap, shouldSearchContentMap);

			System.out.println(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * group(String indexName, HashMap<String, Object[]> mustSearchContentMap,
	 * HashMap<String, Object[]> shouldSearchContentMap, String[] groupFields)
	 * 
	 * getComplexCount(String[] indexNames, HashMap<String, Object[]>
	 * mustSearchContentMap, HashMap<String, Object[]> shouldSearchContentMap)
	 * 
	 */
	@Test
	public void testGroup() {
		System.out.println(">>>>>>>>>>>>>testSimpleSearch");
		try {

			HashMap<String, Object[]> mustSearchContentMap = new HashMap<String, Object[]>();
			mustSearchContentMap.put("class", new Object[] { "电视" });

			HashMap<String, Object[]> shouldSearchContentMap = new HashMap<String, Object[]>();
			shouldSearchContentMap.put("name", new Object[] { "康佳", "窄边" });

			String[] groupFields = { "id" };

			Map<String, String> searchResult = searcher.group(
					Global.ES_INDEX_GOODS, mustSearchContentMap,
					shouldSearchContentMap, groupFields);

			System.out.println(searchResult);
			System.out.println(searchResult.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SearchSupport getSearcher() {
		return searcher;
	}

	public static void setSearcher(SearchSupport searcher) {
		SearchIndexTest.searcher = searcher;
	}

}
