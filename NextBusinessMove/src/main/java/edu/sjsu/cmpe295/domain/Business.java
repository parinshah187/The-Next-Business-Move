package edu.sjsu.cmpe295.domain;

public class Business {
	 private String business_id;
	 private double predicted_rating;
	 private String full_address;
	 private String city;
	 private String name;
	 private String state;
	 private double longitude;
	 private double latitutde;
	 
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public double getPredicted_rating() {
		return predicted_rating;
	}
	public void setPredicted_rating(double predicted_rating) {
		this.predicted_rating = predicted_rating;
	}
	public String getFull_address() {
		return full_address;
	}
	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitutde() {
		return latitutde;
	}
	public void setLatitutde(double latitutde) {
		this.latitutde = latitutde;
	}
	 
}
