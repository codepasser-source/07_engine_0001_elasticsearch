package com.mattdamon.model;

import java.io.Serializable;

public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618284412358523683L;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
