package com.leecx.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.leecx.pojo.DataDict;
import com.leecx.povo.DataDictVo;
import com.leecx.service.DataDictService;
import com.leecx.utils.JqGridResult;
import com.leecx.utils.LeeJSONResult;



@Controller
@RequestMapping("dataDict")
public class DataDictController {
	
	//@ResponseBody@Controller注解相当于@RestController
	//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
	private final static Logger log = LoggerFactory.getLogger(DataDictController.class);
	
	@Autowired
	private DataDictService dataDictService;
	
	@Autowired
	private Sid sid;
	
	@RequestMapping("/saveDict")
	public LeeJSONResult saveDataDict() throws Exception {
		
		log.info("保存用户, 当前时间：{}, 操作人：{}", new Date(), "Jack");
		String userId = sid.nextShort();
		log.info("idworker,  时间：{},唯一标识号：{}", new Date(), userId);
		
		DataDict dataDict = new DataDict();
		//dataDict.setId(new Integer(10));
		dataDict.setTypeName("订单状态");
		dataDict.setTypeCode("order");
		dataDict.setDdkey("S01");
		dataDict.setDdvalue("请求成功");
		dataDict.setIsShow(new Integer(1));
		for (int i = 0; i < 10; i++) {
			dataDict.setDdkey("S01"+i);
			dataDictService.saveDataDict(dataDict);
		}
		return LeeJSONResult.ok(dataDict);
		//return LeeJSONResult.ok("保存成功");
	}

	
	@RequestMapping("queryDict")
	@ResponseBody
	public LeeJSONResult queryDataDict() throws Exception {
		log.info("保存用户, 当前时间：{}, 操作人：{}", new Date(), "Jack");
		String userId = sid.nextShort();
		log.info("idworker,  时间：{},唯一标识号：{}", new Date(), userId);
		
		DataDict dataDict = new DataDict();
		dataDict.setId(18);
		dataDict.setTypeName("订单状态");
		dataDict.setDdvalue("Order");
		dataDict.setDdkey("S01");
		dataDict.setDdvalue("请求成功");
		dataDict.setIsShow(1);
		DataDict data=dataDictService.queryDataDictById(18);
		return LeeJSONResult.ok(data);
		//return LeeJSONResult.ok("保存成功");
	}
	
	
	/**
	 * 查询数据列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryList")
	@ResponseBody
	public LeeJSONResult queryUserList() throws Exception {
			
		PageInfo<DataDict> Info = dataDictService.getOnePagenoteData(1, 5, "订单状态", "order");
		return LeeJSONResult.ok(Info);
	}
	
	
	/**
	 * 查询数据列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryList3")
	public LeeJSONResult queryUserListPaged(Integer page)  throws Exception  {
		
		if (page == null) {
			page = 1;
		}
		int pageSize = 3;
		PageInfo<DataDictVo> Info = dataDictService.getOnePagenoteData3(page, pageSize, "订单状态", "order");
		return LeeJSONResult.ok(Info);
	}
	
	
	/**
	 * 返回数据列表视图,MVC
	 * @param request
	 * @return
	 */
	@RequestMapping("/showDataDictListPage")
	public String showDataDictListPage(HttpServletRequest request){
		log.debug("返回数据列表视图");
		return "apps/dataDict/dataDictList";
	}
	
	
	/**
	 * 查询数据字典列表;JqGridResult表格数据
	 * @param dataDict
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDataDictList")
	@ResponseBody
	public JqGridResult getDataDictList(DataDict dataDict, Integer page,Integer rows)  throws Exception {
		Integer pageSize=5;
		if (page == null) {
			page = 1;
		}
		pageSize=rows;
		JqGridResult result = dataDictService.queryDataDictList(null, null, page, pageSize);
		return result;
	}
	
	
	/**
	 * 打开新增数据字典页面
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/showCreateDataDictPage")
	public String showCreateUserPage(String userId, HttpServletRequest request){
		
		log.debug("显示数据字典页面");
		
		return "apps/dataDict/createDataDict";
	}
	

	
	/**
	 * 创建数据字典 或者 更新数据字典
	 * @param dd
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public LeeJSONResult saveOrUpdate(DataDict dd){
		
		// 数据字典id不为空，则修改数据字典；数据字典id为空，则新建数据字典
		Integer ddId = dd.getId();
		if (ddId != null && ddId > 0) {
			dataDictService.updateDataDictById(dd);
		} else {
			dataDictService.saveDataDict(dd);
		}
		
		return LeeJSONResult.ok();
	}
	
	
	
	/**
	 * 跳转到修改数据字典页面
	 * @param ddId
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyDataDict")
	public ModelAndView modifyDataDict(Integer ddId, HttpServletRequest request){
		log.debug("修改数据字典页面");
		// 查询数据字典个人信息
		DataDict dataDict = dataDictService.queryDataDictById(ddId);
		ModelAndView mv = new ModelAndView("apps/dataDict/modifyDataDict");
		mv.addObject("dataDict", dataDict);
		
		return mv;
	}
	
	/**
	 * 删除数据字典
	 * @param ddId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public LeeJSONResult delete(Integer ddId){
		
		dataDictService.deleteDataDictById(ddId);
		
		return LeeJSONResult.ok();
	}
}
