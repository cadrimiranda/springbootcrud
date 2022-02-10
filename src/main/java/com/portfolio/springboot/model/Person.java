package com.portfolio.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Boolean disable = false;

    public Person() {}

    public Person(String name, Boolean disable) {
        this.name = name;
        this.disable = disable;
    }
}
