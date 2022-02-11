package com.portfolio.springboot.dto.response;

import com.portfolio.springboot.model.Month;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MonthDtoResponse {
    private Long id;
    private String name;
    private Boolean disabled;

    public MonthDtoResponse(Long id, String name, Boolean disabled) {
        this.id = id;
        this.name = name;
        this.disabled = disabled;
    }

    public MonthDtoResponse(Month month) {
        this.id = month.getId();
        this.name = month.getName();
        this.disabled = month.getDisabled();
    }
}
