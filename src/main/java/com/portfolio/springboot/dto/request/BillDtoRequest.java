package com.portfolio.springboot.dto.request;

import com.portfolio.springboot.generic.GenericDtoInsert;
import com.portfolio.springboot.model.Bill;
import com.portfolio.springboot.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter @Setter
public class BillDtoRequest implements GenericDtoInsert<Bill> {
    @NotNull @NotEmpty @Length(min = 5)
    private String name;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String description;

    @NotNull @Min(value = 1, message = "Value should not be less than zero")
    private Float value;

    @NotNull
    private Boolean recurrent = false;

    @NotNull @Future
    private LocalDate due;

    @NotNull
    private Person owner;

    public Bill convert() {
        return new Bill(this.description, this.name, this.value, this.recurrent, this.due, this.getOwner());
    }
}
