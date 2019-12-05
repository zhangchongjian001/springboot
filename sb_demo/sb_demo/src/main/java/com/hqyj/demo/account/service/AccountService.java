package com.hqyj.demo.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.demo.account.model.Resource;
import com.hqyj.demo.account.model.Role;
import com.hqyj.demo.account.model.User;
import com.hqyj.demo.common.vo.Result;

public interface AccountService {

	List<User> queryAllIser();

	int insertUserByUser(User user);

	Result updateUserByUser(User user);

	Boolean deleteUserByUserId(int userId);
	
	List<Resource> queryAllResource();

	int insertResourceByResource(Resource resource);

	int updateResourceByResource(Resource resource);

	int deleteResourceByResourceId(int resourceId);
	
	List<Role> queryAllRole();

	int insertRoleByRole(Role role);

	int updateRoleByRole(Role role);

	boolean deleteRoleByRoleId(int roleId);

	boolean checkUserName(String userName);

	String registerCkeak(User user);

	Boolean checkUserNameAndPassword(User user);

	User getUserByName(String userName);

	List<Role> getRolesByUserId(int userId);

	List<Resource> getResourcesByRoleId(int roleId);

	List<Role> queryRoleByUserId(int userId);

	boolean editRoleByRole(Role role);

	List<Role> queryRoleByResourceId(int resourceId);

	boolean editResource(Resource resource);

	PageInfo<User> getUserByPage(int currentPage, int pagesize);

	User selectUserByName(String userNameString);
}
