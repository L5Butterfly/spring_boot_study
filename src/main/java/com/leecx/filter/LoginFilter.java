package com.leecx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leecx.common.utils.RequestHolder;
import com.leecx.pojo.SysUser;


public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        SysUser sysUser = (SysUser)req.getSession().getAttribute("user");
        //登录校验
        if (sysUser == null) {
            String path = "apps/login.html";
            resp.sendRedirect(path);
            return;
        }
        
        //添加登录用户信息到当前线程缓存
        RequestHolder.add(sysUser);
        //添加请求信息到当前线程缓存
        RequestHolder.add(req);
        //执行
        filterChain.doFilter(servletRequest, servletResponse);
        return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
