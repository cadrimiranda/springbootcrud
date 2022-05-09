package com.portfolio.springboot.dto.update;

import java.time.LocalDate;

import com.portfolio.springboot.generic.GenericDtoUpdate;
import com.portfolio.springboot.model.Bill;
import com.portfolio.springboot.model.Person;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BillDtoUpdate implements GenericDtoUpdate<Bill> {
    private String name;
    private String description;
    private Float value;
    private Boolean recurrent;
    private LocalDate due;
    private Boolean disabled;
    private Long ownerId;

    public void update(Bill bill) {
        bill.setName(name);
        bill.setDescription(description);
        bill.setValue(value);
        bill.setRecurrent(recurrent);
        bill.setDue(due);
        bill.setDisabled(disabled);
        bill.setOwner(Person.builder().id(ownerId).build());
    }
}
