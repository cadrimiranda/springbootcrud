package com.portfolio.springboot.generic;

public interface GenericDtoInsert<Entity> {
    Entity convert();
}
