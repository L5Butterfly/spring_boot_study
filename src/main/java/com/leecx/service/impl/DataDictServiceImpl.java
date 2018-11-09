package com.leecx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leecx.mapper.DataDictMapper;
import com.leecx.mapper.DataDictMapperCustom;
import com.leecx.pojo.DataDict;
import com.leecx.povo.DataDictVo;
import com.leecx.service.DataDictService;
import com.leecx.utils.JqGridResult;

@Service
public class DataDictServiceImpl implements DataDictService {

	
	@Autowired
	private DataDictMapper dataDictMapper;
	
	@Autowired
	private DataDictMapperCustom dataDictMapperCustom;
	
	@Override
	public void saveDataDict(DataDict dataDict) {
		dataDictMapper.insert(dataDict);
		
	}

	@Override
	public void deleteDataDictById(Integer ddId) {
		dataDictMapper.deleteByPrimaryKey(ddId);
		
	}

	@Override
	public DataDict queryDataDictById(Integer ddId) {
		return dataDictMapper.selectByPrimaryKey(ddId);
	}

	@Override
	public void updateDataDictById(DataDict dataDict) {
		dataDictMapper.updateByPrimaryKeySelective(dataDict);
		
	}

	@Override
	public String queryDataDictValueByCodeKey(String typeCode, String ddKey) {
		return dataDictMapperCustom.queryDataDictValueByCodeKey(typeCode, ddKey);
	}

	@Override
	public JqGridResult queryDataDictList(String typeName, String typeCode, Integer page, Integer pageSize) throws Exception{
		
	    //PageHelper.startPage(pageIndex, pageSize);
		//List<DataDict> userList = dataDictMapper.selectByExample(dde);
        //PageInfo<DataDict> pageInfo = new PageInfo<>(docs);
		
		PageHelper.startPage(page, pageSize);
		
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("typeName",typeName);
        map.put("typeCode",typeCode);
        
		List<DataDict> docs = dataDictMapperCustom.getDataDictBayCodeType(map);
		PageInfo<DataDict> pageList = new PageInfo<DataDict>(docs);

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
	public List<DataDict> queryDataDictListByTypeCode(String typeCode) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public PageInfo<DataDict> getOnePagenoteData(Integer pageIndex, Integer pageSize, String type_name,String type_code) throws Exception {
		创建分页工具类
        PageHelper.startPage(pageIndex, pageSize);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("typeName",type_name);
        map.put("typeCode",type_code);
        List<DataDict> docs = dataDictMapperCustom.getDataDictBayCodeType(map);
        List<DataDictVo> docts=dataDictMapperCustom.getDataDictBayCodeType3(map);
        PageInfo<DataDict> pageInfo = new PageInfo<>(docs);
        return pageInfo;
	}*/
	
	@Override
	public PageInfo<DataDict> getOnePagenoteData(Integer pageIndex, Integer pageSize, String type_name,String type_code) throws Exception {
		/*创建分页工具类*/
        PageHelper.startPage(pageIndex, pageSize);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("typeName",type_name);
        map.put("typeCode",type_code);
        List<DataDict> docs = dataDictMapperCustom.getDataDictBayCodeType(map);
        PageInfo<DataDict> pageInfo = new PageInfo<>(docs);
        return pageInfo;
	}

	@Override
	public PageInfo<DataDictVo> getOnePagenoteData3(Integer pageIndex, Integer pageSize, String type_name,String type_code) throws Exception {
		/*创建分页工具类*/
        PageHelper.startPage(pageIndex, pageSize);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("typeName",type_name);
        map.put("typeCode",type_code);
        List<DataDictVo> docs=dataDictMapperCustom.getDataDictBayCodeType3(map);
        PageInfo<DataDictVo> pageInfo = new PageInfo<>(docs);
        return pageInfo;
	}
}
