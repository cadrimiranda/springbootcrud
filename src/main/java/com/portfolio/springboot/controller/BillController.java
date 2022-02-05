package com.portfolio.springboot.controller;

import com.portfolio.springboot.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.portfolio.springboot.repository.BillRepository;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillRepository billRepository;

    @GetMapping("/getall")
    public List<Bill> getAll() {
        return billRepository.findAll();
    }
}
