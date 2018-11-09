package com.leecx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leecx.mapper.SysUserMapper;
import com.leecx.mapper.SysUserMapperCustom;
import com.leecx.pojo.SysUser;
import com.leecx.service.UserService;
import com.leecx.utils.JqGridResult;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl3 implements UserService {

	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysUserMapperCustom userMapperCustom;
	
	@SuppressWarnings("static-access")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(SysUser user) throws Exception {
		java.lang.Thread thread = new java.lang.Thread();
		thread.sleep(5001);
		userMapper.insert(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(SysUser user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public SysUser queryUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SysUser> queryUserList(SysUser user) {
		
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
			criteria.andEqualTo("username", user.getUsername());
		}
		
		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		
		List<SysUser> userList = userMapper.selectByExample(example);
		
		return userList;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		
        PageHelper.startPage(page, pageSize);
		
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
			criteria.andEqualTo("username", user.getUsername());
		}
		
		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		
		List<SysUser> userList = userMapper.selectByExample(example);
		
		return userList;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public JqGridResult queryUserListPagedJqgrid(SysUser user, Integer page, Integer pageSize) {
		
        PageHelper.startPage(page, pageSize);
		
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
			criteria.andEqualTo("username", user.getUsername());
		}
		
		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		
		List<SysUser> userList = userMapper.selectByExample(example);
		
		PageInfo<SysUser> pageList = new PageInfo<SysUser>(userList);
		
		JqGridResult grid = new JqGridResult();
		grid.setTotal(pageList.getPages());
		grid.setRows(userList);
		grid.setPage(page);
		grid.setRecords(pageList.getTotal());
		
		return grid;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public SysUser queryUserByIdCustom(String userId) {
		
		List<SysUser> userList = userMapperCustom.queryUserSimplyInfoById(userId);
		
		if (userList != null && !userList.isEmpty()) {
			return (SysUser)userList.get(0);
		}
		
		return null;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserTransactional(SysUser user) {
		
		userMapper.insert(user);
		
		int a = 1 / 0;
		
		user.setIsDelete(1);
		userMapper.updateByPrimaryKeySelective(user);
	}

	
	@Override
	public void updateUserById(SysUser user) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public JqGridResult queryUserList(String username, Integer page, Integer pageSize) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysUser queryUserInfoById(String userId) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean queryNicknameIsExist(String nickname, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean queryUsernameIsExist(String username, String userId) {
		// TODO Auto-generated method stub
		return false;
	}
}
