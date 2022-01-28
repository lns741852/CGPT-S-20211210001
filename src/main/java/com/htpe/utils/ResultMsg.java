package com.htpe.utils;

/**
 * 返回JSON前端公用類
 *
 */
public class ResultMsg {
	
	private boolean success;
	
	private String msg;
		
	private Integer code;
	
	private Object data;
	
	
	public static ResultMsg success(String msg){
		ResultMsg result = new ResultMsg();
		result.setSuccess(true);
		result.setCode(200);
		result.setMsg(msg);
		return result;
	}
	
	
	public static ResultMsg fail(Integer code, String msg){
		ResultMsg result = new ResultMsg();
		result.setSuccess(false);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	
	//使用方法將map傳入
	
	public ResultMsg addData(Object data){
		this.data =data;
		return this;
	}
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}	

}
