package com.prateekj.repositories;

import com.prateekj.model.Person;
import com.prateekj.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

  @Query("SELECT t from Task t where t.person = :person")
  List<Task> findByPerson(@Param("person") Person person);
}
