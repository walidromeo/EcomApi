package com.ecomapi.ws.serviceImp;

import com.ecomapi.ws.entities.CategorieEntity;
import com.ecomapi.ws.repositories.CategorieRepo;
import com.ecomapi.ws.services.CategorieService;
import com.ecomapi.ws.shared.Utils;
import com.ecomapi.ws.shared.dto.CategoryDto;
import com.ecomapi.ws.userRequest.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CategorieServiceImp implements CategorieService {

    private final CategorieRepo categoryRepository;
    private  final Utils utils;


    @Override
    public CategoryDto save(CategoryDto category) {

        ModelMapper modelMapper = new ModelMapper();
        CategorieEntity categorieEntity = new CategorieEntity();
        if(categoryRepository.existsByNomCategory(category.getNomCategory())){
            throw new RuntimeException("Categorie Exixt");
        }
        else{
            categorieEntity.setIdCategoryClient(utils.generateStringId(20));
            categorieEntity.setNomCategory(category.getNomCategory());
            categoryRepository.save(categorieEntity);
        }
        CategoryDto categoryDto = modelMapper.map(categorieEntity,CategoryDto.class);
        return categoryDto;
    }
    @Override
    public List<CategoryDto> findAll(){
      List<CategorieEntity> categorieEntities =  categoryRepository.findAll();
      ModelMapper modelMapper = new ModelMapper();
      List<CategoryDto> categoryDtos = new ArrayList<>();
        for (CategorieEntity categorieEntity:categorieEntities) {
            CategoryDto categoryDto = modelMapper.map(categorieEntity,CategoryDto.class);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }
    @Override
    public CategorieEntity findCategoryByName(String name){
        return categoryRepository.findCategoryByNomCategory(name);
    }
    @Override
    public CategorieEntity findCategoryById(Long id){
        return categoryRepository.findCategoryById(id);
    }
    @Override
    public List<CategorieEntity> getAllCategories(){
        return categoryRepository.findAll();
    }
    @Override
    public Boolean existsByNomCategory(String name) {
        return categoryRepository.existsByNomCategory(name);
    }
    @Override
    public void removeCategory(CategorieEntity category){
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto updateCategory(String categorieId, CategoryRequest categoryRequest) {
        ModelMapper modelMapper = new ModelMapper();
        CategorieEntity categorieEntity = categoryRepository.findCategoryByIdCategoryClient(categorieId);
        if(categoryRequest.getNomCategory().isEmpty())throw  new RuntimeException("LE CHAMP EST VIDE");
        else
        {
            categorieEntity.setNomCategory(categoryRequest.getNomCategory());
            categoryRepository.save(categorieEntity);

        }
        CategoryDto categoryDto = modelMapper.map(categorieEntity,CategoryDto.class);

        return categoryDto;
    }

    @Override
    public void deletedArticle(String clientId) {
        CategorieEntity categorieEntity = categoryRepository.findCategoryByIdCategoryClient(clientId);
        categoryRepository.delete(categorieEntity);
    }
}

