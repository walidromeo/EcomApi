package com.ecomapi.ws.services;

import com.ecomapi.ws.entities.CategorieEntity;
import com.ecomapi.ws.shared.dto.CategoryDto;
import com.ecomapi.ws.userRequest.CategoryRequest;

import java.util.List;

public interface CategorieService {

    CategoryDto save(CategoryDto category);
    List<CategoryDto> findAll();
    CategorieEntity findCategoryByName(String name);
    CategorieEntity findCategoryById(Long id);
    List<CategorieEntity> getAllCategories();
    Boolean existsByNomCategory(String name);
    void removeCategory(CategorieEntity category);

    CategoryDto updateCategory(String categorieId, CategoryRequest categoryRequest);

    void deletedArticle(String clientId);
}
