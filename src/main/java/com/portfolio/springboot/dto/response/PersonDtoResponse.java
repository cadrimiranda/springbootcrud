package com.portfolio.springboot.dto.response;

import com.portfolio.springboot.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonDtoResponse {
    private Long id;
    private String name;
    private Boolean disable = false;

    public PersonDtoResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.disable = person.getDisable();
    }
}