package com.portfolio.springboot.controller;

import com.portfolio.springboot.repository.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@RequestMapping("/months")
public class MonthController {
    @Autowired
    private MonthRepository monthRepository;
}
