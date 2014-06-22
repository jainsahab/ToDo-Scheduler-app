package com.prateekj.controllers;

import com.prateekj.model.User;
import com.prateekj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/users")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public ResponseEntity<Void> addUser(@RequestBody User user){
    userService.saveUser(user);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }
}
