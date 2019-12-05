package com.hqyj.demo.account.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hqyj.demo.account.model.Resource;
import com.hqyj.demo.account.model.Role;
import com.hqyj.demo.account.model.User;
import com.hqyj.demo.account.service.AccountService;
import com.hqyj.demo.common.vo.Result;
import com.hqyj.demo.utils.MD5Util;

@Controller
@RequestMapping("/account")
public class accountcontroller {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("accountIndex")
	public String accountIndex(ModelMap modelMap) {
		
		return "account/dashboard";
	}
	
	@RequestMapping("/login")
	public String toLogin() {
		return "accountindex";
		
	}
	
	@RequestMapping("/dologin")
	@ResponseBody
	public Boolean checkLogin(@ModelAttribute User user) {
		
		return accountService.checkUserNameAndPassword(user);
		
	}
	
	@RequestMapping("dashboard")
	public String todashboard(ModelMap modelMap) {
		
		return "index";
	}
	
	
	@RequestMapping("/logout")
	public String Logout() {
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/account/login";
	}
	
	@RequestMapping("/register")
	public String userRegister() {
		
		return "accountindex";
	}
	
	@PostMapping(value = "/checkUserName",consumes = "application/x-www-form-urlencoded")
	@ResponseBody
	public boolean checkUserName(@ModelAttribute User user) {
		boolean flag=accountService.checkUserName(user.getUserName());
		return flag;
	}
	
	
	@RequestMapping("/registerCkeak")
	@ResponseBody
	public String registerCkeak(@ModelAttribute User user) {
		user.setCreateDate(new Date());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		return accountService.registerCkeak(user);
	}
	
	
	@RequestMapping("/userPage/{currentPage}")
	public String  userPage(ModelMap modelMap,@PathVariable int currentPage ){
//		List<User> list=accountService.queryAllIser();
		int pagesize=3;
		PageInfo<User> userpage=accountService.getUserByPage(currentPage,pagesize);
		int pages=userpage.getPages();
		int pageNum=userpage.getPageNum();
		List<User> list=userpage.getList();
		modelMap.addAttribute("pages", pages);
		modelMap.addAttribute("pageNum", pageNum);
		modelMap.addAttribute("userlist", list);
		modelMap.addAttribute("template", "/account/users");
		
		List<Role> list1=accountService.queryAllRole();
		modelMap.addAttribute(userpage);
//		modelMap.addAttribute("userlist", list);
		modelMap.addAttribute("list1", list1);
		return "index";	
	}
	
	@ResponseBody
	@PostMapping(value = "/insertuser",consumes = "application/x-www-form-urlencoded")
	public String addUser(@ModelAttribute User user) {
		int n=accountService.insertUserByUser(user);
		if (n>0) {
			return "插入成功";
		}else {
			return "插入失败";
		}

	}
	
	@ResponseBody
	@PutMapping(value = "/updateuser",consumes = "application/json")
	public Result updateUser(@RequestBody User user) {


		return accountService.updateUserByUser(user);
	}
	
	/**
	 * <p>
	 * 通过路径传过来的userId将用户数据删除，并且利用Ajax局部刷新技术将改用户信息从页面上删除
	 * </p>
	 * @author zcj
	 * @Date 2019年12月3日
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteUser/{userId}")
	public Boolean deleteUser(@PathVariable int userId) {
		return accountService.deleteUserByUserId(userId);
	}
	
	@ResponseBody
	@RequestMapping("/role/user/{userId}")
	public List<Role> queryRoleByUserId(@PathVariable int userId){
		
		return accountService.queryRoleByUserId(userId);	
	}
	
	
	@RequestMapping("/roles")
	public String queryAllRole(ModelMap modelMap){
		List<Role> list=accountService.queryAllRole();
		modelMap.addAttribute("roles", list);
		return "index";	
	}
	
	@ResponseBody
	@PostMapping(value = "/editrole",consumes = "application/json")
	public boolean addRole(@RequestBody Role role) {
		return accountService.editRoleByRole(role);
	}
	
	@ResponseBody
	@PutMapping(value = "/updaterole",consumes = "application/x-www-form-urlencoded")
	public String updateUser(@ModelAttribute Role role) {
		int n=accountService.updateRoleByRole(role);
		if (n>0) {
			return "修改成功";
		}else {
			return "修改失败";
		}

	}
	
	@ResponseBody
	@DeleteMapping("/deleterole/{roleId}")
	public boolean deleteRole(@PathVariable int roleId) {

		return accountService.deleteRoleByRoleId(roleId);
	}
	
	@RequestMapping("/resources")
	public String  queryAllResource(ModelMap modelMap){
		List<Resource> list=accountService.queryAllResource();
		modelMap.addAttribute("resources", list);
		List<Role> list1=accountService.queryAllRole();
		modelMap.addAttribute("roles", list1);
		return "index";	
	}
	
	@ResponseBody
	@RequestMapping("/roles/resource/{resourceId}")
	public List<Role> queryRolesByResourceId(@PathVariable int resourceId){
		
		return accountService.queryRoleByResourceId(resourceId);	
	}
	
	@ResponseBody
	@PostMapping(value = "/editresource",consumes = "application/json")
	public boolean editResource(@RequestBody Resource resource) {
		return accountService.editResource(resource);
	}
	
	
	@ResponseBody
	@PostMapping(value = "/insertresource",consumes = "application/x-www-form-urlencoded")
	public String addRole(@ModelAttribute Resource resource) {
		int n=accountService.insertResourceByResource(resource);
		if (n>0) {
			return "插入成功";
		}else {
			return "插入失败";
		}

	}
	
	@ResponseBody
	@PutMapping(value = "/updateresource",consumes = "application/x-www-form-urlencoded")
	public String updateResource(@ModelAttribute Resource resource) {
		int n=accountService.updateResourceByResource(resource);
		if (n>0) {
			return "修改成功";
		}else {
			return "修改失败";
		}

	}
	
	@ResponseBody
	@DeleteMapping("/deleteresource/{resourceId}")
	public String deleteResource(@PathVariable int resourceId) {
		int n=accountService.deleteResourceByResourceId(resourceId);
		if (n>0) {
			return "删除成功";
		}else {
			return "删除失败";
		}

	}
	
	@RequestMapping("/myInfo")
	public String selectMyInfo(ModelMap modelMap) {
		String userNameString=(String) SecurityUtils.getSubject().getSession().getAttribute("username");
		User user=accountService.selectUserByName(userNameString);
		modelMap.addAttribute("user", user);
		
		return "index";
		
	}

}
