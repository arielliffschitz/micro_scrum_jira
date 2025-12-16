CREATE TABLE db_mscrum_jira.product_backlog_items ( id INT NOT NULL AUTO_INCREMENT, 
title VARCHAR(45) NOT NULL, 
description VARCHAR(255) NULL,
 priority INT NOT NULL, 
 estimate INT NOT NULL, 
created_by VARCHAR(45) NOT NULL, 
created_at DATETIME NOT NULL, 
PRIMARY KEY (id)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;