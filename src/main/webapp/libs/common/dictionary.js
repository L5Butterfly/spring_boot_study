project.isPC = isPC();

project.dictionay={};
$.post("/getDataDictionaryCache.do",null,function(res){
	if(!res.errcode)
		var display = {};
	for(var i in res){
		var obj ={};
		for(var j in res[i]){
			var item = res[i][j];
			obj[item.v] = item.n;
		}
		display[i] = obj;
	}
	project.dictionary = display;
	project._dictionary = res;
	
	initDic();
});

project.addDic = function(dicName,newDicName){
	if(newDicName == undefined || newDicName.trim() == "")
		throw "new dic name should not be empty !";
	if(project._dictionary[newDicName] != undefined){
		console.log(newDicName+" already exist !");
		return ;
	}
		
	project._dictionary[newDicName] = [];
	var newDic = project._dictionary[newDicName];
	for(var i = 2;i<arguments.length;i++){
		newDic.push(arguments[i]);
	}
	var orginalDic = project._dictionary[dicName] || [];
	for(var i = 0;i<orginalDic.length;i++){
		newDic.push(orginalDic[i]);
	}
	return newDic;
}

project.removeDic =function(dicName,newDicName){
	if(newDicName == undefined || newDicName.trim() == "")
		throw "new dic name should not be empty !";
	if(project._dictionary[newDicName] != undefined){
		console.log(newDicName+" already exist !");
		return ;
	}
		
	if(!(arguments[2] instanceof Array))
		throw "remove dic must be an array type";
	project._dictionary[newDicName] = [];
	var newDic = project._dictionary[newDicName];
	var orginalDic = project._dictionary[dicName] || [];
	var rmObj = arguments[2];
	var find = function(orgItem){
			for(var j = 0;j<rmObj.length;j++){
				var rmItem = rmObj[j];
				if(orgItem.v == rmItem.v && orgItem.n == rmItem.n)
				 return true;
			}
		return false;
	}
	for(var j = 0;j<orginalDic.length;j++){
		if(!find(orginalDic[j])){
			newDic.push(orginalDic[j]);
		}
	}
	
	return newDic;
}
var storage = {
		session : function session(key,value){
			if(typeof value =="undefined"){
				return sessionStorage ? sessionStorage.getItem(key) : null;
			}else{
				if(sessionStorage)
					sessionStorage.setItem(key,value);
				return this;
			}
		},
		local : function(key,value){
			if(typeof value =="undefined"){
				return localStorage ? localStorage.getItem(key) : null;
			}else{
				if(localStorage)
					localStorage.setItem(key,value);
				return this;
			}
		}
}

function initDic(){
	project.addDic('DIC_CONTRACT_TYPE','DIC_CONTRACT_TYPE_NEW',{v:'',n:'全部'});
	project.addDic('DIC_OPERATOR_TYPE','DIC_OPERATOR_TYPE_NEW',{v:'',n:'全部'});
	project.addDic('DIC_ORDER_STATUS','DIC_ORDER_STATUS_NEW',{v:'',n:'全部'});
	project.addDic('DIC_COMMON_FLAG','DIC_COMMON_FLAG_NEW',{v:'',n:'全部'});
	project.removeDic('DIC_REGION_ALL','DIC_REGION_ALL_REMOVE',[{v:'100',n:'中国'}]);
	project.addDic('DIC_REGION_ALL_REMOVE','DIC_REGION_NEW',{v:'',n:'全部'});
	project.addDic('DIC_SALE_PERSON','DIC_SALE_PERSON_NEW',{v:'',n:'全部'});
	project.addDic('DIC_DIS_SALE_PERSON','DIC_DIS_SALE_PERSON_NEW',{v:'',n:'全部'});
	project.addDic('DIC_COMPANY_TYPE','DIC_COMPANY_TYPE_NEW',{v:'',n:'公司类型'});
}