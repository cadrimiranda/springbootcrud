package com.portfolio.springboot.generic;

public interface GenericDtoInsert<T> {
    T convert();

    Long getId();
}
