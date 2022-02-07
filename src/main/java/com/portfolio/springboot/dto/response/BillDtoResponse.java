package com.portfolio.springboot.dto.response;

import com.portfolio.springboot.model.Bill;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BillDtoResponse {
    private Long id;
    private String description;
    private Float value;
    private Boolean recurrent;
    private LocalDate due;
    private Boolean disabled;

    public BillDtoResponse(Bill bill) {
        this.id = bill.getId();
        this.description = bill.getDescription();
        this.value = bill.getValue();
        this.recurrent = bill.getRecurrent();
        this.due = bill.getDue();
        this.disabled = bill.getDisabled();
    }
}
