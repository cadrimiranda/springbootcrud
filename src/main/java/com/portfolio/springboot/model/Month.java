package com.portfolio.springboot.model;

import com.portfolio.springboot.dto.response.MonthDtoResponse;
import com.portfolio.springboot.generic.GenericEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter @Setter
public class Month implements Serializable, GenericEntity<Month, MonthDtoResponse> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Boolean disabled;

    public Month() {}

    public Month(Long id, String name, Boolean disabled) {
        this.id = id;
        this.name = name;
        this.disabled = disabled;
    }

    @Override
    public MonthDtoResponse toDtoResponse() {
        return new MonthDtoResponse(this.id, this.name, this.disabled);
    }
}
