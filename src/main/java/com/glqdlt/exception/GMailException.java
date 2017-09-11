package com.glqdlt.exception;

@SuppressWarnings("serial")
public class GMailException extends Exception {
	public GMailException(String msg) {
		super(msg);
	}

	public GMailException(String msg, Throwable e) {
		super(msg, e);
	}
}
