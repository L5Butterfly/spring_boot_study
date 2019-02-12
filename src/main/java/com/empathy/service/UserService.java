package com.empathy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.empathy.pojo.SysUser;
import com.empathy.utils.JqGridResult;


public interface UserService {

	/**
	 * 保存用户信息
	 * @param user
	 * @throws Exception
	 */
	public void saveUser(SysUser user) throws Exception;

	public void updateUser(SysUser user);

	public void deleteUser(String userId);

	public SysUser queryUserById(String userId);

	public List<SysUser> queryUserList(SysUser user);

	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

	public SysUser queryUserByIdCustom(String userId);
	
	public void saveUserTransactional(SysUser user);
	
	public void updateUserById(SysUser user) throws Exception;
	
	/**
	 * 查询用户列表
	 * @param username
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public JqGridResult queryUserList(String username, Integer page, Integer pageSize) throws Exception;
	
	
	/**
	 *  查询用户详细信息
	 * @param userId
	 * @return
	 */
	public SysUser queryUserInfoById(String userId) throws Exception;
	
	/**
	 * 删除用户
	 * @param userId
	 */
	public void deleteUserById(String userId);
	
	/**
	 * 检查昵称是否存在
	 * @param nickname
	 * @param userId
	 * @return
	 */
	public boolean queryNicknameIsExist(String nickname, String userId);
	
	
	/**
	 * 查用户是否存在
	 * @param username
	 * @param userId
	 * @return
	 */
	public boolean queryUsernameIsExist(String username, String userId);
	
	
	/**
	 * 用户列表
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public JqGridResult queryUserListPagedJqgrid(SysUser user, Integer page, Integer pageSize) throws Exception;
	
}


