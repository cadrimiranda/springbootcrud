package com.portfolio.springboot.generic;

import com.portfolio.springboot.dto.response.ListDTO;

public interface GenericEntity<T, DTOResponse> {
    Long getId();
    DTOResponse toDtoResponse();
    
    ListDTO toListDTO();
}
