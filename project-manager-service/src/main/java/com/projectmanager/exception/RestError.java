/**
 * RestError.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.exception;

public class RestError {

	private Exceptions[] exceptions;

	/**
	 * @return the exceptions
	 */
	public Exceptions[] getExceptions() {
		return exceptions;
	}

	/**
	 * @param exceptions the exceptions to set
	 */
	public void setExceptions(Exceptions[] exceptions) {
		this.exceptions = exceptions;
	}

}
