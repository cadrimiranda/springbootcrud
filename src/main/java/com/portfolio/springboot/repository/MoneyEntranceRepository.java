package com.portfolio.springboot.repository;

import com.portfolio.springboot.dto.response.MoneyEntranceDtoResponse;
import com.portfolio.springboot.generic.GenericRepository;
import com.portfolio.springboot.model.MoneyEntrance;

public interface MoneyEntranceRepository extends GenericRepository<MoneyEntrance, MoneyEntranceDtoResponse> {
}
