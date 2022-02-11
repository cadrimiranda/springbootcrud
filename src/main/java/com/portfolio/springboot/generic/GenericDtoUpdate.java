package com.portfolio.springboot.generic;

public interface GenericDtoUpdate<T> {
    void update(T entity);
}
