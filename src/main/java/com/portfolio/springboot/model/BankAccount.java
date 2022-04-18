package com.portfolio.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.springboot.dto.response.BankAccountDtoResponse;
import com.portfolio.springboot.generic.GenericEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.MonthDay;

@Entity
@Builder
@Getter @Setter
public class BankAccount implements Serializable, GenericEntity<BankAccount, BankAccountDtoResponse> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;
    private Double amount;
    private String name;
    private String lastNumbers;
    private MonthDay dueDay;

    @Override
    public BankAccountDtoResponse toDtoResponse() {
        return BankAccountDtoResponse
                .builder()
                .id(this.id)
                .amount(this.amount)
                .lastNumbers(this.lastNumbers)
                .name(this.name)
                .dueDay(this.dueDay)
                .build();
    }
}
