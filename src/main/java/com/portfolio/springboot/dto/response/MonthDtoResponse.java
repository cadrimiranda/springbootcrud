package com.portfolio.springboot.dto.response;

import com.portfolio.springboot.model.Month;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthDtoResponse {
    private Long id;
    private String name;

    public MonthDtoResponse(Month month) {
        this.id = month.getId();
        this.name = month.getName();
    }
}
