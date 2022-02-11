package com.portfolio.springboot.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public abstract class GenericService<
        T extends GenericEntity<T, DtoResponse>,
        DtoResponse,
        DtoInsert extends GenericDtoInsert<T>,
        DtoUpdate extends GenericDtoUpdate<T>
        >
{

    private final GenericRepository<T, DtoResponse> repository;

    public GenericService(GenericRepository<T, DtoResponse> repository) {
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<T> get(Long id){
        return repository.findById(id);
    }

    @Transactional
    public boolean update(DtoUpdate updateRequest){
        Optional<T> dbDomain = get(updateRequest.getId());
        dbDomain.ifPresent(updateRequest::update);

        return dbDomain.isPresent();
    }

    @Transactional
    public T create(DtoInsert newDomain){
        T dbDomain = newDomain.convert();
        return repository.save(dbDomain);
    }

    @Transactional
    public void delete(Long id){
        //check if object with this id exists
        get(id);
        repository.deleteById(id);
    }
}
