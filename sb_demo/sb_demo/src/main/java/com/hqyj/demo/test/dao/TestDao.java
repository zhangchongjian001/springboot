package com.hqyj.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.hqyj.demo.test.model.City;
import com.hqyj.demo.test.model.Country;

@Repository
@Mapper
public interface TestDao {
	
	@Select("SELECT * FROM m_city where country_id=#{countryId}")
	List<City> queryCityByCountryId(int countryId);
	
	@Select("SELECT * FROM m_country where country_id=#{countryId}")
	List<Country> queryCountryByCountryId(int countryId);

	/**
	 * <p>
	 * 将传入的参数CountryId分别映射到两个方法上，将两个方法返回的结果集包含在同一个结果集中，达到联合查询的目的
	 * </p>
	 * @author zcj
	 * @Date 2019年11月28日
	 * @param countryId
	 * @return
	 */
	@Select("SELECT * FROM m_country where country_id=#{countryId}")
	@Results(id = "countryResult",value= {
			@Result(column = "country_id",property = "countryId"),
			@Result(column = "country_id",property = "cities",
			javaType = List.class,
			many=@Many(select = "com.hqyj.demo.test.dao.TestDao.queryCityByCountryId"))
	})
	List<Country> queryCountryAndCityByCountryId(int countryId);

	
	@Select("SELECT * FROM m_country where country_name=#{countryName}")
	@ResultMap(value="countryResult")
	Country queryCountryAndCityByCountryName(String countryName);

	@Select("SELECT * FROM m_city")
	List<City> queryCityByPage();

	@Delete("delete from m_city where city_id=#{cityId}")
	int deleteCityByCityId(int cityId);

	@Insert("insert into m_city(city_name,local_city_name,country_id,date_created) values(#{cityName},#{localCityName},#{countryId},#{dateCreated})")
	int insertCityByJson(City city);

	@Update("update m_city set city_name=#{cityName},local_city_name=#{localCityName},"
			+ "country_id=#{countryId},date_created=#{dateCreated} where city_id=#{cityId}")
	int updateCityByJson(City city);



}
	


	

