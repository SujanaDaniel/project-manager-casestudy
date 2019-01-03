/**
 * ProjectManagerServiceImpl.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
 * This is the service class which contains the logics used in the Project Manager Application
 *
 */
@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManagerServiceImpl.class); 
	
	private TaskRepository taskRepository;
	
	private ParentTaskRepository parentTaskRepository;
	
	private ProjectRepository projectRepository;
	
	private UserRepository userRepository;
	
	private Mapper dozerMapper;
	
	public ProjectManagerServiceImpl(ProjectRepository projectRepository,
			TaskRepository taskRepository, 
			ParentTaskRepository parentTaskRepository,
			UserRepository userRepository, Mapper dozerMapper) {
		this.projectRepository = projectRepository;
		this.taskRepository = taskRepository;
		this.parentTaskRepository = parentTaskRepository;
		this.userRepository = userRepository;
		this.dozerMapper = dozerMapper;
	}

	@Override
	public List<TaskModel> getTasks() throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside getTasks - start");
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		List<Task> taskList = new ArrayList<Task>();
		try {
			taskList = taskRepository.findAll();
			if(null != taskList && taskList.size() > 0) {
				for (Task task : taskList) {
					TaskModel taskModel = new TaskModel();
					taskModel = dozerMapper.map(task, TaskModel.class);
					if(null != task.getParent()) {
						taskModel.setParentId(task.getParent().getParentId());
						taskModel.setParentTask(task.getParent().getParentTask());
					}
					if(null != task.getProject()) {
						taskModel.setProjectId(task.getProject().getProjectId());
						taskModel.setProjectName(task.getProject().getProject());
						if(null != task.getProject().getUser()) {
							taskModel.setUserId(task.getProject().getUser().getUserId());
							taskModel.setUserName(task.getProject().getUser().getFirstName()+
									" "+task.getProject().getUser().getLastName());
						}
					}
					taskModelList.add(taskModel);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl getTasks::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside getTasks - end");
		return taskModelList;
	}
	
	@Override
	public List<ParentTaskModel> getParentTasks() throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside getParentTasks - start");
		List<ParentTaskModel> parentTaskModelList = new ArrayList<ParentTaskModel>();
		List<ParentTask> parentTaskList = new ArrayList<ParentTask>();
		try {	
			parentTaskList = parentTaskRepository.findAll();
			for (ParentTask parentTask : parentTaskList) {
				ParentTaskModel parentTaskModel = new ParentTaskModel();
				parentTaskModel = dozerMapper.map(parentTask, ParentTaskModel.class);
				parentTaskModelList.add(parentTaskModel);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl getParentTasks::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside getParentTasks - end");
		return parentTaskModelList;
	}

	@Override
	public List<ProjectModel> getProjects() throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside getProjects - start");
		List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
		List<Project> projectList = new ArrayList<Project>();
		try {	
			projectList = projectRepository.findAllActive();
			if(null != projectList && projectList.size() > 0) {
				for (Project project : projectList) {
					ProjectModel projectModel = new ProjectModel();
					projectModel = dozerMapper.map(project, ProjectModel.class);
					if(null != project.getTask() && project.getTask().size() > 0) {
						projectModel.setTotalTaskCount(project.getTask().size());
						for (Task task : project.getTask()) {
							int completedTaskCount = 0;
							if(("Completed").equalsIgnoreCase(task.getStatus())) {
								completedTaskCount++;
							}
							projectModel.setCompletedTaskCount(completedTaskCount);
						}
					}
					projectModelList.add(projectModel);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl getProjects::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside getProjects - end");
		return projectModelList;
	}

	@Override
	public List<UserModel> getUsers() throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside getUsers - start");
		List<UserModel> usersModelList = new ArrayList<UserModel>();
		List<User> usersList = new ArrayList<User>();
		try {
			usersList = userRepository.findAllActive();
			for (User users : usersList) {
				UserModel usersModel = new UserModel();
				usersModel = dozerMapper.map(users, UserModel.class);
				usersModelList.add(usersModel);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl getUsers::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside getUsers - end");
		return usersModelList;
	}
	
	@Override
	public ProjectModel getUserDetailsForProject(int projectId) throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside getUserDetailsForProject - start");
		ProjectModel projectModel = new ProjectModel();
		try {
			Optional<Project> optionalProject = projectRepository.findById(projectId);
			if(optionalProject.isPresent() && null != optionalProject.get()) {
				Project project = optionalProject.get();
				if(null != project && null != project.getUser()){
					projectModel = dozerMapper.map(project, ProjectModel.class);
					projectModel.setUserId(project.getUser().getUserId());
					projectModel.setUserName(project.getUser().getFirstName()+ ' '+project.getUser().getLastName());
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl getUserDetailsForProject::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside getUserDetailsForProject - end");
		return projectModel;
	}
	

	@Override
	public List<TaskModel> getTasksForProject(int projectId) throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside getTasksForProject - start");
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		try {
			List<Task> taskList = taskRepository.getTasksForProject(projectId);
			if(null != taskList && taskList.size() > 0){
				for (Task task : taskList) {
					TaskModel taskModel = new TaskModel();
					taskModel = dozerMapper.map(task, TaskModel.class);
					if(null != task.getParent()) {
						taskModel.setParentTask(task.getParent().getParentTask());
						taskModel.setParentId(task.getParent().getParentId());
					}
					if(null != task.getProject()) {
						taskModel.setProjectId(task.getProject().getProjectId());
						taskModel.setProjectName(task.getProject().getProject());
						if(null != task.getProject().getUser()) {
							taskModel.setUserId(task.getProject().getUser().getUserId());
							taskModel.setUserName(task.getProject().getUser().getFirstName()+
									" "+task.getProject().getUser().getLastName());
						}
					}
					taskModelList.add(taskModel);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl getTasksForProject::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside getTasksForProject - end");
		return taskModelList;
	}

	@Override
	public boolean updateTask(TaskModel taskModel) throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside updateTask - start");
		boolean status = false;
		try{
			if(null != taskModel) {
				if(taskModel.isEnableParentTask()) {
					ParentTask parentTask = new ParentTask();
					parentTask.setParentTask(taskModel.getTask());
					parentTask.setProjectId(taskModel.getProjectId());
					parentTaskRepository.save(parentTask);
					status = true;
				} else {
					Task task = new Task();
					Project project = new Project();
					ParentTask parentTask = new ParentTask();
					Optional<Project> optionalProject = projectRepository.findById(taskModel.getProjectId());
					if(optionalProject.isPresent() && null != optionalProject.get()) {
						project = optionalProject.get();
					}
					if (taskModel.getParentId() != 0) {
						Optional<ParentTask> OptionalParentTask = parentTaskRepository.findById(taskModel.getParentId());
						if(OptionalParentTask.isPresent() && null != OptionalParentTask.get()) {
							parentTask = OptionalParentTask.get();
						}
					}
					Optional<Task> optionalTask = taskRepository.findById(taskModel.getTaskId());
					
					if(optionalTask.isPresent() && null != optionalTask.get()) {
						task = optionalTask.get();
						if(null != task) {
							task = dozerMapper.map(taskModel, Task.class);
							if(null != taskModel.getAction() && taskModel.getAction().equalsIgnoreCase("end")) {
								task.setStatus("Completed");
							} else {
								task.setStatus("Active");
							}
							task.setProject(project);
							task.setParent(parentTask);
							taskRepository.save(task);
						}
					} else {
						task = dozerMapper.map(taskModel, Task.class);
						task.setProject(project);
						task.setParent(parentTask);
						task.setStatus("Active");
						taskRepository.save(task);
					}
					status = true;
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl updateTask::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside updateTask - end");
		return status;
	}

	@Override
	public boolean updateProject(ProjectModel projectModel) throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside updateProject - start");
		boolean status = false;
		try {
			if(null != projectModel) {
				Project project = new Project();
				User user = new User();
				Optional<User> optionalUser = userRepository.findById(projectModel.getUserId());
				if(optionalUser.isPresent() && null != optionalUser.get()) {
					user = optionalUser.get();
				}
				if(null != projectModel.getAction() && projectModel.getAction().equalsIgnoreCase("add")) {
					project = dozerMapper.map(projectModel, Project.class);
					project.setStatus("Active");
					project.setUser(user);
					projectRepository.save(project);
				} else {
					Optional<Project> optionalProject = projectRepository.findById(projectModel.getProjectId());
					if(optionalProject.isPresent() && null != optionalProject.get()) {
						project = optionalProject.get();
						if(null != project) {
							project = dozerMapper.map(projectModel, Project.class);
							if(null != projectModel.getAction() && projectModel.getAction().equalsIgnoreCase("end")) {
								project.setStatus("Completed");
							} else if(null != projectModel.getAction() && projectModel.getAction().equalsIgnoreCase("update")) {
								project.setStatus("Active");
							}
							project.setUser(user);
							projectRepository.save(project);
						}
					}
				}
				status = true;
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl updateProject::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside updateProject - end");
		return status;
	}

	@Override
	public boolean updateUser(UserModel userModel) throws ProjectManagerException {
		LOGGER.info("ProjectManagerServiceImpl - Inside updateUser - start");
		boolean status = false;
		try {
			if(null != userModel) {
				User users = new User();
				if(null != userModel.getAction() && userModel.getAction().equalsIgnoreCase("add")) {
					users = dozerMapper.map(userModel, User.class);
					users.setStatus("Active");
					userRepository.save(users);
				} else {
					Optional<User> optionalTask = userRepository.findById(userModel.getUserId());
					
					if(optionalTask.isPresent() && null != optionalTask.get()) {
						users = optionalTask.get();
						if(null != users) {
							users = dozerMapper.map(userModel, User.class);
							if(null != userModel.getAction() && userModel.getAction().equalsIgnoreCase("end")) {
								users.setStatus("Completed");
							} else if(null != userModel.getAction() && userModel.getAction().equalsIgnoreCase("update")) {
								users.setStatus("Active");
							}
							userRepository.save(users);
						}
					}
				}
				status = true;
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in ProjectManagerServiceImpl updateUser::: " + ex.getMessage());
			throw new ProjectManagerException("TE001","Technical Error", 500);
		}
		LOGGER.info("ProjectManagerServiceImpl - Inside updateUser - end");
		return status;
	}

}
