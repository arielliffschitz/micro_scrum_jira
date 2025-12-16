INSERT INTO `db_mscrum_jira`.`product_backlog_items` 
(`title`, `description`, `priority`, `estimate`, `created_by`, `created_at`) 
VALUES
('User Authentication', 'Implement login and token-based authentication', 1, 5, 'admin', NOW()),
('Create Product Backlog', 'Set up initial product backlog structure', 2, 3, 'admin', NOW()),
('Define DTOs', 'Create DTO classes for backlog items', 3, 2, 'admin', NOW()),
('Implement Service Layer', 'Service layer for CRUD operations on backlog items', 2, 4, 'admin', NOW()),
('Create REST Controller', 'Expose REST endpoints for backlog item operations', 1, 3, 'admin', NOW());
