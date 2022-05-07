package com.ecomapi.ws.repositories;

import com.ecomapi.ws.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<ImageEntity, Long> {
   ImageEntity findByIdBrowserPhoto(String idClientPhoto);
}
