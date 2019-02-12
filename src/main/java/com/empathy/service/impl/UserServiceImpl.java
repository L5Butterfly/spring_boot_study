package com.empathy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.empathy.mapper.SysUserMapper;
import com.empathy.mapper.SysUserMapperCustom;
import com.empathy.pojo.SysUser;
import com.empathy.service.UserService;
import com.empathy.utils.JqGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysUserMapperCustom userMapperCustom;
	
	@Override
	public void updateUserById(SysUser user) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public JqGridResult queryUserList(String username, Integer page, Integer pageSize) throws Exception{
		return null;
	}

	@Override
	public SysUser queryUserInfoById(String userId) throws Exception{
		System.out.println("=====================");
		System.out.println("============userId========:"+userId);
		System.out.println("=====================");
		return userMapper.selectByPrimaryKey(userId);
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

	@Override
	public void saveUser(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(SysUser user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysUser queryUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysUser> queryUserList(SysUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JqGridResult queryUserListPagedJqgrid(SysUser user, Integer page, Integer pageSize) throws Exception {
		//设置分页数据
		PageHelper.startPage(page, pageSize);
		
		//Mapper参数映射设置
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("typeName",user.getUsername());
        map.put("typeCode",user.getNickname());
        
        //查询数据列表
		List<SysUser> docs = userMapperCustom.queryUserListByPage(user);
		PageInfo<SysUser> pageList = new PageInfo<SysUser>(docs);

		// 表格插件的一些分页信息
		JqGridResult grid = new JqGridResult();
		grid.setTotal(pageList.getPages());
		grid.setRows(docs);
		grid.setPage(pageList.getPageNum());
		grid.setRecords(pageList.getTotal());
		
		//自定义数据
		grid.setUserdata(null);
		return grid;
		
	}

	@Override
	public SysUser queryUserByIdCustom(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUserTransactional(SysUser user) {
		// TODO Auto-generated method stub
		
	}
}
