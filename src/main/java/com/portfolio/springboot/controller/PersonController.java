package com.portfolio.springboot.controller;

import com.portfolio.springboot.dto.request.PersonDtoRequest;
import com.portfolio.springboot.dto.response.ListDTO;
import com.portfolio.springboot.dto.response.PersonDtoResponse;
import com.portfolio.springboot.dto.update.PersonDtoUpdate;
import com.portfolio.springboot.generic.GenericController;
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

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController extends GenericController<Person, PersonDtoResponse, PersonDtoRequest, PersonDtoUpdate> {

	@Autowired
    private PersonRepository personRepository;
	
    public PersonController() {
		super("persons");
	}

    @PostConstruct
    public void init() {
        this.setRepository(this.personRepository);
    }

    @GetMapping("/getall")
    public Page<PersonDtoResponse> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pagination
    ) {
        return this.findAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDtoResponse> getById(@PathVariable Long id) {
        return this.getOne(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PersonDtoResponse> saveOne(
            @RequestBody @Valid PersonDtoRequest personRequest,
            UriComponentsBuilder uriBuilder
    ) {
        return this.saveOne(personRequest, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<?> edit(@PathVariable Long id, @RequestBody @Valid PersonDtoUpdate personRequest) {
        return this.update(id, personRequest);
    }

    @GetMapping("/listall")
    public  ResponseEntity<List<ListDTO>> listItems() {
        return this.listAll();
    }
}
