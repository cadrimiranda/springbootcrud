package com.portfolio.springboot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.springboot.model.Bill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter @Setter
@AllArgsConstructor
public class BillDtoResponse {
    private Long id;
    private String name;
    private String description;
    private Float value;
    private Boolean recurrent;
    private LocalDate due;
    private Boolean disabled;
    @JsonProperty("owner_id")
    private Long ownerId;
    private String ownerName;
    
	public BillDtoResponse(Bill bill) {
		super();
		this.id = bill.getId();
		this.name = bill.getName();
		this.description = bill.getDescription();
		this.value = bill.getValue();
		this.recurrent = bill.getRecurrent();
		this.due = bill.getDue();
		this.disabled = bill.getDisabled();
		this.ownerId = bill.getOwner().getId();
		this.ownerName = bill.getOwner().getName();
	}
    

}
