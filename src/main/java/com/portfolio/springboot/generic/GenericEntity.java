package com.portfolio.springboot.generic;

public interface GenericEntity<T, DTOResponse> {
    void update(T source);

    Long getId();


    DTOResponse toDtoResponse();
}
