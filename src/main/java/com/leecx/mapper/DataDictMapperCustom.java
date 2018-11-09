package com.leecx.mapper;

import java.util.List;
import java.util.Map;

import com.leecx.pojo.DataDict;
import com.leecx.povo.DataDictVo;
import com.leecx.utils.JqGridResult;


/**
 * 自定义dao层接口
 * @author Administrator
 *
 */
public interface DataDictMapperCustom {
	
	List<DataDict> queryDataDictSimplyInfoById(String id);
	
	String queryDataDictValueByCodeKey(String typeCode, String ddKey);
	
	JqGridResult queryDataDictList(String typeName, String typeCode, Integer page, Integer pageSize);
	
	List<DataDict> queryDataDictListByTypeCode(String typeCode);
	
	/*笔记分页查询*/
    List<DataDict> getDataDictBayCodeType(Map<String, Object> map) throws Exception;
    
    List<DataDictVo> getDataDictBayCodeType3(Map<String, Object> map) throws Exception;

}
