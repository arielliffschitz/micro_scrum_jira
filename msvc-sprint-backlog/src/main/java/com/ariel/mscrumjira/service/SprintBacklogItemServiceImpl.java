package com.ariel.mscrumjira.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.client.ProductBacklogFeignClient;
import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.repository.SprintBacklogItemRepository;

@Service
public class SprintBacklogItemServiceImpl implements SprintBacklogItemService {

    private final ProductBacklogFeignClient client;
    private final SprintBacklogItemRepository repository;

    public SprintBacklogItemServiceImpl(ProductBacklogFeignClient client, SprintBacklogItemRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintBacklogItemDto> findAll() {
        return ((List<SprintBacklogItem>) repository.findAll())
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SprintBacklogItemDto> findById(UUID id) {
        return repository.findById(id).map(this::mapToDto);
    }

    @Override
    @Transactional
    public Optional<SprintBacklogItemDto> updateState(UUID id, TaskState taskState) {
        return repository.findById(id)
                .map(dao -> {
                    dao = actualizeTaskStateAndDate(taskState, dao);
                    return mapToDto(repository.save(dao));
                });
    }

    

    @Override
    @Transactional
    public void moveBackToProduct(UUID sprintBacklogItemId) {
        repository.findById(sprintBacklogItemId).ifPresent(daoSprint -> {
            client.create(mapFromSprintToProductDto(daoSprint));
            repository.deleteById(sprintBacklogItemId);
        });
    }

    // --- Mapping Methods ---
    private ProductBacklogItemDto mapFromSprintToProductDto(SprintBacklogItem daoSprint) {
        return new ProductBacklogItemDto(
                daoSprint.,
                daoSprint.getTitle(),
                daoSprint.getDescription(),
                daoSprint.getPriority(),
                daoSprint.getEstimate(),
                daoSprint.getCreatedBy(),
                daoSprint.getCreatedAt()
        );
    }

    private SprintBacklogItem mapFromProductDtoToSprintDao(ProductBacklogItemDto productDto) {
        return new SprintBacklogItem(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getPriority(),
                productDto.getEstimate(),
                TaskState.PENDING
        );
    }

    private ProductBacklogItemDto findProductById(UUID productBacklogId) {
        return client.findProductById(productBacklogId);
    }

    private void deleteProductById(UUID productBacklogId) {
        client.deleteProductById(productBacklogId);
    }

    private SprintBacklogItemDto mapToDto(SprintBacklogItem daoSprint) {
        return new SprintBacklogItemDto(
                daoSprint.getSprintBacklogId(),
                daoSprint.getProductBacklogId(),
                daoSprint.getTitle(),
                daoSprint.getDescription(),
                daoSprint.getPriority(),
                daoSprint.getEstimate(),
                daoSprint.getTaskState(),
                daoSprint.getStartDate(),
                daoSprint.getEndDate(),
                daoSprint.getCreatedBy(),
                daoSprint.getCreatedAt()
        );
    }

    private SprintBacklogItem actualizeTaskStateAndDate(TaskState taskState, SprintBacklogItem sprintBacklogItem) {
        if (sprintBacklogItem.getTaskState() == TaskState.PENDING) {
            if (taskState == TaskState.IN_PROGRESS) {
                sprintBacklogItem.setTaskState(taskState);
                sprintBacklogItem.setStartDate(LocalDateTime.now());
            } else if (taskState == TaskState.DONE) {
                sprintBacklogItem.setTaskState(taskState);
                sprintBacklogItem.setEndDate(LocalDateTime.now());
            }
        } else if (sprintBacklogItem.getTaskState() == TaskState.IN_PROGRESS && taskState == TaskState.DONE) {
            sprintBacklogItem.setTaskState(taskState);
            sprintBacklogItem.setEndDate(LocalDateTime.now());
        }
        return sprintBacklogItem;
    }
}
