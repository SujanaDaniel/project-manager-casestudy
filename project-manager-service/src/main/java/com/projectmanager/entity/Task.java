/**
 * Task.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This is the entity class for the TASK table
 *
 */
@Entity
@Table(name="TASK")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TASK_ID")
	private int taskId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PARENT_ID")
	private ParentTask parent;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PROJECT_ID")
	private Project project;
	
	@Column(name="TASK")
	private String task;
	
	@Column(name="START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name="END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name="PRIORITY")
	private int priority;
	
	@Column(name="STATUS")
	private String status;
	
	public Task() {}

	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the parent
	 */
	public ParentTask getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(ParentTask parent) {
		this.parent = parent;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
