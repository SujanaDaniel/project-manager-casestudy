/**
 * ProjectManagerServiceImplTest.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanager.entity.ParentTask;
import com.projectmanager.entity.Project;
import com.projectmanager.entity.Task;
import com.projectmanager.entity.User;
import com.projectmanager.exception.ProjectManagerException;
import com.projectmanager.model.ParentTaskModel;
import com.projectmanager.model.ProjectModel;
import com.projectmanager.model.TaskModel;
import com.projectmanager.model.UserModel;
import com.projectmanager.repository.ParentTaskRepository;
import com.projectmanager.repository.ProjectRepository;
import com.projectmanager.repository.TaskRepository;
import com.projectmanager.repository.UserRepository;

/**
 * This is the test class for the Project Manager Service
 *
 */
@RunWith(SpringRunner.class)
public class ProjectManagerServiceImplTest {
	
	private ProjectRepository projectRepository;
	private TaskRepository taskRepository;
	private ParentTaskRepository parentTaskRepository;
	private UserRepository userRepository;
	
	private ProjectManagerServiceImpl projectManagerServiceImpl;
	
	@Spy
    private DozerBeanMapper dozerMapper;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setUp() throws Exception {
		projectRepository = Mockito.mock(ProjectRepository.class);
		taskRepository = Mockito.mock(TaskRepository.class);
		parentTaskRepository = Mockito.mock(ParentTaskRepository.class);
		userRepository = Mockito.mock(UserRepository.class);
		projectManagerServiceImpl = new ProjectManagerServiceImpl(projectRepository, taskRepository, parentTaskRepository, userRepository, dozerMapper);
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void getTasks() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<List<Task>> mapType = new TypeReference<List<Task>>() {};
		List<Task> allTasks = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("tasks.json").getFile());
		allTasks = mapper.readValue(file, mapType);
		when(taskRepository.findAll()).thenReturn(allTasks);
		List<TaskModel> taskDetails = projectManagerServiceImpl.getTasks();
		Assert.assertNotNull(taskDetails);
		verify(taskRepository,times(1)).findAll();
		verifyNoMoreInteractions(taskRepository);
	}
	
	@Test
	public void getProjects() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<List<Project>> mapType = new TypeReference<List<Project>>() {};
		List<Project> allProjects = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projects.json").getFile());
		allProjects = mapper.readValue(file, mapType);
		when(projectRepository.findAllActive()).thenReturn(allProjects);
		List<ProjectModel> projectDetails = projectManagerServiceImpl.getProjects();
		Assert.assertNotNull(projectDetails);
		verify(projectRepository,times(1)).findAllActive();
		verifyNoMoreInteractions(projectRepository);
	}
	
	@Test
	public void getProjects_Completed() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<List<Project>> mapType = new TypeReference<List<Project>>() {};
		List<Project> allProjects = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projects_completed.json").getFile());
		allProjects = mapper.readValue(file, mapType);
		when(projectRepository.findAllActive()).thenReturn(allProjects);
		List<ProjectModel> projectDetails = projectManagerServiceImpl.getProjects();
		Assert.assertNotNull(projectDetails);
		verify(projectRepository,times(1)).findAllActive();
		verifyNoMoreInteractions(projectRepository);
	}
	
	@Test
	public void getUserDetailsForProject() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<Project> mapType = new TypeReference<Project>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projectdetails.json").getFile());
		Project project = mapper.readValue(file, mapType);
		Optional<Project> projects = Optional.ofNullable(project);
		when(projectRepository.findById(1)).thenReturn(projects);
		ProjectModel projectModel = projectManagerServiceImpl.getUserDetailsForProject(1);
		Assert.assertNotNull(projectModel);
		verify(projectRepository,times(1)).findById(1);
		verifyNoMoreInteractions(projectRepository);
	}
	
	
	@Test
	public void getTasksForProject() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<List<Task>> mapType = new TypeReference<List<Task>>() {};
		List<Task> allTasks = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("tasks.json").getFile());
		allTasks = mapper.readValue(file, mapType);
		when(taskRepository.getTasksForProject(1)).thenReturn(allTasks);
		List<TaskModel> taskDetails = projectManagerServiceImpl.getTasksForProject(1);
		Assert.assertNotNull(taskDetails);
		verify(taskRepository,times(1)).getTasksForProject(1);
		verifyNoMoreInteractions(taskRepository);
	}
	
	
	@Test
	public void updateTask() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		Task task = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task_update.json").getFile());
		task = mapper.readValue(file, Task.class);
		
		TaskModel taskVO = null;
		File fileTaskModel = new File(classLoader.getResource("task.json").getFile());
		taskVO = mapper.readValue(fileTaskModel, TaskModel.class);
		when(taskRepository.save(task)).thenReturn(task);
		projectManagerServiceImpl.updateTask(taskVO);
	}
	
	@Test
	public void updateTask_Parent() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		Task task = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task_update.json").getFile());
		task = mapper.readValue(file, Task.class);
		
		TaskModel taskVO = null;
		File fileTaskModel = new File(classLoader.getResource("task_parent.json").getFile());
		taskVO = mapper.readValue(fileTaskModel, TaskModel.class);
		when(taskRepository.save(task)).thenReturn(task);
		projectManagerServiceImpl.updateTask(taskVO);
	}
	
	@Test
	public void updateTask_Edit() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<Task> mapType = new TypeReference<Task>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task_update.json").getFile());
		Task task = mapper.readValue(file, mapType);
		Optional<Task> tasks = Optional.ofNullable(task);
		when(taskRepository.findById(1)).thenReturn(tasks);
		
		TaskModel taskVO = null;
		File fileTaskModel = new File(classLoader.getResource("task.json").getFile());
		taskVO = mapper.readValue(fileTaskModel, TaskModel.class);
		projectManagerServiceImpl.updateTask(taskVO);
		Assert.assertNotNull(tasks);
		verify(taskRepository,times(1)).findById(1);
	}
	
	@Test
	public void updateTask_Suspend() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<Task> mapType = new TypeReference<Task>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task_update.json").getFile());
		Task task = mapper.readValue(file, mapType);
		Optional<Task> tasks = Optional.ofNullable(task);
		when(taskRepository.findById(1)).thenReturn(tasks);
		
		TaskModel taskVO = null;
		File fileTaskModel = new File(classLoader.getResource("task_suspend.json").getFile());
		taskVO = mapper.readValue(fileTaskModel, TaskModel.class);
		projectManagerServiceImpl.updateTask(taskVO);
		Assert.assertNotNull(tasks);
		verify(taskRepository,times(1)).findById(1);
	}
	
	@Test
	public void getParentTasks() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<List<ParentTask>> mapType = new TypeReference<List<ParentTask>>() {};
		List<ParentTask> allParentTasks = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("parenttasks.json").getFile());
		allParentTasks = mapper.readValue(file, mapType);
		when(parentTaskRepository.findAll()).thenReturn(allParentTasks);
		List<ParentTaskModel> allParentTasksDetails = projectManagerServiceImpl.getParentTasks();
		Assert.assertNotNull(allParentTasksDetails);
		verify(parentTaskRepository,times(1)).findAll();
		verifyNoMoreInteractions(parentTaskRepository);
	}

	@Test
	public void updateProject() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		Project project = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("project_update.json").getFile());
		project = mapper.readValue(file, Project.class);
		
		ProjectModel projectVO = null;
		File fileProjectModel = new File(classLoader.getResource("project.json").getFile());
		projectVO = mapper.readValue(fileProjectModel, ProjectModel.class);
		when(projectRepository.save(project)).thenReturn(project);
		projectManagerServiceImpl.updateProject(projectVO);
	}
	
	@Test
	public void updateProject_Edit() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {

		TypeReference<Project> mapType = new TypeReference<Project>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("project_update.json").getFile());
		Project project = mapper.readValue(file, mapType);
		Optional<Project> projects = Optional.ofNullable(project);
		when(projectRepository.findById(1)).thenReturn(projects);
		
		ProjectModel projectVO = null;
		File fileUserModel = new File(classLoader.getResource("project_edit.json").getFile());
		projectVO = mapper.readValue(fileUserModel, ProjectModel.class);
		projectManagerServiceImpl.updateProject(projectVO);
		Assert.assertNotNull(projects);
	}
	
	@Test
	public void updateProject_Suspend() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {

		TypeReference<Project> mapType = new TypeReference<Project>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("project_update.json").getFile());
		Project project = mapper.readValue(file, mapType);
		Optional<Project> projects = Optional.ofNullable(project);
		when(projectRepository.findById(1)).thenReturn(projects);
		
		ProjectModel projectVO = null;
		File fileUserModel = new File(classLoader.getResource("project_suspend.json").getFile());
		projectVO = mapper.readValue(fileUserModel, ProjectModel.class);
		projectManagerServiceImpl.updateProject(projectVO);
		Assert.assertNotNull(projects);
	}
	
	@Test
	public void getUsers() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
		List<User> allUsers = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("users.json").getFile());
		allUsers = mapper.readValue(file, mapType);
		when(userRepository.findAllActive()).thenReturn(allUsers);
		List<UserModel> allUsersDetails = projectManagerServiceImpl.getUsers();
		Assert.assertNotNull(allUsersDetails);
	}
	
	@Test
	public void updateUser_Add() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		User user = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user_update.json").getFile());
		user = mapper.readValue(file, User.class);
		
		UserModel userVO = null;
		File fileUserModel = new File(classLoader.getResource("user_add.json").getFile());
		userVO = mapper.readValue(fileUserModel, UserModel.class);
		when(userRepository.save(user)).thenReturn(user);
		projectManagerServiceImpl.updateUser(userVO);
	}
	
	@Test
	public void updateUser_Edit() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<User> mapType = new TypeReference<User>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user_update.json").getFile());
		User user = mapper.readValue(file, mapType);
		Optional<User> users = Optional.ofNullable(user);
		when(userRepository.findById(1)).thenReturn(users);
		
		UserModel userVO = null;
		File fileUserModel = new File(classLoader.getResource("user_edit.json").getFile());
		userVO = mapper.readValue(fileUserModel, UserModel.class);
		projectManagerServiceImpl.updateUser(userVO);
		Assert.assertNotNull(users);
	}
	
	@Test
	public void updateUser_Suspend() throws JsonParseException, JsonMappingException, IOException, ProjectManagerException {
		TypeReference<User> mapType = new TypeReference<User>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user_update.json").getFile());
		User user = mapper.readValue(file, mapType);
		Optional<User> users = Optional.ofNullable(user);
		when(userRepository.findById(1)).thenReturn(users);
		
		UserModel userVO = null;
		File fileUserModel = new File(classLoader.getResource("user_suspend.json").getFile());
		userVO = mapper.readValue(fileUserModel, UserModel.class);
		projectManagerServiceImpl.updateUser(userVO);
		Assert.assertNotNull(users);
	}

}