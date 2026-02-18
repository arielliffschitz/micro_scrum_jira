-- Proyectos
INSERT INTO project (id, project_key, name, description, state, created_at, created_by, updated_at, updated_by)
VALUES
  (UNHEX(REPLACE(UUID(), '-', '')), 1, 'Project Alpha', 'First test project', 'ACTIVE', NOW(), 'test', NOW(), 'test'),
  (UNHEX(REPLACE(UUID(), '-', '')), 2, 'Project Beta', 'Second test project', 'ACTIVE', NOW(), 'test', NOW(), 'test'),
  (UNHEX(REPLACE(UUID(), '-', '')), 3, 'Project Gamma', 'Third test project', 'ARCHIVED', NOW(), 'test', NOW(), 'test');

-- Recuperar los IDs generados para asignar a sprints
SET @project1_id = (SELECT id FROM project WHERE project_key=1);
SET @project2_id = (SELECT id FROM project WHERE project_key=2);

-- Sprints
INSERT INTO sprint (id, sprint_key, project_id, team_key, sprint_state, start_date, end_date, created_at, created_by, updated_at, updated_by)
VALUES
  (UNHEX(REPLACE(UUID(), '-', '')), 101, @project1_id, 'TeamA', 'PLANNED', NOW(), NOW() + INTERVAL 14 DAY, NOW(), 'test', NOW(), 'test'),
  (UNHEX(REPLACE(UUID(), '-', '')), 102, @project1_id, 'TeamB', 'ACTIVE', NOW(), NOW() + INTERVAL 14 DAY, NOW(), 'test', NOW(), 'test'),
  (UNHEX(REPLACE(UUID(), '-', '')), 201, @project2_id, 'TeamC', 'ACTIVE', NOW(), NOW() + INTERVAL 14 DAY, NOW(), 'test', NOW(), 'test');
