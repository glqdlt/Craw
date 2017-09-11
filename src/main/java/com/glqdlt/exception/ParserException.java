package com.glqdlt.exception;

@SuppressWarnings("serial")
public class ParserException extends RuntimeException {

	public ParserException(String msg) {
		super(msg);
	}

	public ParserException(String msg, Throwable e) {
		super(msg, e);
	}

}
