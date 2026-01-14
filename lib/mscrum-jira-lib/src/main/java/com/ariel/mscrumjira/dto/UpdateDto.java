package com.ariel.mscrumjira.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateDto {
    
    @NotBlank
    @Size(max=50)
    private String title;

    @NotBlank
    @Size(max=250)
    private String description; 
      
    @Min(1)
    @Max(10) 
    private Integer priority; 
   
    @Positive   
    private Integer estimate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getEstimate() {
        return estimate;
    }  
    
}
