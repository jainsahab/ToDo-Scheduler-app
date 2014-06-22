package com.prateekj.services;

import com.prateekj.model.Person;
import com.prateekj.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public void savePerson(Person person) {
    personRepository.save(person);
  }
}
