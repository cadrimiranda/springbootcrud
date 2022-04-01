package com.portfolio.springboot.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.portfolio.springboot.dto.request.BankAccountDtoRequest;
import com.portfolio.springboot.dto.response.BankAccountDtoResponse;
import com.portfolio.springboot.dto.update.BankAccountDtoUpdate;
import com.portfolio.springboot.generic.GenericController;
import com.portfolio.springboot.model.BankAccount;
import com.portfolio.springboot.repository.BankRepository;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController extends GenericController<
	BankAccount,
	BankAccountDtoResponse,
	BankAccountDtoRequest,
	BankAccountDtoUpdate
> {
	@Autowired
	private BankRepository bankRepository;

    public BankAccountController() {
        super("bankaccount");
    }

    @PostConstruct
    public void init() {
        this.setRepository(this.bankRepository);
    }

    @GetMapping("/getall")
    public Page<BankAccountDtoResponse> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pagination
    ) {
        return this.findAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDtoResponse> getById(@PathVariable Long id) {
        return this.getOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public ResponseEntity<?> saveOne(
            @RequestBody @Valid BankAccountDtoRequest bankAccountDtoRequest,
            UriComponentsBuilder uriBuilder
    ) {
        return this.create(bankAccountDtoRequest, uriBuilder);
    }

    @GetMapping("/byowner/{userid}")
    public ResponseEntity<List<BankAccountDtoResponse>> getBillsByUser(@PathVariable Long userid) {
        List<BankAccountDtoResponse> bills = bankRepository
                .findAllByOwnerId(userid)
                .stream()
                .map(BankAccount::toDtoResponse)
                .toList();
        return ResponseEntity.ok(bills);
    }
}
