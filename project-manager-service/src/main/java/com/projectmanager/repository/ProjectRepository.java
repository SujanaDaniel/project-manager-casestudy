/**
 * ProjectRepository.java
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
import org.springframework.stereotype.Repository;

import com.projectmanager.entity.Project;

/**
 * This is the Repository for the PROJECT table
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

	@Query("SELECT p FROM Project p WHERE p.status = 'Active'")
	List<Project> findAllActive();
	
}
