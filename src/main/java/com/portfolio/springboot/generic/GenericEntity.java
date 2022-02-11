package com.portfolio.springboot.generic;

public interface GenericEntity<T, DTOResponse> {
    Long getId();
    DTOResponse toDtoResponse();
}
