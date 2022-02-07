package com.portfolio.springboot.dto.update;

import com.portfolio.springboot.model.Bill;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BillDtoUpdate {
    private Boolean disabled = false;

    public void update(Bill bill) {
        bill.setDisabled(this.disabled);
    }
}
