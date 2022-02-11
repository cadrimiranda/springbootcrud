package com.portfolio.springboot.controller;

import com.portfolio.springboot.dto.request.MonthDtoRequest;
import com.portfolio.springboot.dto.response.MonthDtoResponse;
import com.portfolio.springboot.dto.update.MonthDtoUpdate;
import com.portfolio.springboot.generic.GenericController;
import com.portfolio.springboot.model.Month;
import com.portfolio.springboot.repository.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@ControllerAdvice
@RequestMapping("/months")
public class MonthController extends GenericController<Month, MonthDtoResponse, MonthDtoRequest, MonthDtoUpdate> {
    @Autowired
    private MonthRepository monthRepository;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        System.out.println(e.toString());
        return ResponseEntity.internalServerError().build();
    }

    public MonthController() {
        super("months");
    }

    @PostConstruct
    public void init() {
        this.setRepository(this.monthRepository);
    }

    @GetMapping("/getall")
    public Page<MonthDtoResponse> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pagination
    ) {
        return this.findAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthDtoResponse> getById(@PathVariable Long id) {
        return this.getOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public ResponseEntity<?> saveOne(
            @RequestBody @Valid MonthDtoRequest monthRequest,
            UriComponentsBuilder uriBuilder
    ) {
        return this.create(monthRequest, uriBuilder);
    }


    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<?> editOne(@PathVariable Long id, @RequestBody @Valid MonthDtoUpdate monthDtoUpdate) {
        return this.update(id, monthDtoUpdate);
    }
}
