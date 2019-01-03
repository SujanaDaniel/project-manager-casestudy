/**
 * TaskRepository.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectmanager.entity.Task;

/**
 * This is the Repository for the TASK table
 *
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	@Query(value = "SELECT t FROM Task t WHERE t.project.projectId = :projectId")
	List<Task> getTasksForProject(@Param("projectId") int projectId);
	
}
