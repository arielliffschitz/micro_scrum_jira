package com.ariel.mscrumjira.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;

@Service
public class SprintTaskStateServiceImpl implements SprintTaskStateService{

    private static final Map<TaskState, Set<TaskState>> TASK_STATE_VALID = Map.of(TaskState.PENDING,    Set.of(TaskState.IN_PROGRESS, TaskState.BLOCKED),
                                                                         TaskState.IN_PROGRESS,Set.of(TaskState.DONE, TaskState.BLOCKED) ,
                                                                         TaskState.BLOCKED,    Set.of(TaskState.IN_PROGRESS),
                                                                         TaskState.DONE,       Set.of()
                                                                        );       

    @Override
    public SprintBacklogItemDto applyTransition(TaskState next, SprintBacklogItemDto dto)  {
       if (isValid( dto.getTaskState(),  next)){
          dto.setTaskState(next);
          dto = takeAction(next, dto);
       }
       else { throw new RuntimeException("Invalid transition ") ;}
       return  dto; 
    }

    private boolean isValid(TaskState current, TaskState next) {
        return TASK_STATE_VALID.get(current).contains(next);        
    }

    private SprintBacklogItemDto takeAction(TaskState next, SprintBacklogItemDto dto) {        
        switch (next) {
            case TaskState.IN_PROGRESS : dto.setStartDate(LocalDateTime.now());
                
                break;
            case TaskState.DONE : dto.setEndDate(LocalDateTime.now());
                
                break;
            case TaskState.BLOCKED : System.out.println("TODO message to boss ");
                
                break;
            default:
                break;
        }
       return dto;
    }

}
