package com.portfolio.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private LocalDateTime due;
    @Column
    private LocalDateTime creation = LocalDateTime.now();
    @Column
    private Boolean disabled = false;

    public Bill() {
    }

    public Bill(String description, Float value, Boolean recurrent, LocalDateTime due) {
        this.description = description;
        this.value = value;
        this.recurrent = recurrent;
        this.due = due;
    }
}
