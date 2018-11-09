package com.leecx.mapper;

import com.leecx.pojo.SysUser;
import com.leecx.utils.MyMapper;

public interface SysUserMapper extends MyMapper<SysUser> {
	
	//SysUserMapper继承MyMapper,默认封装好了通用对单张表的增删改查接口方法实现。
	//SysUserMapperCoutom,自定义Mapper,实现多表关联查询实现。
}