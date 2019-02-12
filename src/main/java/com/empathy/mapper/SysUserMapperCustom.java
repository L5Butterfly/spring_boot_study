package com.empathy.mapper;

import java.util.List;

import com.empathy.pojo.SysUser;

public interface SysUserMapperCustom {
	
	List<SysUser> queryUserSimplyInfoById(String id);
	
	List<SysUser> queryUserListByPage(SysUser user) throws Exception;
}