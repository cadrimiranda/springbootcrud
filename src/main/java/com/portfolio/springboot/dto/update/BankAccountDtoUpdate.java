package com.portfolio.springboot.dto.update;

import com.portfolio.springboot.generic.GenericDtoUpdate;
import com.portfolio.springboot.model.BankAccount;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.MonthDay;

@Getter
@Setter
public class BankAccountDtoUpdate implements GenericDtoUpdate<BankAccount> {
    @NotNull
    @NotEmpty
    @NotEmpty @Length(min = 5)
    private String name;

    @NotNull
    private MonthDay dueDay;

    @Override
    public void update(BankAccount entity) {
        this.name = entity.getName();
        this.dueDay = entity.getDueDay();
    }
}
