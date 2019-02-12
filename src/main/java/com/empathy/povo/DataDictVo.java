package com.empathy.povo;

import com.empathy.pojo.DataDict;

public class DataDictVo extends DataDict {
	
	//package com.leecx.povo.DataDictVo
	private String showName;

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	@Override
	public void setIsShow(Integer isShow) {
		super.setIsShow(isShow);
		if(isShow==1) {
			this.showName="启用";
		}
		else{
			this.showName="禁用";
		}
	}
	
}
