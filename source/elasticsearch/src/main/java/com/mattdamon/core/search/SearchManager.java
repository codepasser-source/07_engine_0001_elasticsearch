package com.mattdamon.core.search;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.mattdamon.core.domain.SysConfig;
import com.mattdamon.core.domain.SysConfig.ESNode;

public class SearchManager {

	private static Object lock = new Object();

	private static TransportClient client;

	/**
	 * @return
	 */
	public static TransportClient getClient() {
		System.out.println("SearchManager >>>>>>> getClient");
		SysConfig sysconfig = SysConfig.getInstance();

		if (client == null) {
			// 同步锁
			synchronized (lock) {
				Map<String, String> settingMap = new HashMap<String, String>();
				// 是否只作为客户端，即不存储索引数据，默认值为false
				// settingMap.put("node.client", "false");
				// 是否持有索引数据，默认值为true
				// settingMap.put("node.data", "true");
				// 是否为本地节点，本地节点是指在JVM级别中的同级
				// settingMap.put("node.local", "true");
				settingMap.put("cluster.name", "elasticsearch");

				Settings settings = ImmutableSettings.settingsBuilder()
						.put(settingMap).build();
				client = new TransportClient(settings);
				// 添加节点
				for (ESNode nodeObject : sysconfig.getEsNodes()) {
					client.addTransportAddress(new InetSocketTransportAddress(
							nodeObject.getAddress(), nodeObject.getPort()));
				}
			}
		}
		return client;
	}

	public static void closeClient() {
		if (client != null) {
			synchronized (lock) {
				System.out.println("SearchManager >>>>>>> closeClient");
				client.close();
				client = null;
			}
		}
	}

	public static void checkNode() {
		if (client != null) {
			synchronized (lock) {
				ImmutableList<DiscoveryNode> nodes = client.connectedNodes();
				System.out.println("elastic search client connected nodes : "
						+ nodes.size());
			}
		}
	}

}