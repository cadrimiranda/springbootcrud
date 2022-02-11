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
    private String path;

    public GenericController(String path) {
        this.path = path;
        this.service = new GenericService<T, DtoResponse, DtoInsert, DtoUpdate>() {};
    }

    public Page<DtoResponse> findAll(Pageable pageable){
        Page<T> list = service.getPage(pageable);

        return service.getPage(pageable).map(GenericEntity::toDtoResponse);
    }

    public ResponseEntity<DtoResponse> getOne(Long id){
        Optional<T> dbDomain = service.get(id);
        return dbDomain.map(value -> ResponseEntity.ok(value.toDtoResponse())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<DtoUpdate> update(Long id, DtoUpdate updated){
        if (service.update(id, updated)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build() ;
    }

    public ResponseEntity<?> create(DtoInsert created,
                                    UriComponentsBuilder uriBuilder
    ){
        T dboDomain = service.create(created);
        URI uri = uriBuilder.path("/{path}/{id}").buildAndExpand(this.path, dboDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(dboDomain.toDtoResponse());
    }

    public ResponseEntity<String> delete(Long id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }

    public void setRepository(GenericRepository<T, DtoResponse> repository) {
        this.service.setRepository(repository);
    }
}
