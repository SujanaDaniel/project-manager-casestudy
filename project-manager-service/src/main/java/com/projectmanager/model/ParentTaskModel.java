/**
 * ParentTaskModel.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This is the model class for the PARENT_TASK table
 *
 */
@JsonInclude(Include.NON_NULL)
public class ParentTaskModel {

	private int parentId;
	private String parentTask;
	private int projectId;
	
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return the parentTask
	 */
	public String getParentTask() {
		return parentTask;
	}
	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
}
