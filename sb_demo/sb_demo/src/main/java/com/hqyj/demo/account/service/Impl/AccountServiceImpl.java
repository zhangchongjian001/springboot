package com.hqyj.demo.account.service.Impl;

import java.util.List;

import javax.websocket.Session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.demo.account.dao.AccountDao;
import com.hqyj.demo.account.model.Resource;
import com.hqyj.demo.account.model.Role;
import com.hqyj.demo.account.model.User;
import com.hqyj.demo.account.service.AccountService;
import com.hqyj.demo.common.vo.Result;
import com.hqyj.demo.test.model.City;
import com.hqyj.demo.utils.MD5Util;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public List<User> queryAllIser() {
		// TODO Auto-generated method stub
		return accountDao.queryAllUser();
	}

	@Override
	public int insertUserByUser(User user) {
		// TODO Auto-generated method stub
		return accountDao.insertUserByUser(user);
	}

	@Override
	public Result updateUserByUser(User user) {
		// TODO Auto-generated method stub
		if (user==null) {
			return new Result(500,"未选中角色，请选择");
					
		}  
		accountDao.deleteUserRoleByUserId(user.getUserId());
		if(user.getRoles()!=null){		
			for (Role role : user.getRoles()) {
				accountDao.addUserRole(user.getUserId(),role.getRoleId());	
			}
		}
		return  new Result(200, "success", user);
	}

	@Override
	public Boolean deleteUserByUserId(int userId) {
		// TODO Auto-generated method stub
		int n =accountDao.deleteUserByUserId(userId);
		Boolean flag=true;
		if (n>0) {
			flag=true;
		}else {
			flag=false;
		}
		return flag;
	}
	
	@Override
	public List<Role> queryAllRole() {
		// TODO Auto-generated method stub
		return accountDao.queryAllRole();
	}

	@Override
	public int insertRoleByRole(Role role) {
		// TODO Auto-generated method stub
		return accountDao.insertRoleByRole(role);
	}
	@Override
	public boolean editRoleByRole(Role role) {
		// TODO Auto-generated method stub
		boolean flag=false;
		if (role.getRoleId()==0) {
			int n=accountDao.insertRoleByRole(role);
			if (n>0) {
				flag=true;
			}
		}else {
			int n=accountDao.updateRoleByRole(role);
			if (n>0) {
				flag=true;
		}
		
		
	}
		return flag;
	}

	@Override
	public int updateRoleByRole(Role role) {
		// TODO Auto-generated method stub
		return accountDao.updateRoleByRole(role);
	}

	@Override
	public boolean deleteRoleByRoleId(int userId) {
		// TODO Auto-generated method stub
		return accountDao.deleteRoleByRoleId(userId)>0;
	}
	
	@Override
	public List<Resource> queryAllResource() {
		// TODO Auto-generated method stub
		return accountDao.queryAllResource();
	}

	@Override
	public int insertResourceByResource(Resource resource) {
		// TODO Auto-generated method stub
		return accountDao.insertResourceByResource(resource);
	}

	@Override
	public int updateResourceByResource(Resource resource) {
		// TODO Auto-generated method stub
		return accountDao.updateResourceByResource(resource);
	}

	@Override
	public int deleteResourceByResourceId(int resourceId) {
		// TODO Auto-generated method stub
		return accountDao.deleteResourceByResourceId(resourceId) ;
	}

	@Override
	public boolean checkUserName(String userName){
		// TODO Auto-generated method stub

		return accountDao.checkUserName(userName)>0;
	}

	@Override
	public String registerCkeak(User user) {
		// TODO Auto-generated method stub
		int n=accountDao.insertUserByUser(user);
		String str=null;
		if (n>0) {
			str="注册成功，请返回登录页面登录";
		} else {
			str="注册失败，请重新注册";
		}
		return str;
	}

	@Override
	public Boolean checkUserNameAndPassword(User user) {
		// TODO Auto-generated method stub
		Boolean flag=true;
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken usernamePasswordToken = 
					new UsernamePasswordToken(user.getUserName(), MD5Util.getMD5(user.getPassword()));
			usernamePasswordToken.setRememberMe(user.getRememberMe());
			subject.login(usernamePasswordToken);
			subject.checkRoles();
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
			return flag;
		}
		
		return flag;
	}

	@Override
	public User getUserByName(String userName) {
		// TODO Auto-generated method stub
		return accountDao.getUserByName(userName);
	}

	@Override
	public List<Role> getRolesByUserId(int userId) {
		// TODO Auto-generated method stub
		return accountDao.getRolesByUserId(userId);
	}

	@Override
	public List<Resource> getResourcesByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return accountDao.getResourcesByRoleId(roleId);
	}

	@Override
	public List<Role> queryRoleByUserId(int userId) {
		// TODO Auto-generated method stub
		
		return accountDao.getRolesByUserId(userId);
	}

	@Override
	public List<Role> queryRoleByResourceId(int resourceId) {
		// TODO Auto-generated method stub
		return accountDao.queryRoleByResourceId(resourceId);
	}

	@Override
	public boolean editResource(Resource resource) {
		boolean flag=false;
		if (resource.getResourceId()==0) {
			int n=accountDao.insertResourceByResource(resource);
			if (n>0) {
				flag=true;
			}
		}else {
			int n=accountDao.updateResourceByResource(resource);
			if (n>0) {
				flag=true;
		}
		// 添加 roleResource
		accountDao.deletRoleResourceByResourceId(resource.getResourceId());
		if (resource.getRoles() != null && !resource.getRoles().isEmpty()) {
			for (Role role : resource.getRoles()) {
				accountDao.addRoleResource(role.getRoleId(), resource.getResourceId());
			}
		}
		
	}
		return flag;

	}

	@Override
	public PageInfo<User> getUserByPage(int currentPage, int pagesize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(currentPage,pagesize);
		List<User> users=accountDao.getUserByPage();
		return new PageInfo<User>(users);
	}

	@Override
	public User selectUserByName(String userNameString) {
		// TODO Auto-generated method stub
		return accountDao.getUserByName(userNameString);
	}
}
