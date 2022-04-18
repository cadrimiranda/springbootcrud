package com.portfolio.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private Float value;
    @Column
    private Boolean recurrent;
    @Column
    private LocalDate due;
    @Column
    private LocalDate creation = LocalDate.now();
    @Column
    private Boolean disabled = false;
    @ManyToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Person owner;

    public Bill() {
    }

    public Bill(String description, Float value, Boolean recurrent, LocalDate due, Person owner) {
        this.description = description;
        this.value = value;
        this.recurrent = recurrent;
        this.due = due;
        this.owner = owner;
    }
}
