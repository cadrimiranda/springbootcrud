package com.portfolio.springboot.service;

import org.springframework.stereotype.Service;

import com.portfolio.springboot.dto.request.PersonDtoRequest;
import com.portfolio.springboot.dto.response.PersonDtoResponse;
import com.portfolio.springboot.dto.update.PersonDtoUpdate;
import com.portfolio.springboot.generic.GenericService;
import com.portfolio.springboot.model.Person;

@Service
public class PersonService extends GenericService<Person, PersonDtoResponse, PersonDtoRequest, PersonDtoUpdate> {

}
