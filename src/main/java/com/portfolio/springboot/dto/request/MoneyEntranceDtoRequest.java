package com.portfolio.springboot.dto.request;

import com.portfolio.springboot.generic.GenericDtoInsert;
import com.portfolio.springboot.model.MoneyEntrance;
import com.portfolio.springboot.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Getter @Setter
public class MoneyEntranceDtoRequest implements GenericDtoInsert<MoneyEntrance> {
    @PositiveOrZero
    private Double value;
    @NotNull
    private Person owner;
    @NotNull @FutureOrPresent
    private LocalDate entranceDay;


    @Override
    public MoneyEntrance convert() {
        return MoneyEntrance
                .builder()
                .entranceDay(this.entranceDay)
                .owner(this.owner)
                .value(this.value)
                .build();
    }
}
