package com.mattdamon.elasticsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mattdamon.common.Global;
import com.mattdamon.core.search.SearchManager;
import com.mattdamon.core.search.SearchSupport;

/**
 * Unit test for simple App.
 */
public class CreateIndexTest {

	private static SearchSupport searcher;

	private static List<HashMap<String, Object[]>> goods = new ArrayList<HashMap<String, Object[]>>();

	private static List<HashMap<String, Object[]>> goodsOLD = new ArrayList<HashMap<String, Object[]>>();

	private static List<HashMap<String, Object[]>> stories = new ArrayList<HashMap<String, Object[]>>();

	private static List<HashMap<String, Object[]>> storiesOLD = new ArrayList<HashMap<String, Object[]>>();

	@BeforeClass
	public static void beforeClass() {
		System.out.println(">>>>>>>>>>>>>beforeClass");

		// 商品
		for (int i = 1; i <= 10; i++) {
			HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
			contentMap.put("id", new Object[] { Global.ES_INDEX_GOODS + i });
			contentMap.put("name", new Object[] { "康佳窄边高清液晶电视" + i });
			contentMap.put("brand", new Object[] { "康佳" });
			contentMap.put("price", new Object[] { 100.01 });
			contentMap.put("class", new Object[] { "家电", "电视" });
			contentMap.put("type", new Object[] { "电视" });
			contentMap.put("url", new Object[] { "http://www.neusoft-sy.com" });
			goods.add(contentMap);
		}
		for (int i = 11; i <= 20; i++) {
			HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
			contentMap.put("id", new Object[] { Global.ES_INDEX_GOODS + i });
			contentMap.put("name", new Object[] { "创维八核网络平板液晶电视" + i });
			contentMap.put("brand", new Object[] { "创维" });
			contentMap.put("price", new Object[] { 100.01 });
			contentMap.put("class", new Object[] { "家电", "电视" });
			contentMap.put("type", new Object[] { "电视" });
			contentMap.put("url", new Object[] { "http://www.neusoft-sy.com" });
			goods.add(contentMap);
		}

		for (int i = 1; i <= 20; i++) {
			HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
			contentMap.put("id", new Object[] { Global.ES_INDEX_GOODS + i });
			goodsOLD.add(contentMap);
		}

		// 店铺
		for (int i = 21; i <= 30; i++) {
			HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
			contentMap.put("id", new Object[] { Global.ES_INDEX_STORE + i });
			contentMap.put("name", new Object[] { "康佳旗舰店" + i });
			contentMap.put("brand", new Object[] { "康佳" });
			contentMap.put("class", new Object[] { "家电", "电视" });
			contentMap.put("url", new Object[] { "http://www.neusoft-sy.com" });
			stories.add(contentMap);
		}
		for (int i = 31; i <= 40; i++) {
			HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
			contentMap.put("id", new Object[] { Global.ES_INDEX_STORE + i });
			contentMap.put("name", new Object[] { "创维旗舰店" + i });
			contentMap.put("brand", new Object[] { "创维" });
			contentMap.put("class", new Object[] { "家电", "电视" });
			contentMap.put("url", new Object[] { "http://www.neusoft-sy.com" });
			stories.add(contentMap);
		}

		for (int i = 21; i <= 40; i++) {
			HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
			contentMap.put("id", new Object[] { Global.ES_INDEX_STORE + i });
			storiesOLD.add(contentMap);
		}
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

	@Test
	public void testBulkInsertData() {

		System.out.println(">>>>>>>>>>>>>testBulkInsertData");
		try {
			for (HashMap<String, Object[]> contentMap : goods) {

				boolean result = searcher.bulkInsertData(Global.ES_INDEX_GOODS,
						contentMap);
				System.out.println(result);

			}

			for (HashMap<String, Object[]> contentMap : stories) {

				boolean result = searcher.bulkInsertData(Global.ES_INDEX_STORE,
						contentMap);
				System.out.println(result);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBulkDeleteData() {

		System.out.println(">>>>>>>>>>>>>testBulkDeleteData");
		try {
			for (HashMap<String, Object[]> contentMap : goodsOLD) {
				boolean result = searcher.bulkDeleteData(Global.ES_INDEX_GOODS,
						contentMap);
				System.out.println(result);
			}
			for (HashMap<String, Object[]> contentMap : storiesOLD) {
				boolean result = searcher.bulkDeleteData(Global.ES_INDEX_STORE,
						contentMap);
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBulkUpdateData() {

		System.out.println(">>>>>>>>>>>>>testBulkUpdateData");
		try {
			List<HashMap<String, Object[]>> goodsNEW = new ArrayList<HashMap<String, Object[]>>();

			for (int i = 1; i <= 20; i++) {
				HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
				contentMap
						.put("id", new Object[] { Global.ES_INDEX_GOODS + i });
				contentMap.put("name", new Object[] { "康佳窄边高清液晶电视" + i + i });
				contentMap.put("brand", new Object[] { "康佳" });
				contentMap.put("price", new Object[] { 100.01 + i });
				contentMap.put("class", new Object[] { "家电", "电视" });
				contentMap.put("type", new Object[] { "电视" });
				goodsNEW.add(contentMap);
			}

			int i = 0;
			for (HashMap<String, Object[]> oldContentMap : goodsOLD) {

				HashMap<String, Object[]> newContentMap = goodsNEW.get(i);

				boolean result = searcher.bulkUpdateData(Global.ES_INDEX_GOODS,
						oldContentMap, newContentMap);
				System.out.println(result);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAutoBulkUpdateData() {
		System.out.println(">>>>>>>>>>>>>testAutoBulkUpdateData");
		try {
			List<HashMap<String, Object[]>> goodsNEW = new ArrayList<HashMap<String, Object[]>>();

			for (int i = 1; i <= 20; i++) {
				HashMap<String, Object[]> contentMap = new HashMap<String, Object[]>();
				contentMap
						.put("id", new Object[] { Global.ES_INDEX_GOODS + i });
				contentMap.put("name", new Object[] { "康佳窄边高清液晶电视" + i });
				contentMap.put("brand", new Object[] { "康佳" });
				contentMap.put("price", new Object[] { 100.01 });
				contentMap.put("class", new Object[] { "家电", "电视" });
				contentMap.put("type", new Object[] { "电视" });
				contentMap.put("url",
						new Object[] { "http://www.neusoft-sy.com/" + i });
				goodsNEW.add(contentMap);
			}

			int i = 0;
			for (HashMap<String, Object[]> oldContentMap : goodsOLD) {

				HashMap<String, Object[]> newContentMap = goodsNEW.get(i);

				boolean result = searcher.autoBulkUpdateData(
						Global.ES_INDEX_GOODS, oldContentMap, newContentMap);
				System.out.println(result);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SearchSupport getSearcher() {
		return searcher;
	}

	public static void setSearcher(SearchSupport searcher) {
		CreateIndexTest.searcher = searcher;
	}

}
