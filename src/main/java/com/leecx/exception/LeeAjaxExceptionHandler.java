package com.leecx.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.leecx.utils.LeeJSONResult;

//@RestControllerAdvice
public class LeeAjaxExceptionHandler {

//	@ExceptionHandler(value = Exception.class)
	public LeeJSONResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		e.printStackTrace();
		return LeeJSONResult.errorException(e.getMessage());
	}
}
