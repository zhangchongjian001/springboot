package com.hqyj.demo.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.demo.test.model.City;
import com.hqyj.demo.test.model.Country;

public interface TestService {

	List<City> queryCityByCountryId(int countryId);

	List<Country> queryCountryByCountryId(int countryId);

	List<Country> queryCountryAndCityByCountryId(int countryId);

	Country queryCountryAndCityByCountryName(String countryName);

	PageInfo<City> queryCityByPage(int currentPage, int pageSize);

	int deleteCityByCityId(int cityId);

	int insertCityByJson(City city);

	int updateCityByJson(City city);

}
