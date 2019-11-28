package com.hqyj.demo.test.controller;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hqyj.demo.test.model.City;
import com.hqyj.demo.test.model.Country;
import com.hqyj.demo.test.service.TestService;


@Controller
@RequestMapping("/test")
public class TestController {
	
//	注入service业务接口
	@Autowired
	private TestService testService;
	
	
	@Value("${server.port}")
	private String port;
	@Value("${com.hqyj.name}")
	private String name;
	@Value("${com.hqyj.age}")
	private String age;
	@Value("${com.test.description}")
	private String description;
	@Value("${com.test.random}")
	private String random;
	
	/**
	 * <p>
	 * 通过国家id属性查找所有城市的集合
	 * </p>
	 * @author zcj
	 * @Date 2019年11月27日
	 * @param countryId
	 * @return list<City>
	 */
	@ResponseBody
	@RequestMapping("/queryCity/{countryId}")
	private List<City> queryCityByCountryId(@PathVariable("countryId") int countryId){
		
		return testService.queryCityByCountryId(countryId);	
	}
	
	/**
	 * <p>
	 * 通过国家id查询国家
	 * </p>
	 * @author zcj
	 * @Date 2019年11月27日
	 * @param countryId
	 * @return list<Country>
	 */
	@ResponseBody
	@RequestMapping("/queryCountry/{countryId}")
	private List<Country> queryCountryByCountryId(@PathVariable("countryId") int countryId){
		
		return testService.queryCountryByCountryId(countryId);
	}
	
	
//	@ResponseBody
//	@RequestMapping("/queryCountryAndCity/{countryId}")
//	private List<Country> queryCountryAndCityByCountryId(@PathVariable("countryId") int countryId){
//		
//		return testService.queryCountryAndCityByCountryId(countryId);	
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/applicationInfo")
	private StringBuilder Info() {
		StringBuilder str=new StringBuilder();
		str.append("----port------").append(port).append("<br>------name----").append(name)
		.append("<br>----age------").append(age).append("<br>-----description-----")
		.append(description).append("<br>------random----").append(random).append("----------------");
		return str;	
	}
	
	
	@ResponseBody
	@RequestMapping("/page")
	public String  welcomPage() {
		return "welcome to this page,and you will see this word:sb";
		
	}
	
	private final static Logger LOGGER=LoggerFactory.getLogger(TestController.class); 
	
	/**
	 * <p>
	 * 测试日志文件配置
	 * </p>
	 * @author zcj
	 * @Date 2019年11月27日
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logTest")
	public String testLog() {
		LOGGER.trace("this is trace");
		LOGGER.debug("this is debug");
		LOGGER.info("this is info");
		LOGGER.warn("this is warn");
		LOGGER.error("this is error");
		return "this is logput";
	}
}
