/**
 * ProjectManagerController.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.exception.ProjectManagerException;
import com.projectmanager.model.ParentTaskModel;
import com.projectmanager.model.ProjectModel;
import com.projectmanager.model.TaskModel;
import com.projectmanager.model.UserModel;
import com.projectmanager.service.ProjectManagerService;

/**
 * This class acts as a rest controller for exposing the different end-points in the Project Manager application
 */
@RestController
@RequestMapping(value = "/projectmanager")
public class ProjectManagerController {
	
	@Autowired
	ProjectManagerService projectManagerServiceImpl;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManagerController.class); 
	
	/**
	 * This method is used to retrieve the list of available tasks
	 * @return List<TaskModel>
	 * @throws ProjectManagerException
	 */
	@GetMapping(value = "/get/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskModel> getTasks() throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside getTasks - start");
		List<TaskModel> taskList = new ArrayList<TaskModel> ();
		taskList = projectManagerServiceImpl.getTasks();
		LOGGER.info("ProjectManagerController - Inside getTasks - end");
		return taskList;
	}
	
	/**
	 * This method is used to retrieve the list of available parent tasks
	 * @return List<ParentTaskModel>
	 * @throws ProjectManagerException
	 */
	@GetMapping(value = "/get/parenttasks", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ParentTaskModel> getParentTasks() throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside getParentTasks - start");
		List<ParentTaskModel> parentTaskList = new ArrayList<ParentTaskModel> ();
		parentTaskList = projectManagerServiceImpl.getParentTasks();
		LOGGER.info("ProjectManagerController - Inside getParentTasks - end");
		return parentTaskList;
	}
	
	/**
	 * This method is used to retrieve the list of available projects
	 * @return List<ProjectModel>
	 * @throws ProjectManagerException
	 */
	@GetMapping(value = "/get/projects", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectModel> getProjects() throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside getProjects - start");
		List<ProjectModel> projectList = new ArrayList<ProjectModel> ();
		projectList = projectManagerServiceImpl.getProjects();
		LOGGER.info("ProjectManagerController - Inside getProjects - end");
		return projectList;
	}
	
	/**
	 * This method is used to retrieve the list of available users
	 * @return List<UserModel>
	 * @throws ProjectManagerException
	 */
	@GetMapping(value = "/get/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserModel> getUsers() throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside getUsers - start");
		List<UserModel> usersList = new ArrayList<UserModel> ();
		usersList = projectManagerServiceImpl.getUsers();
		LOGGER.info("ProjectManagerController - Inside getUsers - end");
		return usersList;
	}
	
	/**
	 * This method is used to retrieve the user details linked to a particular project id
	 * @param projectId
	 * @return ProjectModel
	 * @throws ProjectManagerException
	 */
	@GetMapping(value = "/get/user/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProjectModel getUserDetailsForProject(@PathVariable("projectId") int projectId) throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside getUserDetailsForProject - start");
		ProjectModel projectModel = projectManagerServiceImpl.getUserDetailsForProject(projectId);
		LOGGER.info("ProjectManagerController - Inside getUserDetailsForProject - end");
		return projectModel;
	}
	
	/**
	 * This method is used to retrieve the list of tasks linked to a particular project id
	 * @param projectId
	 * @return List<TaskModel>
	 * @throws ProjectManagerException
	 */
	@GetMapping(value = "/get/task/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskModel> getTasksForProject(@PathVariable("projectId") int projectId) throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside getTasksForProject - start");
		List<TaskModel> taskModelList = projectManagerServiceImpl.getTasksForProject(projectId);
		LOGGER.info("ProjectManagerController - Inside getTasksForProject - end");
		return taskModelList;
	}
	
	/**
	 * This method is used to add a new task or to edit/delete an existing task
	 * @param taskModel
	 * @return boolean
	 * @throws ProjectManagerException
	 */
	@PostMapping(value = "/update/task", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateTask(@RequestBody TaskModel taskModel) throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside updatetask - start");
		boolean status = false;
		status = projectManagerServiceImpl.updateTask(taskModel);
		LOGGER.info("ProjectManagerController - Inside updatetask - end");
		return status;
	}
	
	/**
	 * This method is used to add a new project or to edit/delete an existing project
	 * @param projectModel
	 * @return boolean
	 * @throws ProjectManagerException
	 */
	@PostMapping(value = "/update/project", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateProject(@RequestBody ProjectModel projectModel) throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside updateProject - start");
		boolean status = false;
		status = projectManagerServiceImpl.updateProject(projectModel);
		LOGGER.info("ProjectManagerController - Inside updateProject - end");
		return status;
	}
	
	/**
	 * This method is used to add a new user or to edit/delete an existing user
	 * @param userModel
	 * @return boolean
	 * @throws ProjectManagerException
	 */
	@PostMapping(value = "/update/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateUser(@RequestBody UserModel userModel) throws ProjectManagerException {
		LOGGER.info("ProjectManagerController - Inside updateUser - start");
		boolean status = false;
		status = projectManagerServiceImpl.updateUser(userModel);
		LOGGER.info("ProjectManagerController - Inside updateUser - end");
		return status;
	}

}
