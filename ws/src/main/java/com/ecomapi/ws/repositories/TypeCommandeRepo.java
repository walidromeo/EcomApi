package com.ecomapi.ws.repositories;

import com.ecomapi.ws.entities.TypeCommandeEntity;
import org.springframework.data.repository.CrudRepository;

public interface TypeCommandeRepo extends CrudRepository<TypeCommandeEntity, Long> {
    TypeCommandeEntity findById(long id);
}