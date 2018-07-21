package com.prompt.marginplus.exceptions;

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = -4772808604311908425L;
	
	public UserNotFoundException(Exception e) {
		super(e);
	}
}
