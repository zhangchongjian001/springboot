package com.hqyj.demo.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.hqyj.demo.account.model.Resource;
import com.hqyj.demo.account.model.Role;
import com.hqyj.demo.account.model.User;
import com.hqyj.demo.common.vo.Result;

@Repository
@Mapper
public interface AccountDao {
	
	@Select("select * from resource")
	List<Resource> queryAllResource();

	@Insert("insert into resource(resource_id,resource_name,resource_uri,permission) "
			+ "values(#{resourceId},#{resourceName},#{resourceUri},#{permission})")
	int insertResourceByResource(Resource resource);

	@Update("update resource set resource_name=#{resourceName},resource_uri=#{resourceUri},permission=#{permission} where resource_id=#{resourceId}")
	int updateResourceByResource(Resource resource);

	@Delete("delete from resource where resource_id=#{resourceId}")
	int deleteResourceByResourceId(int resourceId);
	
	@Select("select * from user")
	List<User> queryAllUser();

	@Insert("insert into user(user_id,user_name,password,create_date) "
			+ "values(#{userId},#{userName},#{password},#{createDate})")
	int insertUserByUser(User user);

	@Update("update user set user_name=#{userName},password=#{password} where user_id=#{userId}")
	Result updateUserByUser(User user);

	@Delete("delete from user where user_id=#{userId}")
	int deleteUserByUserId(int userId);
	
	@Select("select * from role")
	List<Role> queryAllRole();
	
	@Insert("insert into role(role_id,role_name) values(#{roleId},#{roleName})")
	int insertRoleByRole(Role role);
	
	@Update("update role set role_name=#{roleName} where role_id=#{roleId}")
	int updateRoleByRole(Role role);
	
	@Delete("delete from role where role_id=#{roleId}")
	int deleteRoleByRoleId(int roleId);

	@Select("select count(*) from user where user_name=#{userName}")
	int checkUserName(String userName);

	@Select("select count(*) from user where user_name=#{userName} and password=#{password}")
	int selectUserByUserNameAndPassword(User user);

	@Select("select * from user where user_name=#{userName}")
	User getUserByName(String userName);

	@Select("SELECT\r\n" + 
			"role.role_id,\r\n" + 
			"role.role_name\r\n" + 
			"FROM\r\n" + 
			"user_role\r\n" + 
			"INNER JOIN `user` ON `user`.user_id = user_role.user_id\r\n" + 
			"INNER JOIN role ON user_role.role_id = role.role_id\r\n" + 
			"where `user`.user_id=#{userId}")
	List<Role> getRolesByUserId(int userId);
	
	@Select("SELECT\r\n" + 
			"resource.resource_id,\r\n" + 
			"resource.resource_uri,\r\n" + 
			"resource.resource_name,\r\n" + 
			"resource.permission\r\n" + 
			"FROM\r\n" + 
			"role\r\n" + 
			"INNER JOIN role_resource ON role.role_id = role_resource.role_id\r\n" + 
			"INNER JOIN resource ON role_resource.resource_id = resource.resource_id\r\n" + 
			"where `role`.role_id=#{roleId}")
	List<Resource> getResourcesByRoleId(int roleId);

	@Delete("delete from user_role where user_id=#{userId}")
	void deleteUserRoleByUserId(int userId);

	@Insert("insert into user_role(user_id,role_id) values(#{userId},#{roleId})")
	void addUserRole(int userId, int roleId);

	@Select("SELECT\r\n" + 
			"role.role_id,\r\n" + 
			"role.role_name\r\n" + 
			"FROM\r\n" + 
			"role_resource\r\n" + 
			"INNER JOIN resource ON role_resource.resource_id = resource.resource_id\r\n" + 
			"INNER JOIN role ON role.role_id = role_resource.role_id\r\n" + 
			"where resource.resource_id=#{resourceId}")
	List<Role> queryRoleByResourceId(int resourceId);

	@Delete("delete from role_resource where resource_id=#{resourceId}")
	void deletRoleResourceByResourceId(int resourceId);

	@Insert("insert into role_resource(resource_id,role_id) values(#{resourceId},#{roleId})")
	void addRoleResource(@Param("roleId") int roleId, @Param("resourceId") int resourceId);

	PageInfo<User> getUserByPage(int currentPage, int pagesize);

	@Select("SELECT * FROM user")
	List<User> getUserByPage();



}
