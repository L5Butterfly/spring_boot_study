project.loginPage = isPC() ? '/login.do' : '/m_login.do';

$.ajaxSetup({
    dataFilter : function(data, type){
    	if(data){
    		//ajax请求，发现session过期，重新刷新页面，跳转到登录页面
    	       if(data && (data == "timeOut" || data.indexOf("<!DOCTYPE html>") == 0)){
    	    	   BootstrapDialog.show({
    	    	        message: "登录超时，请重新登录", 
    	    	        type: BootstrapDialog.TYPE_DANGER, 
    	    	        title: '登录超时', 
    	    	        buttons: [{
    	    	            label: '重新登录',
    	    	            action: function (dialogItself) {
    	    	                dialogItself.close();
    	    	                location.href = project.loginPage;
    	    	            }
    	    	        }]
    	    	    });
    	       }else{
    	           return data;
    	       }
    	}else{
    		return data;
    	}
       
   }
});

$(document).ajaxSend(function(a,b,c){});

$(document).ajaxSuccess(function(event,xhr,options){});

$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
	console.log(event);
    console.log(jqxhr);
    console.log(settings);
    console.log(thrownError);
    var res = JSON.parse(jqxhr.responseText);
    var message = res.msg;
    project.errorWindow = message;
    BootstrapDialog.show({
        message: message, 
        type: BootstrapDialog.TYPE_DANGER, 
        title: '出错啦！', 
        onhide: function(){
        	if(jqxhr.status == 401)
        		project.errorWindow = '';
        },buttons: [{
            label: jqxhr.status == 401 ? '重新登录' : '确定',
            action: function (dialogItself) {
                dialogItself.close();
                if(jqxhr.status == 401)
                	location.href = project.loginPage;
            }
        }]
    });
});