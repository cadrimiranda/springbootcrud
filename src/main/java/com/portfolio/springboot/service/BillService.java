package com.portfolio.springboot.service;

import org.springframework.stereotype.Service;

import com.portfolio.springboot.dto.request.BillDtoRequest;
import com.portfolio.springboot.dto.response.BillDtoResponse;
import com.portfolio.springboot.dto.update.BillDtoUpdate;
import com.portfolio.springboot.generic.GenericService;
import com.portfolio.springboot.model.Bill;

@Service
public class BillService extends GenericService<Bill, BillDtoResponse, BillDtoRequest, BillDtoUpdate> {

}
