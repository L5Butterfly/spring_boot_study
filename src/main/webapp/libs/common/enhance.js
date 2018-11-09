//浏览器和版本方法兼容
typeof console == "undefined" && (console = {log:function(){},error:function(){}});
//项目的通用数据存储
if(typeof project == "undefined") project = {};

//日期拓展方法
Date.prototype.toString = function (format){ 
	var d = this;
	var res =  (format || "yyyy-MM-dd hh:mm:ss").replace("yyyy",d.getFullYear()).replace("YYYY",d.getFullYear());
	res = res.replace("MM",d.getMonth() < 9 ? "0"+(d.getMonth()+1) : (d.getMonth()+1));
	res = res.replace("dd",d.getDate()<10?"0"+d.getDate() : d.getDate()).replace("DD",d.getDate()<10?"0"+d.getDate() : d.getDate());
	res = res.replace("hh",d.getHours()<10?"0"+d.getHours():d.getHours()).replace("HH",d.getHours()<10?"0"+d.getHours():d.getHours());
	res = res.replace("mm",d.getMinutes()<10?"0"+d.getMinutes():d.getMinutes());
	res = res.replace("ss",d.getSeconds()<10?"0"+d.getSeconds():d.getSeconds());
	var millisecond = d.getMilliseconds();
	millisecond = millisecond<10 ? "00"+millisecond : millisecond;
	millisecond = millisecond<100 ? "0"+millisecond : millisecond;
	res = res.replace("SSS",millisecond);
	return res;
} 
Date.prototype.toDate = function(){return this;}
Date.prototype.offsetDay = function(day){var t = this;return new Date(t.getFullYear(),t.getMonth(),(t.getDate() + (day || 0)));};
Date.valid = function(date){return date != null && date != undefined && typeof date.getTime == "function" && typeof date.getTime() == "number" && !isNaN(date.getTime());}
Date.prototype.valid = function(){return Date.valid(this);}
Date.prototype.format = Date.prototype.toString;

//字符串拓展方法
String.prototype.stripTags =  function() {return this.replace(/<\/?[^>]+>/gi, '');}
String.prototype.toDate = function(){ return new Date(this.replace(/-/gi,"/")); }
String.prototype.String = function(){return this;}
String.prototype.trim=function(s){ if(s==undefined || s == null||s =="") return this.replace(/(^\s*)|(\s*$)/g, "");  var res = this;  while(res.endsWith(s))  res = res.substring(0,res.length-s.length); while(res.startsWith(s)) res = res.substring(s.length,res.length); return res;}
String.prototype.contains = function(s){return this.indexOf(s) !== -1;}
String.prototype.startsWith = function(prefix) { return prefix == undefined || prefix == null || prefix =="" ? false : this.slice(0, prefix.length) == prefix; }
String.prototype.endsWith = function(suffix) { return suffix == undefined || suffix == null || suffix =="" ? false : this.indexOf(suffix, this.length - suffix.length) !== -1;};
String.prototype.join = function(){return this;};
String.prototype.format = function(format){return this.toDate().toString(format);}
String.prototype.containAll = function(){
	args = arguments[0] instanceof Array ? arguments[0] : arguments
	for(var i =0;i<args.length;i++)
		if(!this.contains(args[i]))
			return false;
	return true;
}

String.prototype.containAny = function(){
	args = arguments[0] instanceof Array ? arguments[0] : arguments
	for(var i =0;i<args.length;i++)
		if(this.contains(args[i]))
			return true;
	return false;
}

//解析日期，now, forever , now - 7d, now + 7d , now +/- nd , 2015-12-23 23:23:23
function parseDate(expression,baseTime){
	 if(expression == undefined)
		return undefined;
	 expression = expression.trim();
	 if(expression == "today"){
		 return (new Date()).toString('yyyy-MM-dd').toDate();
	 }if(expression == "now"){
		return new Date();
     }else if(expression == "forever"){
     	return "2030-12-31 23:59:59".toDate();
     }else if(/now[-,+]\d*d/gi.test(expression.replace(/ /gi,""))){
      	var offsetDays = parseInt(expression.replace(/ |[a-z]|[A-Z]/gi,""));
      	return new Date((new Date()).getTime() + offsetDays*1000*60*60*24);  
      }else if(/today[-,+]\d*d/gi.test(expression.replace(/ /gi,""))){
         	var offsetDays = parseInt(expression.replace(/ |[a-z]|[A-Z]/gi,""));
         	return new Date((new Date()).toString('yyyy-MM-dd').toDate().getTime() + offsetDays*1000*60*60*24);
     }else if((expression.match(/\d| |:|-|\//gi)||[]).join("") == expression){
     	return new Date(expression);
     }else{
    	var offsetDays = parseInt(expression.replace(/ |[a-z]|[A-Z]/gi,""));
      	return new Date((baseTime instanceof Date ? baseTime.getTime() : 0 ) + offsetDays*1000*60*60*24);
     }
}

//PC or Mobile
function isPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone","SymbianOS", "Windows Phone","iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

//判断js对象是否为空
function isEmptyObj(obj){
	if (!obj){
		return true;
	}
	
	for(var key in obj){
		return false;
	}
	
	return true;
}

function formatTime(t,isShowEmpty){
    var s = [60,60,24,1000000];
    var ss = ['秒','分','小时','天'];
    var r = "";
    for(var i = 0;i<s.length;i++){
       r = (t%s[i] == 0 && !isShowEmpty) ? r : t%s[i] + ss[i]+ r;
       t = parseInt(t/s[i]);
       if(t==0)
    	   break;
    }
    if(/^\d*分$/gi.test(r)){
    	r += '钟';
    }
    return r;
}

//html标签转换
function htmlDecode(str) {
	var temp = document.createElement("div"); 
	temp.innerHTML = str; 
	var output = temp.innerText || temp.textContent; 
	temp = null; return output; 
};
//calMonth:月份
//weekSeq:一个月第几周
//dayNum:周几
//返回：calMonth月第weekSeq周第dayNum日
function GetMonthWeekDay(calMonth,weekSeq,dayNum)
{
	var weekDate=calMonth.format('yyyy-MM-01').toDate();
	var monthNow= weekDate.getMonth();
	var weekDay=weekDate.getDay();
	weekDay=weekDay==0?7:weekDay;
	//第一周
	if(weekSeq==1){
		if(dayNum==1){
			return weekDate;
		}
		else{
			weekDate.setDate(weekDate.getDate()+7-weekDay);			
			weekDate.setHours(23);
			weekDate.setMinutes(59);
			weekDate.setSeconds(59);
			return weekDate;
		}
	}
	else{
		weekDate.setDate(weekDate.getDate()+7-weekDay+1);
		if(dayNum==1){
			weekDate.setDate(weekDate.getDate()+(weekSeq-2)*7);
		}
		else{
			weekDate.setDate(weekDate.getDate()+(weekSeq-2)*7+6);
			if(weekDate.getMonth()>monthNow){
				weekDate.setDate(0);
			}
			weekDate.setHours(23);
			weekDate.setMinutes(59);
			weekDate.setSeconds(59);
		}
		return weekDate;
	}
}

//数组根据值移除元素
function arrayRemoveByValue(ary,val) {
	for(var i=0; i<ary.length; i++) {
		if(ary[i] == val) {
	      ary.splice(i, 1);
		  return ary;
		}
	}
}
////数组扩展方法
//Array.prototype.removeByValue = function(val) {
//  for(var i=0; i<this.length; i++) {
//    if(this[i] == val) {
//      this.splice(i, 1);
//      break;
//    }
//  }
//}