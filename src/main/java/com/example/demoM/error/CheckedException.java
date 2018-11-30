package com.example.demoM.error;

public class CheckedException extends RuntimeException {

	private static final long serialVersionUID = 6032287195765223172L;
	private String msg;
	private String code;

	public CheckedException(String msg) {
		super();
		this.msg = msg;
	}

	public CheckedException(String code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public CheckedException(ErrorInfo errorInfo) {
		this.msg = errorInfo.getMsg();
		this.code = errorInfo.getCode();
	}

	public void setErrorMessage(String errorMessage) {
		this.msg = errorMessage;
	}

	public String getErrorMessage() {
		return msg;
	}

	public void setErrorCode(String errorCode) {
		this.code = errorCode;
	}

	public String getErrorCode() {
		return code;
	}
}
