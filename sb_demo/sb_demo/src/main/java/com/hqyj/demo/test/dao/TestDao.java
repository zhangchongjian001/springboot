package com.hqyj.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import com.hqyj.demo.test.model.City;
import com.hqyj.demo.test.model.Country;

@Repository
@Mapper
public interface TestDao {
	
	@Select("SELECT * FROM m_city where country_id=#{countryId}")
	List<City> queryCityByCountryId(int countryId);
	
	@Select("SELECT * FROM m_country where country_id=#{countryId}")
	List<Country> queryCountryByCountryId(int countryId);
}
