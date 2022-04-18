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
    private Boolean active = true;

    public Person() {}

    public Person(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }
}
