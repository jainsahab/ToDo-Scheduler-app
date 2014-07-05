ALTER TABLE task
ADD CONSTRAINT FK_task_person_person_id FOREIGN KEY (person_id) REFERENCES person(id);