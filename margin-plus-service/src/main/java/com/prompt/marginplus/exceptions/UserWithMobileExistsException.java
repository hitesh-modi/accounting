package com.prompt.marginplus.exceptions;

public class UserWithMobileExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserWithMobileExistsException(Exception e) {
		super(e);
	}

	public UserWithMobileExistsException(String string) {
		super(string);
	}
	
}
