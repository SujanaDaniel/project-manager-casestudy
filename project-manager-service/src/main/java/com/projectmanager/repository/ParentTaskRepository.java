/**
 * ParentTaskRepository.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmanager.entity.ParentTask;

/**
 * This is the Repository for the PARENT_TASK table
 *
 */
@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer>{

}
