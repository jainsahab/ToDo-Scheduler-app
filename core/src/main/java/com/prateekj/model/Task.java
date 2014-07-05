package com.prateekj.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"work"})
@Table(name = "task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  @JsonBackReference
  User user;

  String work;
}
