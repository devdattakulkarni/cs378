package com.restfully.shop.domain;

public class Student {
	
	String name;
	String city;
	int uteid;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setUteid(int uteid) {
		this.uteid = uteid;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public int getUteid() {
		return this.uteid;
	}
}
