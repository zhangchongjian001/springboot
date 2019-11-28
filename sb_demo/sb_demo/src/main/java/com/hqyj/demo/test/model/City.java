package com.hqyj.demo.test.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class City {
	private int cityId;
	private String cityName;
	private String localCityName;
	private int countryId;
	private String district;
	private int population;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dateModified;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dateCreated;
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLocalCityName() {
		return localCityName;
	}
	public void setLocalCityName(String localCityName) {
		this.localCityName = localCityName;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", localCityName=" + localCityName + ", countryId="
				+ countryId + ", district=" + district + ", population=" + population + ", dateModified=" + dateModified
				+ ", dateCreated=" + dateCreated + "]";
	}
	public City(int cityId, String cityName, String localCityName, int countryId, String district, int population,
			Date dateModified, Date dateCreated) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.localCityName = localCityName;
		this.countryId = countryId;
		this.district = district;
		this.population = population;
		this.dateModified = dateModified;
		this.dateCreated = dateCreated;
	}
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
