package com.mattdamon.model;

public class Goods extends BaseModel {

	private static final long serialVersionUID = -4201138094166571794L;

	private String name;
	private String broad;

	private String type;
	private String gclass;

	private String specifications;
	private String characteristic;

	private float price;
	private long stock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBroad() {
		return broad;
	}

	public void setBroad(String broad) {
		this.broad = broad;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGclass() {
		return gclass;
	}

	public void setGclass(String gclass) {
		this.gclass = gclass;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

}
