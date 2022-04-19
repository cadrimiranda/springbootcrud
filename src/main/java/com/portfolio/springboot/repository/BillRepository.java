package com.portfolio.springboot.repository;

import com.portfolio.springboot.dto.response.BillDtoResponse;
import com.portfolio.springboot.generic.GenericRepository;
import com.portfolio.springboot.model.Bill;

import java.util.List;

public interface BillRepository extends GenericRepository<Bill, BillDtoResponse> {
    List<Bill> findAllByOwnerId(Long id);
}
