package com.portfolio.springboot.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class PersonDtoResponse {
    private Long id;
    private String name;
    @Builder.Default
    private Boolean active = true;
}
