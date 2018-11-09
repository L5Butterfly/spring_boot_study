package com.leecx.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.leecx.pojo.DataDict;
import com.leecx.povo.DataDictVo;
import com.leecx.utils.JqGridResult;


public interface DataDictService {

	public void saveDataDict(DataDict dataDict);
	
	public void deleteDataDictById(Integer ddId);
	
	public DataDict queryDataDictById(Integer ddId);
	
	public void updateDataDictById(DataDict dataDict);
	
	public String queryDataDictValueByCodeKey(String typeCode, String ddKey);
	
	public JqGridResult queryDataDictList(String typeName, String typeCode, Integer page, Integer pageSize) throws Exception;
	
	public List<DataDict> queryDataDictListByTypeCode(String typeCode);
	
	PageInfo<DataDict> getOnePagenoteData(Integer pageIndex, Integer pageSize, String type_name, String type_code) throws Exception;
	
	PageInfo<DataDictVo> getOnePagenoteData3(Integer pageIndex, Integer pageSize, String type_name, String type_code) throws Exception;
	
}
