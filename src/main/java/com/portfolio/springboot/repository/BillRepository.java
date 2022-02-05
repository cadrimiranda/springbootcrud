package com.portfolio.springboot.repository;

import com.portfolio.springboot.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
