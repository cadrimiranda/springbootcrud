package com.portfolio.springboot.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.portfolio.springboot.dto.response.ListDTO;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ResponseEntity<List<ListDTO>> listAll(){
        List<ListDTO> all = service.getAll().stream().map(GenericEntity::toListDTO).collect(Collectors.toList());
        return ResponseEntity.ok(all);
    }

    public void setRepository(GenericRepository<T, DtoResponse> repository) {
        this.service.setRepository(repository);
    }
}
