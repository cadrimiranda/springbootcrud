package com.portfolio.springboot.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ListDTO {
    private Long value;
    private String name;
}
