/**
 * ProjectManagerControllerTest.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanager.model.ParentTaskModel;
import com.projectmanager.model.ProjectModel;
import com.projectmanager.model.TaskModel;
import com.projectmanager.model.UserModel;
import com.projectmanager.service.ProjectManagerService;

/**
 * This is the test class for the Project Manager Controller
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProjectManagerController.class)
public class ProjectManagerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectManagerService projectManagerServiceImpl;

	@Test
	public void getProjects() throws Exception {

		ProjectModel projectModel = getProjects_Json();
		List<ProjectModel> projects = Arrays.asList(projectModel);
		given(projectManagerServiceImpl.getProjects()).willReturn(projects);
		mockMvc.perform(get("/projectmanager/get/projects").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].project", is(projectModel.getProject())));
	}

	@Test
	public void getUsers() throws Exception {

		UserModel userModel = getUsers_Json();
		List<UserModel> users = Arrays.asList(userModel);
		given(projectManagerServiceImpl.getUsers()).willReturn(users);
		mockMvc.perform(get("/projectmanager/get/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].userId", is(userModel.getUserId())));
	}

	@Test
	public void getTasks() throws Exception {

		TaskModel taskModel = getTasks_Json();
		List<TaskModel> tasks = Arrays.asList(taskModel);
		given(projectManagerServiceImpl.getTasks()).willReturn(tasks);
		mockMvc.perform(get("/projectmanager/get/tasks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].task", is(taskModel.getTask())));

	}

	@Test
	public void getParentTasks() throws Exception {

		ParentTaskModel taskModel = getParentTasks_Json();
		List<ParentTaskModel> tasks = Arrays.asList(taskModel);
		given(projectManagerServiceImpl.getParentTasks()).willReturn(tasks);
		mockMvc.perform(get("/projectmanager/get/parenttasks").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].parentTask", is(taskModel.getParentTask())));

	}

	@Test
	public void getUserDetailsForProject() throws Exception {
		
		ProjectModel projectModel = getProjects_Json();
		given(projectManagerServiceImpl.getUserDetailsForProject(1)).willReturn(projectModel);
		mockMvc.perform(get("/projectmanager/get/user/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("project", is(projectModel.getProject())));
		
	}
	
	@Test
	public void getTasksForProject() throws Exception {
		
		TaskModel taskModel = getTasks_Json();
		List<TaskModel> tasks = Arrays.asList(taskModel);
		given(projectManagerServiceImpl.getTasksForProject(1)).willReturn(tasks);
		mockMvc.perform(get("/projectmanager/get/task/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].task", is(taskModel.getTask())));
		
	}
	
	@Test
	public void getTasks_BadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/projectmanager/get/tasks/s").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404));
	}
	
	@Test
	public void getParentTasks_BadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/projectmanager/get/parenttasks/s").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404));
	}
	
	
	@Test
	public void getUsers_BadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/projectmanager/get/users/s").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404));
	}
	
	@Test
	public void getProjects_BadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/projectmanager/get/projects/s").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404));
	}

	@Test
	public void updateUser_Add() throws Exception {
		
		UserModel userModel = updateUser_Add_Json();
		given(projectManagerServiceImpl.updateUser(userModel)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/projectmanager/update/user").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void updateProject_Edit() throws Exception {
		ProjectModel projectModel = getProjects_Json();

		given(projectManagerServiceImpl.updateProject(projectModel)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();

		mockMvc.perform(post("/projectmanager/update/project").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(projectModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void updateProject_Add() throws Exception {
		
		ProjectModel projectModel = updateProject_Add_Json();
		given(projectManagerServiceImpl.updateProject(projectModel)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/projectmanager/update/project").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(projectModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void updateTask_Add() throws Exception {
		
		TaskModel taskModel = updateTask_Add_Json();
		given(projectManagerServiceImpl.updateTask(taskModel)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/projectmanager/update/task").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(taskModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	public void updateTask_Edit() throws Exception {
		
		TaskModel taskModel = getTasks_Json();
		given(projectManagerServiceImpl.updateTask(taskModel)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/projectmanager/update/task").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(taskModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	} 

	@Test
	public void updateTask_Suspend() throws Exception {
		
		TaskModel taskModel = updateTask_Suspend_Json();
		given(projectManagerServiceImpl.updateTask(taskModel)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/projectmanager/update/task").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(taskModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	private ProjectModel getProjects_Json() throws JsonParseException, JsonMappingException, IOException {
		
		TypeReference<ProjectModel> mapObj = new TypeReference<ProjectModel>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("project.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		ProjectModel projectModel = mapper.readValue(file, mapObj);
		return projectModel;
		
	}

	private ProjectModel updateProject_Add_Json() throws JsonParseException, JsonMappingException, IOException {
		
		TypeReference<ProjectModel> mapObj = new TypeReference<ProjectModel>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("project_add.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		ProjectModel projectModel = mapper.readValue(file, mapObj);
		return projectModel;
		
	}

	private UserModel getUsers_Json() throws JsonParseException, JsonMappingException, IOException {
		
		TypeReference<UserModel> mapObj = new TypeReference<UserModel>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		UserModel userModel = mapper.readValue(file, mapObj);
		return userModel;
		
	}

	private UserModel updateUser_Add_Json() throws JsonParseException, JsonMappingException, IOException {
		
		TypeReference<UserModel> mapObj = new TypeReference<UserModel>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user_add.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		UserModel userModel = mapper.readValue(file, mapObj);
		return userModel;
		
	}

	private TaskModel getTasks_Json() throws JsonParseException, JsonMappingException, IOException {
		
		TypeReference<TaskModel> mapObj = new TypeReference<TaskModel>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		TaskModel taskModel = mapper.readValue(file, mapObj);
		return taskModel;
		
	}

	private TaskModel updateTask_Add_Json() throws JsonParseException, JsonMappingException, IOException {
		
		TypeReference<TaskModel> mapObj = new TypeReference<TaskModel>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task_add.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		TaskModel taskModel = mapper.readValue(file, mapObj);
		return taskModel;
		
	}

	private TaskModel updateTask_Suspend_Json() throws JsonParseException, JsonMappingException, IOException {
		
		TypeReference<TaskModel> mapObj = new TypeReference<TaskModel>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task_suspend.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		TaskModel taskModel = mapper.readValue(file, mapObj);
		return taskModel;
		
	}

	private ParentTaskModel getParentTasks_Json() throws JsonParseException, JsonMappingException, IOException {
		
		TypeReference<ParentTaskModel> mapObj = new TypeReference<ParentTaskModel>() {};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("parenttask.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		ParentTaskModel parentTaskModel = mapper.readValue(file, mapObj);
		return parentTaskModel;

	}

}
