package com.portfolio.springboot.dto.request;

import com.portfolio.springboot.generic.GenericDtoInsert;
import com.portfolio.springboot.model.Month;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MonthDtoRequest implements GenericDtoInsert<Month> {
    private String name;
    private Boolean disabled;

    @Override
    public Month convert() {
        Month month = Month.builder().name(this.name).disabled(this.disabled).build();
        System.out.println(month.getName());
        return month;
    }
}
