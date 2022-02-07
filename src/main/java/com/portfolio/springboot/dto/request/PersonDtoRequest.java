package com.portfolio.springboot.dto.request;

import com.portfolio.springboot.model.Bill;
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
    private Boolean disabled = false;

    public PersonDtoRequest(String name, Boolean disabled) {
        this.name = name;
        this.disabled = disabled;
    }

    public Person convert() {
        return new Person(this.name, this.disabled);
    }

    public void update(Person person) {
        person.setDisable(this.disabled);
        person.setName(this.name);
    }
}
