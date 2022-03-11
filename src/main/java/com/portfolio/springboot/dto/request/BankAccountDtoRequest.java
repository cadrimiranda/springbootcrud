package com.portfolio.springboot.dto.request;

import com.portfolio.springboot.generic.GenericDtoInsert;
import com.portfolio.springboot.model.BankAccount;
import com.portfolio.springboot.model.Person;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.MonthDay;

public class BankAccountDtoRequest implements GenericDtoInsert<BankAccount> {
    @NotNull
    private Person person;

    @NotNull @NotEmpty @NotEmpty @Length(min = 5)
    private String name;

    @NotNull @NotEmpty @NotEmpty @Length(min = 4, max = 4)
    private String lastNumbers;

    @NotNull
    private MonthDay dueDay;

    @Override
    public BankAccount convert() {
        return BankAccount
                .builder()
                .name(this.name)
                .build();
    }
}
