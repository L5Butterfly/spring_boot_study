/**
 * jQuery Validation Plugin 1.9.0
 * 
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * http://docs.jquery.com/Plugins/Validation
 * 
 * Copyright (c) 2006 - 2011 Jörn Zaefferer
 * 
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 */

(function() {

	function stripHtml(value) {
		// remove html tags and space chars
		return value.replace(/<.[^<>]*?>/g, ' ').replace(/&nbsp;|&#160;/gi, ' ')
		// remove numbers and punctuation
		.replace(/[0-9.(),;:!?%#$'"_+=\/-]*/g, '');
	}
	jQuery.validator.addMethod("required", function(value, element) {
		switch( element.nodeName.toLowerCase() ) {
		case 'select':
			var val = $(element).val();
			return val && val.length > 0;
		case 'input':
			if ( this.checkable(element) )
				return this.getLength(value, element) > 0;
		default:
			return $.trim(value).length > 0;
		}
	}, "请输入必填项");
	jQuery.validator.addMethod("ip", function(value, element) {
		var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
		return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256
						&& RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
	}, "Ip地址格式错误");
	
	jQuery.validator.addMethod("maxWords", function(value, element, params) {
		return this.optional(element) || stripHtml(value).match(/\b\w+\b/g).length < params;
	}, jQuery.validator.format("Please enter {0} words or less."));

	jQuery.validator.addMethod("minWords", function(value, element, params) {
		return this.optional(element) || stripHtml(value).match(/\b\w+\b/g).length >= params;
	}, jQuery.validator.format("Please enter at least {0} words."));

	jQuery.validator.addMethod("rangeWords", function(value, element, params) {
		return this.optional(element) || stripHtml(value).match(/\b\w+\b/g).length >= params[0]
				&& value.match(/bw+b/g).length < params[1];
	}, jQuery.validator.format("Please enter between {0} and {1} words."));

})();

// same as email, but TLD is optional
jQuery.validator.addMethod("email2", function(value, element, param) {
	return this.optional(element)
			|| /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)*(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
					.test(value);
}, "请输入有效的邮箱地址");

// same as url, but TLD is optional
jQuery.validator.addMethod("url2", function(value, element, param) {
	return this.optional(element)
			|| /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)*(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i
					.test(value);
}, jQuery.validator.messages.url);

jQuery.validator.addMethod("ipv4", function(value, element, param) {
	return this.optional(element)
			|| /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/i
					.test(value);
}, "请输入有效IP地址.");

jQuery.validator.addMethod("ipv6", function(value, element, param) {
	return this.optional(element)
			|| /^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$/i
					.test(value);
}, "请输入有效IP地址.");

jQuery.validator.addMethod("macv6",function(value, element, param) {
	return this.optional(element)
			|| /[A-F\d]{2}\:[A-F\d]{2}\:[A-F\d]{2}\:[A-F\d]{2}\:[A-F\d]{2}\:[A-F\d]{2}/
					.test(value);
}, "请输入有效MAC地址.");

jQuery.validator.addMethod("nochinese", function(value, element, param) {
	return this.optional(element) ||/^[\w\.]{0,32}$/.test(value);
}, "含有中文或特殊字符,或长度大于32,修改后请重试");


// ===================放在validate.addmethod.js中=========================

function checkidcard(num) {
	var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(num);
	if(!check) return false;
	
	var len = num.length, re;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
	else {
		// alert("请输入15或18位身份证号,您输入的是 "+len+ "位");
		return false;
	}
	var a = num.match(re);
	if (a != null) {
		if (len == 15) {
			var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];
		} else {
			var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];
		}
		if (!B) {
			// alert("输入的身份证号 "+ a[0] +" 里出生日期不对！");
			return false;
		}
	}

	return true;
}

// 检查号码是否符合规范，包括长度，类型
isCardNo = function(card) {
	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
	var reg = /(^d{15}$)|(^d{17}(d|X)$)/;
	if (reg.test(card) === false) {
		return false;
	}
	return true;
};

// 取身份证前两位,校验省份
checkProvince = function(card) {
	var province = card.substr(0, 2);
	if (vcity[province] == undefined) {
		return false;
	}
	return true;
};

// 检查生日是否正确
checkBirthday = function(card) {
	var len = card.length;
	// 身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
	if (len == '15') {
		var re_fifteen = /^(d{6})(d{2})(d{2})(d{2})(d{3})$/;
		var arr_data = card.match(re_fifteen);
		var year = arr_data[2];
		var month = arr_data[3];
		var day = arr_data[4];
		var birthday = new Date('19' + year + '/' + month + '/' + day);
		return verifyBirthday('19' + year, month, day, birthday);
	}
	// 身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
	if (len == '18') {
		var re_eighteen = /^(d{6})(d{4})(d{2})(d{2})(d{3})([0-9]|X)$/;
		var arr_data = card.match(re_eighteen);
		var year = arr_data[2];
		var month = arr_data[3];
		var day = arr_data[4];
		var birthday = new Date(year + '/' + month + '/' + day);
		return verifyBirthday(year, month, day, birthday);
	}
	return false;
};

// 校验日期
verifyBirthday = function(year, month, day, birthday) {
	var now = new Date();
	var now_year = now.getFullYear();
	// 年月日是否合理
	if (birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day) {
		// 判断年份的范围（3岁到100岁之间)
		var time = now_year - year;
		if (time >= 3 && time <= 100) {
			return true;
		}
		return false;
	}
	return false;
};
// 校验位的检测
checkParity = function(card) {
	// 15位转18位
	card = changeFivteenToEighteen(card);
	var len = card.length;
	if (len == '18') {
		var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
		var cardTemp = 0, i, valnum;
		for (i = 0; i < 17; i++) {
			cardTemp += card.substr(i, 1) * arrInt[i];
		}
		valnum = arrCh[cardTemp % 11];
		if (valnum == card.substr(17, 1)) {
			return true;
		}
		return false;
	}
	return false;
};
// 15位转18位身份证号
changeFivteenToEighteen = function(card) {
	if (card.length == '15') {
		var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
		var cardTemp = 0, i;
		card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
		for (i = 0; i < 17; i++) {
			cardTemp += card.substr(i, 1) * arrInt[i];
		}
		card += arrCh[cardTemp % 11];
		return card;
	}
	return card;
};
isIdCardNo = function(card) {
	// 是否为空
	if (card === '') {
		return false;
	}
	// 校验长度，类型
	if (isCardNo(card) === false) {
		// return false;
	}
	// 检查省份
	if (checkProvince(card) === false) {
		return false;
	}
	// 校验生日
	if (checkBirthday(card) === false) {
		return false;
	}
	// 检验位的检测
	if (checkParity(card) === false) {
		return false;
	}
	return true;
};
// 身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
	return this.optional(element) || checkidcard(value) ;
}, "请正确输入您的身份证号码");
// 护照号格式验证
jQuery.validator.addMethod("isPassport", function(value, element) {
	var passport = /^(1[45][0-9]{7})|(G[0-9]{8})|(P[0-9]{7})|(S[0-9]{7,8})|(D[0-9]+)$/;
	return this.optional(element) || (passport.test(value));
}, "请正确填写您的护照号");

// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
	
	//固话
	if(mobile.test(value)){
		return true;
	}
	
	//手机
	//var mobileRex = /^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\d{8}$/;
	var mobileRex = /^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|9[0-9]|7[0-9])\d{8}$/;
	var cmRex = /^(1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\d{8})|(1705\d{7})$/;
	var cuRex = /^(1(3[0-2]|4[5]|5[56]|7[6]|8[56])\d{8})|(1709\d{7})$/;
    var ctRex = /^(1(33|53|77|8[019])\d{8})|(1700\d{7})$/; 
    
	return this.optional(element) || (length == 11 && (mobileRex.test(value) 
			||cmRex.test(value) ||cuRex.test(value) ||ctRex.test(value)));
}, "请正确填写您的手机号码");

//密码为6-10位的字母或数字
jQuery.validator.addMethod("isPassword", function(value, element) {
	var length = value.length;
	if(length != 8){
		return false;
	}
    var reg = /^(?![^a-zA-Z]+$)(?!\D+$)/;
	return this.optional(element) || (reg.test(value));
}, "密码为8位的字母和数字组合");

//端口校验
jQuery.validator.addMethod("isPort", function(value, element) {
	var length = value.length;
	 var reg =  /^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/;
	return this.optional(element) || (reg.test(value));
}, "上报端口请输入0到65535的整数");

//税率校验0.00-100.00
jQuery.validator.addMethod("isRate", function(value, element) {
	 var reg =  /^(100|[1-9]?\d(\.\d\d?)?)$/;
	return this.optional(element) || (reg.test(value));
}, "请填写正确的税率");

jQuery.validator.addMethod("isPrice", function(value, element) {
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	return this.optional(element) || (reg.test(value));
}, "金额格式有误");

//唯一性校验
jQuery.validator.addMethod("unique", function(value, element, params) {
	//禁用状态无需验证
	if($(element).prop('disabled') || $(element).prop('readonly')){
		return true;
	}
	
	//编辑状态如果是原始值，无需验证
	var $form = $($(element).parents("form")[0]);
	if($form.status() == 'edit' ){
		var backData = $form.data('backup');
		if(value == backData[element.name]){
			return true;
		}
	}
	
	if ( this.optional(element) )
		return "dependency-mismatch";

	var previous = this.previousValue(element);
	if (!this.settings.messages[element.name] )
		this.settings.messages[element.name] = {};
	previous.originalMessage = this.settings.messages[element.name].remote;
	this.settings.messages[element.name].remote = previous.message;

	if ( this.pending[element.name] ) {
		return "pending";
	}
	
	var data = {};
	data[element.name] = value;
	//处理携带的其他参数，keys为其他元素的名称
	var names = params.names;
	if(names){
		var nameArr = names.split(',');
		for(var i=0; i< nameArr.length; i++){
			data[nameArr[i]]= $form.find('input[name="'+nameArr[i]+'"]').val() || $form.find('select[name="'+nameArr[i]+'"]').val();
		}
	}
	var url = params.url;
	var xjlValue = url+"?"+JSON.stringify(data);
	if ( previous.old === value && previous.xjlValue === xjlValue) {
		return previous.valid;
	}

	previous.old = value;
	previous.xjlValue = xjlValue;
	var validator = this; 
	this.startRequest(element);
	
	$.ajax({
		url: url,
		data: data,
		type: 'POST',
		success: function(res){
			validator.settings.messages[element.name].remote = previous.originalMessage;
			var valid = res;
			if ( valid ) {
				var submitted = validator.formSubmitted;
				validator.prepareElement(element);
				validator.formSubmitted = submitted;
				validator.successList.push(element);
				validator.showErrors();
			} else {
				var errors = {};
				var message = validator.defaultMessage( element, "unique" );
				errors[element.name] = previous.message = $.isFunction(message) ? message(value) : message;
				validator.showErrors(errors);
			}
			previous.valid = valid;
			validator.stopRequest(element, valid);
		}
	});
	return "pending";
},"请重新填写此值");