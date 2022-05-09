package com.portfolio.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

import com.portfolio.springboot.dto.response.ListDTO;
import com.portfolio.springboot.dto.response.PersonDtoResponse;
import com.portfolio.springboot.generic.GenericEntity;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @Builder.Default
    private Boolean active = true;

	@Override
	public PersonDtoResponse toDtoResponse() {
		return PersonDtoResponse.builder().active(active).id(id).name(name).build();
	}

	@Override
	public ListDTO toListDTO() {
		return ListDTO.builder().name(name).value(id).build();
	}
}
