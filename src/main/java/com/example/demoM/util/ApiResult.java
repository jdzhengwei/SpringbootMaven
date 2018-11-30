package com.example.demoM.util;

import com.example.demoM.error.ErrorInfo;

public final class ApiResult<T> {

	private static final int STATUS_SUCCESS = 1;

	private static final int STATUS_ERROR = -1;

	private int status = STATUS_SUCCESS;

	private String code = "";
	
	private String msg = "";

	private T result;


	public ApiResult(T data) {
		this.result = data;
	}
	
	private ApiResult() {
	}
	
	public static <T> ApiResult<T> errorResult(String code, String msg) {
		ApiResult<T> result = new ApiResult<>();
		result.code = code;
		result.msg = msg;
		result.status = STATUS_ERROR;
		return result;
	}
	
	public static <T> ApiResult<T> errorResult(String code, String msg, T data) {
		ApiResult<T> result = errorResult(code, msg);
		result.result = data;
		return result;
	}
	
	public static <T> ApiResult<T> errorResult(ErrorInfo errorInfo) {
		return errorResult(errorInfo.getCode(), errorInfo.getMsg());
	}
	
	public static <T> ApiResult<T> errorResult(ErrorInfo errorInfo, T data) {
		return errorResult(errorInfo.getCode(), errorInfo.getMsg(), data);
	}

	public int getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getResult() {
		return result;
	}

	public static int getStatusSuccess(){
		return STATUS_SUCCESS;
	}

	public static int getStatusError(){
		return STATUS_ERROR;
	}

}
