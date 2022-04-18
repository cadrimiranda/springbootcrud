package com.portfolio.springboot.controller;

import com.portfolio.springboot.dto.request.PersonDtoRequest;
import com.portfolio.springboot.dto.response.PersonDtoResponse;
import com.portfolio.springboot.model.Person;
import com.portfolio.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/getall")
    public Page<PersonDtoResponse> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pagination
    ) {
        return personRepository.findAll(pagination).map(PersonDtoResponse::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDtoResponse> getById(@PathVariable Long id) {
        Optional<Person> persons = personRepository.findById(id);
        return persons.map(value -> ResponseEntity.ok(new PersonDtoResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PersonDtoResponse> saveOne(
            @RequestBody @Valid PersonDtoRequest personRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Person person = personRequest.convert();
        personRepository.save(person);

        URI uri = uriBuilder.path("/persons/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonDtoResponse(person));
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<?> editOne(@PathVariable Long id, @RequestBody @Valid PersonDtoRequest personRequest) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Person person = personOptional.get();
        personRequest.update(person);
        return ResponseEntity.ok(new PersonDtoResponse(person));
    }
}
