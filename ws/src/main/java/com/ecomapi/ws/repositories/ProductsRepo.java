package com.ecomapi.ws.repositories;

import com.ecomapi.ws.entities.CategorieEntity;
import com.ecomapi.ws.entities.ProductsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductsRepo extends PagingAndSortingRepository<ProductsEntity, Long> {

    ProductsEntity findByIdClientProducts(String idClient);

    List<ProductsEntity> findByCategoryProduct(CategorieEntity categorieEntity);
}
