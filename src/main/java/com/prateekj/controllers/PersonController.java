package com.prateekj.controllers;

import com.prateekj.model.Person;
import com.prateekj.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/users")
public class PersonController {

  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService){
    this.personService = personService;
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public ResponseEntity<Void> addUser(@RequestBody Person person){
    personService.savePerson(person);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }
}
