package com.portfolio.springboot.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.portfolio.springboot.dto.response.BankAccountDtoResponse;
import com.portfolio.springboot.generic.GenericRepository;
import com.portfolio.springboot.model.BankAccount;

@Component
public interface BankRepository extends GenericRepository<BankAccount, BankAccountDtoResponse> {
	List<BankAccount> findAllByPersonId(Long id);
}
