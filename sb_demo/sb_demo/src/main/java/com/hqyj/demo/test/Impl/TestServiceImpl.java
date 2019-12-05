package com.hqyj.demo.test.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

	@Override
	public List<Country> queryCountryAndCityByCountryId(int countryId) {

		return testDao.queryCountryAndCityByCountryId(countryId);
	}

	@Override
	public Country queryCountryAndCityByCountryName(String countryName) {
		Country list=testDao.queryCountryAndCityByCountryName(countryName);
		return testDao.queryCountryAndCityByCountryName(countryName);
	}

	/**
	 *分页查询
	 */
	@Override
	public PageInfo<City> queryCityByPage(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage,pageSize);
		List<City> cities=testDao.queryCityByPage();
		return new PageInfo<>(cities);
	}

	@Override
	public int deleteCityByCityId(int cityId) {
		// TODO Auto-generated method stub
		return testDao.deleteCityByCityId(cityId);
	}

	@Override
	public int insertCityByJson(City city) {
		
		return testDao.insertCityByJson(city);
	}

	@Override
	public int updateCityByJson(City city) {
		// TODO Auto-generated method stub
		return testDao.updateCityByJson(city);
	}
}
