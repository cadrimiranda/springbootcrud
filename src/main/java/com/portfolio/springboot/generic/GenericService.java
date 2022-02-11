package com.portfolio.springboot.generic;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Setter
public abstract class GenericService<
        T extends GenericEntity<T, DtoResponse>,
        DtoResponse,
        DtoInsert extends GenericDtoInsert<T>,
        DtoUpdate extends GenericDtoUpdate<T>
        >
{

    private GenericRepository<T, DtoResponse> repository;

    public GenericService() {
    }

    public Page<T> getPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<T> get(Long id){
        return repository.findById(id);
    }

    @Transactional
    public boolean update(Long id, DtoUpdate updater){
        Optional<T> dbDomain = get(id);
        dbDomain.ifPresent(updater::update);

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
