package com.portfolio.springboot.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

import com.portfolio.springboot.dto.response.ListDTO;
import com.portfolio.springboot.dto.response.PersonDtoResponse;
import com.portfolio.springboot.generic.GenericEntity;

@Entity
@Getter @Setter
public class Person implements Serializable, GenericEntity<Person, PersonDtoResponse> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Boolean active = true;

    public Person() {}

    public Person(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

	@Override
	public PersonDtoResponse toDtoResponse() {
		return PersonDtoResponse.builder().active(active).id(id).name(name).build();
	}

	@Override
	public ListDTO toListDTO() {
		return ListDTO.builder().name(name).value(id).build();
	}
}
