package com.portfolio.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.portfolio.springboot.dto.response.BillDtoResponse;
import com.portfolio.springboot.dto.response.ListDTO;
import com.portfolio.springboot.generic.GenericEntity;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Bill implements Serializable, GenericEntity<Bill, BillDtoResponse> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Float value;
    @Column
    private Boolean recurrent;
    @Column
    private LocalDate due;
    @Column
    private LocalDate creation = LocalDate.now();
    @Column
    private Boolean disabled = false;
    @ManyToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Person owner;

    public Bill() {
    }

    public Bill(String description, String name, Float value, Boolean recurrent, LocalDate due, Person owner) {
        this.description = description;
        this.value = value;
        this.recurrent = recurrent;
        this.due = due;
        this.owner = owner;
        this.name = name;
    }

	@Override
	public BillDtoResponse toDtoResponse() {
		return new BillDtoResponse(this);
	}

	@Override
	public ListDTO toListDTO() {
		return ListDTO.builder().name(name).value(id).build();
	}
}
