package com.prompt.marginplus.exceptions;

public class UserWithEmailExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserWithEmailExistsException(Exception e) {
		super(e);
	}

	public UserWithEmailExistsException(String string) {
		super(string);
	}
	
}
