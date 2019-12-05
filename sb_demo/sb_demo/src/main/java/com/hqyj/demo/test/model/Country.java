package com.hqyj.demo.test.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Country {
	private int countryId;
	private String countryName;
	private String localCountryName;
	private String countryCode;
	private String countryCode2;
	private String continent;
	private String region;
	private float surfaceArea;
	private int indepYear;
	private int population;
	private float lifeExpectancy;
	private float gnp;
	private String governmentForm;
	private String headOfState;
	private int capital;
	private String timeZone;
	private int languageId;
	private int currencyId;
	private List<City> cities;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dateModified;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dateCreated;
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLocalCountryName() {
		return localCountryName;
	}
	public void setLocalCountryName(String localCountryName) {
		this.localCountryName = localCountryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryCode2() {
		return countryCode2;
	}
	public void setCountryCode2(String countryCode2) {
		this.countryCode2 = countryCode2;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public float getSurfaceArea() {
		return surfaceArea;
	}
	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}
	public int getIndepYear() {
		return indepYear;
	}
	public void setIndepYear(int indepYear) {
		this.indepYear = indepYear;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public float getLifeExpectancy() {
		return lifeExpectancy;
	}
	public void setLifeExpectancy(float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}
	public float getGnp() {
		return gnp;
	}
	public void setGnp(float gnp) {
		this.gnp = gnp;
	}
	public String getGovernmentForm() {
		return governmentForm;
	}
	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}
	public String getHeadOfState() {
		return headOfState;
	}
	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}
	public int getCapital() {
		return capital;
	}
	public void setCapital(int capital) {
		this.capital = capital;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
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
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
//	@Override
//	public String toString() {
//		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", localCountryName="
//				+ localCountryName + ", countryCode=" + countryCode + ", countryCode2=" + countryCode2 + ", continent="
//				+ continent + ", region=" + region + ", surfaceArea=" + surfaceArea + ", indepYear=" + indepYear
//				+ ", population=" + population + ", lifeExpectancy=" + lifeExpectancy + ", gnp=" + gnp
//				+ ", governmentForm=" + governmentForm + ", headOfState=" + headOfState + ", capital=" + capital
//				+ ", timeZone=" + timeZone + ", languageId=" + languageId + ", currencyId=" + currencyId + ", cities="
//				+ cities + ", dateModified=" + dateModified + ", dateCreated=" + dateCreated + "]";
//	}
//	public Country(int countryId, String countryName, String localCountryName, String countryCode, String countryCode2,
//			String continent, String region, float surfaceArea, int indepYear, int population, float lifeExpectancy,
//			float gnp, String governmentForm, String headOfState, int capital, String timeZone, int languageId,
//			int currencyId, List<City> cities, Date dateModified, Date dateCreated) {
//		super();
//		this.countryId = countryId;
//		this.countryName = countryName;
//		this.localCountryName = localCountryName;
//		this.countryCode = countryCode;
//		this.countryCode2 = countryCode2;
//		this.continent = continent;
//		this.region = region;
//		this.surfaceArea = surfaceArea;
//		this.indepYear = indepYear;
//		this.population = population;
//		this.lifeExpectancy = lifeExpectancy;
//		this.gnp = gnp;
//		this.governmentForm = governmentForm;
//		this.headOfState = headOfState;
//		this.capital = capital;
//		this.timeZone = timeZone;
//		this.languageId = languageId;
//		this.currencyId = currencyId;
//		this.cities = cities;
//		this.dateModified = dateModified;
//		this.dateCreated = dateCreated;
//	}
//	public Country() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	

}
