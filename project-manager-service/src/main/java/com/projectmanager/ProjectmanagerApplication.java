/**
 * ProjectmanagerApplication.java
 * 
 * Date    		 Version     Developer
 * ----------    --------    -------------
 * 28/12/2018	 1.0		 Sujana Daniel Christopher	    
 * 
 */
package com.projectmanager;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.projectmanager.config.CORSFilter;

@SpringBootApplication
public class ProjectmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectmanagerApplication.class, args);
	}
	
	/**
	 * DozerMapper to map the properties from the entity to the model objects
	 * @return DozerBeanMapper
	 */
	@Bean
	public DozerBeanMapper dozerMapper() {
		DozerBeanMapper dozerBean = new DozerBeanMapper(); 
		return dozerBean;
	}
	
	/**
	 * To set CORS and add Url patterns
	 * @return FilterRegistrationBean
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FilterRegistrationBean corsFilter() {
		
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new CORSFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
		
	}
}
