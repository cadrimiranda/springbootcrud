package com.portfolio.springboot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class MoneyEntranceDtoResponse {
    private Long id;
    private Double value;
    @JsonProperty("entrance_day")
    private LocalDate entranceDay;

    public MoneyEntranceDtoResponse(Long id, Double value, LocalDate entranceDay) {
        this.id = id;
        this.value = value;
        this.entranceDay = entranceDay;
    }
}
