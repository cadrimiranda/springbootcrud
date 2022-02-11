package com.portfolio.springboot.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

public abstract class GenericController<
        T extends GenericEntity<T,DtoResponse>,
        DtoResponse,
        DtoInsert extends GenericDtoInsert<T>,
        DtoUpdate extends GenericDtoUpdate<T>
        >
{

    private final GenericService<T, DtoResponse, DtoInsert, DtoUpdate> service;

    public GenericController(GenericRepository<T, DtoResponse> repository) {
        this.service = new GenericService<T, DtoResponse, DtoInsert, DtoUpdate>(repository) {};
    }

    public Page<DtoResponse> getPage(Pageable pageable){
        Page<T> list = service.getPage(pageable);

        return service.getPage(pageable).map(GenericEntity::toDtoResponse);
    }

    public ResponseEntity<DtoResponse> getOne(Long id){
        Optional<T> dbDomain = service.get(id);
        return dbDomain.map(value -> ResponseEntity.ok(value.toDtoResponse())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<DtoUpdate> update(DtoUpdate updated){
        if (service.update(updated)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> create(DtoInsert created,
                                    UriComponentsBuilder uriBuilder
    ){
        T dboDomain = service.create(created);
        URI uri = uriBuilder.path("/bills/{id}").buildAndExpand(dboDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(dboDomain.toDtoResponse());
    }

    public ResponseEntity<String> delete(Long id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
