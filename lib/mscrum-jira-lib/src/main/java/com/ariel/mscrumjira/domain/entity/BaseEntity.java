package com.ariel.mscrumjira.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

@MappedSuperclass
public abstract class BaseEntity {
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

   @PrePersist
    protected void onCreate(){
       if (createdAt == null)   this.createdAt = LocalDateTime.now(); 
      
       if (createdBy == null)  this.createdBy = "John Doe" +
        java.util.concurrent.ThreadLocalRandom.current().nextInt(1, 101);     
    }
    public String getCreatedBy() {
        return createdBy;
    }   

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }   
               
}
