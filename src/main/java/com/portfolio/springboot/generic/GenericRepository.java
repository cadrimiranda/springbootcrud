package com.portfolio.springboot.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity<T, DtoResponse>, DtoResponse> extends JpaRepository<T, Long> {
}
