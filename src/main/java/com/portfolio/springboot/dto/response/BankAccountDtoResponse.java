package com.portfolio.springboot.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.MonthDay;

@Getter @Setter
@Builder
public class BankAccountDtoResponse {
    private Long id;
    private Double amount;
    private String name;
    private String lastNumbers;
    private MonthDay dueDay;
}
