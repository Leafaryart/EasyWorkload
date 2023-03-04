-- Script for Database
-- Ray Rafael Abenido

CREATE TABLE task_list (
    task_id INTEGER PRIMARY KEY ,
    title TEXT NOT NULL,
    description TEXT
);

INSERT INTO task_list VALUES (1, "Example Title", "Some description and data here");
INSERT INTO task_list VALUES (2, "Example Title", "Some description and data here");
INSERT INTO task_list VALUES (3, "Example Title", "Some description and data here");
INSERT INTO task_list VALUES (4, "Example Title", "Some description and data here");
INSERT INTO task_list VALUES (5, "Example Title", "Some description and data here");
SELECT * FROM task_list;