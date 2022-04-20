package com.portfolio.springboot.service;

import org.springframework.stereotype.Service;

import com.portfolio.springboot.dto.request.MoneyEntranceDtoRequest;
import com.portfolio.springboot.dto.response.MoneyEntranceDtoResponse;
import com.portfolio.springboot.generic.GenericService;
import com.portfolio.springboot.model.MoneyEntrance;
import com.portfolio.springboot.utils.DtoUpdate;

@Service
public class MoneyEntranceService extends
		GenericService<MoneyEntrance, MoneyEntranceDtoResponse, MoneyEntranceDtoRequest, DtoUpdate<MoneyEntrance>> {

}
