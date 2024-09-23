package com.carshop.model;

public abstract class Vehicle {

	
	private int id;
	
	private String manufacturer;
	
	private String color;

	private String model;
	
	private String year;
	
	private double price;
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

	@Override
	public String toString() {
		return "[id=" + id + ", name=" +manufacturer  + ", color=" + color + ", model=" + model + ", year=" + year
				+ ", price=" + price + "";
	}
	
	
	
	
	
}
