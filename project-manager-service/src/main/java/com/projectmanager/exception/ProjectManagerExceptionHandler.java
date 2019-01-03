/**
 * ProjectManagerExceptionHandler.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectmanager.controller.ProjectManagerController;

@ControllerAdvice(assignableTypes = {ProjectManagerController.class})
public class ProjectManagerExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody RestError handleCustomException (Exception ex, HttpServletResponse response) {
		response.setHeader("Content-Type", "application/json");
		if(ex instanceof ProjectManagerException){

			response.setStatus(((ProjectManagerException) ex).getReturnStatus());
			return ((ProjectManagerException) ex).transformException();
		}else
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			RestError restError = returnRestError();
			return restError;
		}
		
	}
	
	public RestError returnRestError()
	{
		RestError restError = new RestError();
		Exceptions exception = new Exceptions();
		exception.setType("E");
		exception.setCode("PP0001");
		exception.setMessage("One or more of the request parameters are missing/wrong. Please correct the request.");
		exception.setDetail("One or more of the request parameters are missing/wrong. Please correct the request.");
		Exceptions exceptions[] = new Exceptions[1];
		exceptions[0] = exception;
		restError.setExceptions(exceptions);
		return restError;
	}

}
