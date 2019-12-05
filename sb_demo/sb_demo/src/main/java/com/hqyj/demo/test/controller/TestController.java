package com.hqyj.demo.test.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;
import com.hqyj.demo.test.model.City;
import com.hqyj.demo.test.model.Country;
import com.hqyj.demo.test.service.TestService;


@Controller
@RequestMapping("/test")
public class TestController {
	
//	注入service业务接口
	@Autowired
	private TestService testService;
	
	
	/**
	 * <p>
	 * 上传单个文件到指定文件夹，如果指定的文件夹不存在，返回上传失败的信息
	 * </p>
	 * @author zcj
	 * @Date 2019年11月30日
	 * @param file
	 * @param redirectAttributes
	 * @return /test/index
	 */
	@RequestMapping(value = "/upload",method = RequestMethod.POST,consumes = "multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "please select a file");
			return "redirect:/test/index";
		}

		try {
			String filename=file.getOriginalFilename();
			String destfilename="F:/uploadFile"+File.separator+filename;
			File destFile=new File(destfilename);
			file.transferTo(destFile);
			
			redirectAttributes.addFlashAttribute("message","upload file successfully");
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "upload file failed");
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/test/index";	
	}
	
	
	/**
	 * 上传多个文件
	 */
	@RequestMapping(value="/uploadBatchFile", method=RequestMethod.POST, consumes="multipart/form-data")
	public String uploadBatchFile(@RequestParam MultipartFile[] files, 
			RedirectAttributes redirectAttributes) {
		Boolean isEmpty=true;
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue;
			}

			try {
				String fileName=file.getOriginalFilename();
				String destFileNameString="F:/uploadFile"+File.separator+fileName;
				File destFile=new File(destFileNameString);
				file.transferTo(destFile);
				isEmpty=false;
			} catch (IllegalStateException | IOException e) {
				LOGGER.debug(e.getMessage());
				redirectAttributes.addFlashAttribute("message", "上传文件失败");
				e.printStackTrace();
				return "redirect:/test/index";
			}
		}
		if (isEmpty) {
			redirectAttributes.addFlashAttribute("message", "请选择需要上传的文件");
		}else {
			redirectAttributes.addFlashAttribute("message", "上传文件成功");
		}
		
		return "redirect:/test/index";
	}
	
	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
		try {
			// 使用resource来包装下载文件
			Resource resource = new UrlResource(
					Paths.get("F:/uploadFile" + File.separator + fileName).toUri());
			if (resource.exists() && resource.isReadable()) {
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + 
								resource.getFilename() + "\"")
						.body(resource);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
		}
		
		return null;
	}
	
	
	
	
	
	
	
	/**
	 * 返回test/index页面
	 * return index ---- return classpath:/templates/index.html
	 * template:test/index ---- classpath:/templates/test/index.html
	 */
	@RequestMapping("/index")
	public String testPage(ModelMap modelMap) {
		int countryId = 522;
		List<City> cities = testService.queryCityByCountryId(countryId);
		cities = cities.stream().limit(10).collect(Collectors.toList());
		
		modelMap.addAttribute("thymeleafTitle", "This is thymeleaf test page.");
		modelMap.addAttribute("changeType", "checkbox");
		modelMap.addAttribute("checked", false);
		modelMap.addAttribute("currentNumber", 88);
		modelMap.addAttribute("baiduUrl", "/test/applicationInfo");
		
		modelMap.addAttribute("shopLogo", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575038241972&di=29a4b32b74657aada18d8dc489401d01&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F40%2Fw480h360%2F20190415%2F3cGX-hvsckth1897105.jpg");
		List<Country> queryCountryByCountryId = testService.queryCountryByCountryId(countryId);
		modelMap.addAttribute("country", queryCountryByCountryId.get(0));
		modelMap.addAttribute("city", cities.get(0));
		modelMap.addAttribute("cities", cities);
//		modelMap.addAttribute("updateCityUri", "/test/city");
		modelMap.addAttribute("template", "test/index");
		return "index";
	}
	
	
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
	
	@ResponseBody
	@RequestMapping("/testFilter")
	private String uuu(@RequestParam String countryName,HttpServletRequest req
			){
		String valueString=req.getParameter("countryName");
		return countryName+"==============="+valueString;	
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
	
	
	/**
	 * <p>
	 * 通过contryId查询出国家以及该国家所包含的城市
	 * </p>
	 * @author zcj
	 * @Date 2019年11月28日
	 * @param countryId
	 * @return list<Country>
	 */
	@ResponseBody
	@RequestMapping("/queryCountryAndCity/{countryId}")
	private List<Country> queryCountryAndCityByCountryId(@PathVariable("countryId") int countryId){
		
		return testService.queryCountryAndCityByCountryId(countryId);	
	}
	
	@ResponseBody
	@RequestMapping("/queryCountryAndCityByCountryName")
	private Country queryCountryAndCityByCountryName(@RequestParam("countryName") String countryName){
		
		return testService.queryCountryAndCityByCountryName(countryName);	
	}
	
	
	@RequestMapping("/cityPage")
	@ResponseBody
	private PageInfo<City> queryCityByPage(@RequestParam int currentPage,@RequestParam int pageSize){
		
		return testService.queryCityByPage(currentPage,pageSize);	
	}
	
	@ResponseBody
	@DeleteMapping("/deleteCityByCityId/{cityId}")
	private String deleteCityByCityId(@PathVariable int cityId){
		int n=testService.deleteCityByCityId(cityId);
		String str=null;
		if (n>0) {
			str="删除成功";
		} else {
			str="删除失败";
		}
		return str;	
	}
	
	
	@ResponseBody
	@PostMapping(value = "/insert",consumes = "application/json")
	private String insertCityByJson(@RequestBody City city){
		int n=testService.insertCityByJson(city);
		String str=null;
		if (n>0) {
			str="插入成功";
		} else {
			str="插入失败";
		}
		return str;	
	}
	
	@ResponseBody
	@PostMapping(value = "/insert2",consumes = "application/x-www-form-urlencoded")
	private String insert2(@ModelAttribute City city){
		int n=testService.insertCityByJson(city);
		String str=null;
		if (n>0) {
			str="插入成功";
		} else {
			str="插入失败";
		}
		return str;	
	}
	
	@ResponseBody
	@PutMapping(value = "/update1",consumes = "application/json")
	private String update1(@RequestBody City city){
		int n=testService.updateCityByJson(city);
		String str=null;
		if (n>0) {
			str="修改成功";
		} else {
			str="修改失败";
		}
		return str;	
	}
	
	
	@ResponseBody
	@PutMapping(value = "/update2",consumes = "application/x-www-form-urlencoded")
	private String update2(@ModelAttribute City city){
		int n=testService.updateCityByJson(city);
		String str=null;
		if (n>0) {
			str="修改成功";
		} else {
			str="修改失败";
		}
		return str;	
	}
	
	
	
	
	
	
	
	
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
