package com.ariel.mscrumjira.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductCreateDto (

    @NotBlank
    @Size(max=50)
    String title,  

    @Size(max=250)
    String description, 

    @NotNull  
    @Min(1)
    @Max(10) 
     Integer priority,

    @NotNull
    @Positive   
    Integer estimate,
    
    @NotNull
    Integer projectKey

){}
