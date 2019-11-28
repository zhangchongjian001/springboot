package com.hqyj.demo.test.service;

import java.util.List;

import com.hqyj.demo.test.model.City;
import com.hqyj.demo.test.model.Country;

public interface TestService {

	List<City> queryCityByCountryId(int countryId);

	List<Country> queryCountryByCountryId(int countryId);

}
