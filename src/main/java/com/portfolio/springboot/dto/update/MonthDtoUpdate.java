package com.portfolio.springboot.dto.update;

import com.portfolio.springboot.generic.GenericDtoUpdate;
import com.portfolio.springboot.model.Month;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthDtoUpdate implements GenericDtoUpdate<Month> {
    private String name;
    private Boolean disabled;

    @Override
    public void update(Month entity) {
        entity.setName(this.name);
        entity.setDisabled(this.disabled);
    }
}
