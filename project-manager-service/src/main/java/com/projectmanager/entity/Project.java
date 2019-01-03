/**
 * Project.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This is the entity class for the PROJECT table
 *
 */
@Entity
@Table(name="PROJECT")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PROJECT_ID")
	private int projectId;
	
	@Column(name="PROJECT")
	private String project;
	
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
	
	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	private Set<Task> task;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	private User user;
	
	public Project() {}

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

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
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

	/**
	 * @return the task
	 */
	public Set<Task> getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Set<Task> task) {
		this.task = task;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
