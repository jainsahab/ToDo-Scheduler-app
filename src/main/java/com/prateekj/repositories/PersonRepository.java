package com.prateekj.repositories;


import com.prateekj.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

  Person findByName(String personName);
}
