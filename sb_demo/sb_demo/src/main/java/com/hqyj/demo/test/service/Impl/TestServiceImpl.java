package com.hqyj.demo.test.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hqyj.demo.test.dao.TestDao;
import com.hqyj.demo.test.model.City;
import com.hqyj.demo.test.model.Country;
import com.hqyj.demo.test.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDao testDao;

	@Override
	public List<City> queryCityByCountryId(int countryId) {
		
		return Optional.ofNullable(testDao.queryCityByCountryId(countryId)).orElse(Collections.emptyList());
	}

	@Override
	public List<Country> queryCountryByCountryId(int countryId) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(testDao.queryCountryByCountryId(countryId)).orElse(Collections.emptyList());
	}

//	@Override
//	public List<Country> queryCountryAndCityByCountryId(int countryId) {
//		// TODO Auto-generated method stub
//		return testDao.queryCountryAndCityByCountryId(countryId);
//	}
}
