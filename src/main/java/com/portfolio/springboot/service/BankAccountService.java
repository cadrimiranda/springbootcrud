package com.portfolio.springboot.service;

import org.springframework.stereotype.Service;

import com.portfolio.springboot.dto.request.BankAccountDtoRequest;
import com.portfolio.springboot.dto.response.BankAccountDtoResponse;
import com.portfolio.springboot.dto.update.BankAccountDtoUpdate;
import com.portfolio.springboot.generic.GenericService;
import com.portfolio.springboot.model.BankAccount;

@Service
public class BankAccountService
		extends GenericService<BankAccount, BankAccountDtoResponse, BankAccountDtoRequest, BankAccountDtoUpdate> {

}
