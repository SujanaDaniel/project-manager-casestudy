/**
 * ProjectManagerService.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.service;

import java.util.List;

import com.projectmanager.exception.ProjectManagerException;
import com.projectmanager.model.ParentTaskModel;
import com.projectmanager.model.ProjectModel;
import com.projectmanager.model.TaskModel;
import com.projectmanager.model.UserModel;

/**
 * This is the interface for the service methods in the Project Manager Application
 *
 */
public interface ProjectManagerService {
	
	/**
	 * @return List<TaskModel>
	 * @throws ProjectManagerException
	 */
	List<TaskModel> getTasks() throws ProjectManagerException;
	
	/**
	 * @return List<ParentTaskModel>
	 * @throws ProjectManagerException
	 */
	List<ParentTaskModel> getParentTasks() throws ProjectManagerException;
	
	/**
	 * @return List<ProjectModel>
	 * @throws ProjectManagerException
	 */
	List<ProjectModel> getProjects() throws ProjectManagerException;
	
	/**
	 * @return List<UserModel>
	 * @throws ProjectManagerException
	 */
	List<UserModel> getUsers() throws ProjectManagerException;
	
	/**
	 * @param projectId
	 * @return ProjectModel
	 * @throws ProjectManagerException
	 */
	ProjectModel getUserDetailsForProject(int projectId) throws ProjectManagerException;

	/**
	 * @param projectId
	 * @return List<TaskModel>
	 * @throws ProjectManagerException
	 */
	List<TaskModel> getTasksForProject(int projectId) throws ProjectManagerException;
	
	/**
	 * @param taskModel
	 * @return boolean
	 * @throws ProjectManagerException
	 */
	boolean updateTask(TaskModel taskModel) throws ProjectManagerException;
	
	/**
	 * @param projectModel
	 * @return boolean
	 * @throws ProjectManagerException
	 */
	boolean updateProject(ProjectModel projectModel) throws ProjectManagerException;
	
	/**
	 * @param usersModel
	 * @return boolean
	 * @throws ProjectManagerException
	 */
	boolean updateUser(UserModel usersModel) throws ProjectManagerException;
	
}
