package com.prateekj.repositories;


import com.prateekj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findById(Integer userId);
}
