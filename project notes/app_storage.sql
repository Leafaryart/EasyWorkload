CREATE TABLE task_list (
    taskID INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    date_added TEXT,
    deadline TEXT,
    subject TEXT,
    is_complete TEXT,
    is_late TEXT,
    is_subtask_of TEXT
);

CREATE TABLE subject (
	subjectID INTEGER PRIMARY KEY,
   	subject_title TEXT NOT NULL,
    instructor_name TEXT NOT NULL
)