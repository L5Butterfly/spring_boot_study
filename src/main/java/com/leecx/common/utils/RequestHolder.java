package com.leecx.common.utils;

import javax.servlet.http.HttpServletRequest;

import com.leecx.pojo.SysUser;


/**
 * 获取本地缓存里面加入的实例
 * @author Administrator
 *
 */
public class RequestHolder {
	
	private static final ThreadLocal<SysUser> userHolder = new ThreadLocal<SysUser>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(SysUser sysUser) {
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static SysUser getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }

}
