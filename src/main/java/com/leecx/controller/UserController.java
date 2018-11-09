package com.leecx.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leecx.common.enums.YesOrNo;
import com.leecx.common.utils.NumberUtil;
import com.leecx.pojo.DataDict;
import com.leecx.pojo.SysUser;
import com.leecx.service.DataDictService;
import com.leecx.service.UserService;
import com.leecx.utils.JqGridResult;
import com.leecx.utils.JsonUtils;
import com.leecx.utils.LeeJSONResult;



/**
 * 用户信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DataDictService ddService;
	
	
	/**
	 * 显示新增用户页面
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/showCreateUserPage")
	public ModelAndView showCreateUserPage(String userId, HttpServletRequest request){
		
		log.debug("显示用户个人信息页面");
		
		List<DataDict> ddlist = ddService.queryDataDictListByTypeCode("job");
		
		ModelAndView mv = new ModelAndView("apps/user/createUser");
		mv.addObject("ddlist", ddlist);
		
		return mv;
	}
	
	
	/**
	 * 创建用户 或者 更新用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public LeeJSONResult saveOrUpdate(SysUser user) throws Exception{
		
		// 用户id不为空，则修改用户；用户id为空，则新建用户
		String userId = user.getId();
		if (StringUtils.isEmpty(userId)) {
			// 生成4位随机组合字符作为权限的盐
			String authSalt = NumberUtil.getStringRandom(4);
			
			// 保存用户操作
			user.setAuthSalt(authSalt);
			user.setRegistTime(new Date());
			user.setIsDelete(YesOrNo.NO.value);
			userService.saveUser(user);
		} else {
			userService.updateUserById(user);
		}
		
		return LeeJSONResult.ok();
	}
	
	
	/**
	 * 显示用户信息列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/showUserInfoListPage")
	public String showUserInfoListPage(HttpServletRequest request){
		
		return "apps/user/userInfoList";
	}
	
	
	/**
	 * 按照条件查询用户列表
	 * @param user
	 * @param page
	 * @return
	 */
	@RequestMapping("/getUserInfoList")
	@ResponseBody
	public JqGridResult getUserInfoList(SysUser user, Integer page,Integer rows) throws Exception{
		Integer pageSize=5;
		log.info("====================================================");
		log.info("==============getUserInfoList=======================");
		log.info("====================================================");
		log.info(String.format("=====(page=%s,rows=%s)======",page,rows));
		if (page == null && rows==null) {
			page = 1;
		}
		pageSize=rows;
		
		JqGridResult result = userService.queryUserListPagedJqgrid(user, page, pageSize);
		log.info("==================result==================================");
		System.out.println(result);
		return result;
	}
	
	
	
	/**
	 * 查询用户详情
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/userInfo")
	public ModelAndView showUserInfo(String userId, HttpServletRequest request) throws Exception{
		
		// 查询用户个人信息
		SysUser userInfo = userService.queryUserInfoById(userId);
		
		ModelAndView mv = new ModelAndView("apps/user/userDetail");
		mv.addObject("userInfo", userInfo);
		mv.addObject("num", 12500);
		
		return mv;
	}
	
	
	
	/**
	 * 跳转到修改用户页面
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyUser") 
	public ModelAndView showModifyUser(String userId, HttpServletRequest request) throws Exception{
		
		// 查询用户个人信息
		SysUser userInfo = userService.queryUserInfoById(userId);
		
		List<DataDict> ddlist = ddService.queryDataDictListByTypeCode("job");
		
		ModelAndView mv = new ModelAndView("user/modifyUser");
		mv.addObject("userInfo", userInfo);
		mv.addObject("ddlist", ddlist);
		
		return mv;
	}
	
	/**
	 *  删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public LeeJSONResult update(String userId){
		
		userService.deleteUserById(userId);
		
		return LeeJSONResult.ok();
	}
	
	/**
	 * 用户修改昵称查询是否存在
	 * @param nickname
	 * @param userId
	 * @return
	 */
	@RequestMapping("/nicknameIsExist")
	@ResponseBody
	public LeeJSONResult nicknameIsExist(String nickname, String userId) {
		
		boolean isExist = userService.queryNicknameIsExist(nickname, userId);
		return LeeJSONResult.ok(isExist);
	}
	
	/**
	 * 用户名/登录名 是否是否存在
	 * @param username
	 * @param userId
	 * @return
	 */
	@RequestMapping("/usernameIsExist")
	@ResponseBody
	public LeeJSONResult usernameIsExist(String username, String userId) {
		
		boolean isExist = userService.queryUsernameIsExist(username, userId);
		return LeeJSONResult.ok(isExist);
	}

}
