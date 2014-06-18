package com.prateekj.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"work"})
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  Person person;

  String work;
}
