package com.WCAssignmentFinal.exception;

public class UserAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7493958252536719121L;
	
	public UserAlreadyExistsException () {
		super("Unable to register account!");
	}

}
