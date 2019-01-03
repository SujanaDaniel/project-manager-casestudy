/**
 * ProjectManagerException.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.exception;

import java.io.Serializable;

public class ProjectManagerException extends Exception implements Serializable {

	private static final long serialVersionUID = -2848300257012566378L;

	private String errorCode;
	private String errorType;
	private String errorMessage;
	private int returnStatus;

	public ProjectManagerException(String errorCode, String errorMessage, int returnStatus) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.returnStatus = returnStatus;
	}

	public ProjectManagerException() {

	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorType
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the returnStatus
	 */
	public int getReturnStatus() {
		return returnStatus;
	}

	/**
	 * @param returnStatus the returnStatus to set
	 */
	public void setReturnStatus(int returnStatus) {
		this.returnStatus = returnStatus;
	}

	public RestError transformException() {
		RestError restError = new RestError();
		Exceptions exception = new Exceptions();
		exception.setType(this.errorType);
		exception.setCode(this.errorCode);
		exception.setMessage(this.errorMessage);
		exception.setDetail(this.errorMessage);
		Exceptions exceptions[] = new Exceptions[1];
		exceptions[0] = exception;
		restError.setExceptions(exceptions);
		return restError;
	}
}
