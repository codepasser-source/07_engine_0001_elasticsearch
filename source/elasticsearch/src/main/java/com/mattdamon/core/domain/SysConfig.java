package com.mattdamon.core.domain;

import java.util.ArrayList;
import java.util.List;

public class SysConfig {

	private static SysConfig config;

	private List<ESNode> esNodes = new ArrayList<SysConfig.ESNode>();

	public static SysConfig getInstance() {

		if (config == null) {
			config = new SysConfig();
		}

		config.addEsNode(new ESNode("192.168.24.100", 9300));
		config.addEsNode(new ESNode("192.168.24.101", 9300));
		config.addEsNode(new ESNode("192.168.24.102", 9300));

		return config;

	}

	public List<ESNode> getEsNodes() {
		return esNodes;
	}

	public void setEsNodes(List<ESNode> esNodes) {
		if (esNodes == null) {
			return;
		}
		this.esNodes = esNodes;
	}

	public void addEsNode(ESNode node) {

		if (node == null) {
			return;
		}
		this.esNodes.add(node);

	}

	public static class ESNode {

		private String address;

		private int port;

		public ESNode(String address, int port) {
			this.address = address;
			this.port = port;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

	}
}
