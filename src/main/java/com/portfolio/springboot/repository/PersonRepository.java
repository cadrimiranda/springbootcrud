package com.portfolio.springboot.repository;

import com.portfolio.springboot.dto.response.PersonDtoResponse;
import com.portfolio.springboot.generic.GenericRepository;
import com.portfolio.springboot.model.Person;

public interface PersonRepository extends GenericRepository<Person, PersonDtoResponse> {
}
