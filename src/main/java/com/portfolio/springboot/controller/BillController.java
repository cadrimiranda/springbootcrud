package com.portfolio.springboot.controller;

import com.portfolio.springboot.dto.request.BillDtoRequest;
import com.portfolio.springboot.dto.response.BillDtoResponse;
import com.portfolio.springboot.dto.update.BillDtoUpdate;
import com.portfolio.springboot.generic.GenericController;
import com.portfolio.springboot.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.portfolio.springboot.repository.BillRepository;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bills")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BillController extends GenericController<Bill, BillDtoResponse, BillDtoRequest, BillDtoUpdate> {
	@Autowired
	private BillRepository billRepository;
	
    public BillController() {
		super("bills");
	}

    @PostConstruct
    public void init() {
        this.setRepository(this.billRepository);
    }

    @GetMapping("/getall")
    public Page<BillDtoResponse> getAll(
            @PageableDefault(sort = "due", direction = Sort.Direction.ASC) Pageable pagination
    ) {
        return this.findAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDtoResponse> getById(@PathVariable Long id) {
    	return this.getOne(id);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public ResponseEntity<?> saveOne(
            @RequestBody @Valid BillDtoRequest billRequest,
            UriComponentsBuilder uriBuilder
    ) {
    	return this.saveOne(billRequest, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<?> editOne(@PathVariable Long id, @RequestBody @Valid BillDtoUpdate billRequest) {
        if (billRequest.getDisabled() == Boolean.FALSE) {
            return new ResponseEntity<>("You can't change to enable an disabled bill", HttpStatus.BAD_REQUEST);
        }

        return this.update(id, billRequest);
    }

    @GetMapping("/byowner/{userid}")
    public ResponseEntity<List<BillDtoResponse>> getBillsByUser(@PathVariable Long userid) {
        List<BillDtoResponse> bills = billRepository.findAllByOwnerId(userid).stream().map(BillDtoResponse::new).toList();
        return ResponseEntity.ok(bills);
    }
}
