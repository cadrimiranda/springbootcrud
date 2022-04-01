package com.portfolio.springboot.repository;

import java.util.List;

import com.portfolio.springboot.dto.response.BankAccountDtoResponse;
import com.portfolio.springboot.generic.GenericRepository;
import com.portfolio.springboot.model.BankAccount;

public interface BankRepository extends GenericRepository<BankAccount, BankAccountDtoResponse> {
	List<BankAccount> findAllByOwnerId(Long id);
}
