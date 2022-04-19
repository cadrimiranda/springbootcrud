package com.portfolio.springboot.controller;

import com.portfolio.springboot.dto.request.MoneyEntranceDtoRequest;
import com.portfolio.springboot.dto.response.MoneyEntranceDtoResponse;
import com.portfolio.springboot.generic.GenericController;
import com.portfolio.springboot.model.MoneyEntrance;
import com.portfolio.springboot.repository.MoneyEntranceRepository;
import com.portfolio.springboot.utils.DtoUpdate;
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
import java.util.List;

@RestController
@RequestMapping("/moneyentrance")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MoneyEntranceController extends GenericController<
        MoneyEntrance,
        MoneyEntranceDtoResponse,
        MoneyEntranceDtoRequest,
        DtoUpdate<MoneyEntrance>
        >
{
    @Autowired
    private MoneyEntranceRepository moneyEntranceRepository;

    public MoneyEntranceController() {
        super("moneyentrance");
    }

    @PostConstruct
    public void init() {
        this.setRepository(this.moneyEntranceRepository);
    }

    @GetMapping("/getall")
    public Page<MoneyEntranceDtoResponse> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pagination
    ) {
        return this.findAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoneyEntranceDtoResponse> getById(@PathVariable Long id) {
        return this.getOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public ResponseEntity<?> saveOne(
            @RequestBody @Valid MoneyEntranceDtoRequest moneyEntranceDtoRequest,
            UriComponentsBuilder uriBuilder
    ) {
        return this.create(moneyEntranceDtoRequest, uriBuilder);
    }

    @GetMapping("/byowner/{userid}")
    public ResponseEntity<List<MoneyEntranceDtoResponse>> getBillsByUser(@PathVariable Long userid) {
        List<MoneyEntranceDtoResponse> bills = moneyEntranceRepository
                .findAllByOwnerId(userid)
                .stream()
                .map(MoneyEntrance::toDtoResponse)
                .toList();
        return ResponseEntity.ok(bills);
    }
}
