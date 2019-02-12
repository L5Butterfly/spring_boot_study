package com.empathy.service;

import java.util.List;

import com.empathy.pojo.DataDict;
import com.empathy.povo.DataDictVo;
import com.empathy.utils.JqGridResult;
import com.github.pagehelper.PageInfo;


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
