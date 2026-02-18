DROP TABLE IF EXISTS sprint;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
    id BINARY(16) PRIMARY KEY,
    project_key INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    state VARCHAR(50) NOT NULL,
    created_at DATETIME,
    created_by VARCHAR(255),
    updated_at DATETIME,
    updated_by VARCHAR(255)
);

CREATE TABLE sprint (
    id BINARY(16) PRIMARY KEY,
    sprint_key INT NOT NULL UNIQUE AUTO_INCREMENT,
    project_id BINARY(16) NOT NULL,
    team_key VARCHAR(255) NOT NULL ,
    sprint_state VARCHAR(50) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    created_at DATETIME,
    created_by VARCHAR(255),
    updated_at DATETIME,
    updated_by VARCHAR(255),
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES project(id)
);
