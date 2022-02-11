package com.htpe.bean;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

public class CsrData {
	
	private String param;
	
	
	private List<Map<String, Object>> params;



	public List<Map<String, Object>> getParams() {
		return params;
	}

	public void setParams(List<Map<String, Object>> params) {
		this.params = params;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "CsrData [param=" + param + ", params=" + params + "]";
	}


	
	
}
