package com.portfolio.springboot.controller;

import com.portfolio.springboot.dto.request.BillDtoRequest;
import com.portfolio.springboot.dto.response.BillDtoResponse;
import com.portfolio.springboot.dto.update.BillDtoUpdate;
import com.portfolio.springboot.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.portfolio.springboot.repository.BillRepository;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillRepository billRepository;

    @GetMapping("/getall")
    public Page<BillDtoResponse> getAll(
            @PageableDefault(sort = "due", direction = Sort.Direction.ASC) Pageable pagination
    ) {
        return billRepository.findAll(pagination).map(BillDtoResponse::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDtoResponse> getById(@PathVariable Long id) {
        Optional<Bill> bill = billRepository.findById(id);
        return bill.map(value -> ResponseEntity.ok(new BillDtoResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BillDtoResponse> saveOne(
            @RequestBody @Valid BillDtoRequest billRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Bill bill = billRequest.convert();
        billRepository.save(bill);

        URI uri = uriBuilder.path("/bill/{id}").buildAndExpand(bill.getId()).toUri();
        return ResponseEntity.created(uri).body(new BillDtoResponse(bill));
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<?> editOne(@PathVariable Long id, @RequestBody @Valid BillDtoUpdate billRequest) {
        if (billRequest.getDisabled() == Boolean.FALSE) {
            return new ResponseEntity<>("You can't change to enable an disabled bill", HttpStatus.BAD_REQUEST);
        }

        Optional<Bill> billOptional = billRepository.findById(id);
        if (billOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Bill bill = billOptional.get();
        billRequest.update(bill);
        return ResponseEntity.ok(new BillDtoResponse(bill));
    }
}
