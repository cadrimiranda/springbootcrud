package com.portfolio.springboot.dto.request;

import com.portfolio.springboot.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PersonDtoRequest {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String name;

    @NotNull
    private Boolean active = true;

    public PersonDtoRequest(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

    public Person convert() {
        return new Person(this.name, this.active);
    }

    public void update(Person person) {
        person.setActive(this.active);
        person.setName(this.name);
    }
}
