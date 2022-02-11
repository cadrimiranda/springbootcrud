package com.portfolio.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.springboot.dto.response.MoneyEntranceDtoResponse;
import com.portfolio.springboot.generic.GenericEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
public class MoneyEntrance implements Serializable, GenericEntity<MoneyEntrance, MoneyEntranceDtoResponse> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private Double value;

    @NotNull
    @FutureOrPresent
    @JsonProperty("entrance_day")
    private LocalDate entranceDay;

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Person owner;

    @Override
    public Long getId() {
        return this.id;
    }

    public MoneyEntrance(Long id, Double value, LocalDate entranceDay, Person owner) {
        this.id = id;
        this.value = value;
        this.entranceDay = entranceDay;
        this.owner = owner;
    }

    public MoneyEntrance() {
    }

    @Override
    public MoneyEntranceDtoResponse toDtoResponse() {
        return new MoneyEntranceDtoResponse(this.id, this.value, this.entranceDay);
    }
}
