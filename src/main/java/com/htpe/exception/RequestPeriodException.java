package com.htpe.exception;

public class RequestPeriodException extends Exception{
	
	/**
	 * 運行期間異常
	 */
	private static final long serialVersionUID = 1L;

	
	public RequestPeriodException(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private Integer code;
	
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	
	

}
