package com.ariel.mscrumjira.dto;

import com.ariel.mscrumjira.domain.enums.ProjectState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectUpdateDto {

	 @NotBlank
	 @Size(max=50)
     private String name;

	 @Size(max=250)
     private String description;
	    
	 private ProjectState state;

	 public ProjectUpdateDto() {		
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public String getDescription() {
		 return description;
	 }

	 public void setDescription(String description) {
		 this.description = description;
	 }

	 public ProjectState getState() {
		 return state;
	 }

	 public void setState(ProjectState state) {
		 this.state = state;
	 }

	
	 
	 
}
