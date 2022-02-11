package com.portfolio.springboot.repository;

import com.portfolio.springboot.model.Month;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepository extends JpaRepository<Month, Long> {
}
