package com.portfolio.springboot.repository;

import com.portfolio.springboot.dto.response.MonthDtoResponse;
import com.portfolio.springboot.generic.GenericRepository;
import com.portfolio.springboot.model.Month;

public interface MonthRepository extends GenericRepository<Month, MonthDtoResponse> {
}
