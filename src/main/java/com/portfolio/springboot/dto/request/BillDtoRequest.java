package com.portfolio.springboot.dto.request;

import com.portfolio.springboot.model.Bill;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter @Setter
public class BillDtoRequest {
    @NotNull @NotEmpty @Length(min = 5)
    private String description;

    @NotNull @NotEmpty @Min(value = 1, message = "Value should not be less than zero")
    private Float value;

    @NotNull @NotEmpty
    private Boolean recurrent = false;

    @NotNull @NotEmpty @Future
    private LocalDateTime due;

    public Bill convert() {
        return new Bill(this.description, this.value, this.recurrent, this.due);
    }

    public void update(Bill bill) {
        bill.setDescription(this.description);
        bill.setDue(this.due);
        bill.setRecurrent(this.recurrent);
        bill.setValue(this.value);
    }
}
