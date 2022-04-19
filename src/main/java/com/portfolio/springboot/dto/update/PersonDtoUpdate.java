package com.portfolio.springboot.dto.update;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.portfolio.springboot.generic.GenericDtoUpdate;
import com.portfolio.springboot.model.Person;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonDtoUpdate implements GenericDtoUpdate<Person> {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String name;

    @NotNull
    private Boolean active = true;

    public void update(Person person) {
        person.setActive(this.active);
        person.setName(this.name);
    }

}
