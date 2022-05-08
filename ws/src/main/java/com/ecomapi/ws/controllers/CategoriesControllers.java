package com.ecomapi.ws.controllers;

import com.ecomapi.ws.response.CategoryResponse;
import com.ecomapi.ws.response.StockErrorMessage;
import com.ecomapi.ws.services.CategorieService;
import com.ecomapi.ws.shared.dto.CategoryDto;
import com.ecomapi.ws.userRequest.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/categorie") // localhost:8084/api/categorie
@RequiredArgsConstructor
public class CategoriesControllers {

    private final CategorieService categorieService;
    @PostMapping(path = "/add_categorie")
    public ResponseEntity<CategoryResponse> createArticle(@RequestBody @Valid CategoryRequest categoryRequest , Principal authentication) throws Exception {
        if (categoryRequest.getNomCategory().isEmpty())
            throw new Exception(StockErrorMessage.MISSING_REQUIRED_FILED.getErrorMessage());
        ModelMapper modelMapper = new ModelMapper();
        CategoryDto categoryDto = modelMapper.map(categoryRequest,CategoryDto.class);
        log.info("authentication getName {}",authentication.getName());
        CategoryDto addToDb = categorieService.save(categoryDto);
        log.info("Stock DTO : {}",addToDb);
        CategoryResponse categoryResponse =modelMapper.map(addToDb, CategoryResponse.class);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllArticle() {
        ModelMapper modelMapper= new ModelMapper();
        // Les articles kaynin bzzf So Ghadi n7athum f Array
        // Response diali ghadi n7atu de Type Array
        List<CategoryResponse> allCategorie = new ArrayList<>();
        // Hnaya ghadi ndawz Service diali VALUE LI GHADI TJI MN URL
        List<CategoryDto> allArticleFromStock = categorieService.findAll();
        // db ghadi tjini response 3la cde type DTO Khasni n7awalha De type Response U
        // 3ndi Array Ki ghadi ndir ??
        // Ghadi ndir boucle li ghadi ndir des interations 3la dkchi li jani mn service
        // u dkchi li ghadi iwslni n7atu f Response
        for (CategoryDto categoryDto : allArticleFromStock) {
            CategoryResponse categoryResponse =modelMapper.map(categoryDto, CategoryResponse.class);
            allCategorie.add(categoryResponse);
        }
        // Sf Array Ra 3mer b les Informations
        return new ResponseEntity<List<CategoryResponse>>(allCategorie,HttpStatus.OK);
    }


    @PutMapping(path = "/update_categories/{categorieId}")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody @Valid CategoryRequest categoryRequest ,@PathVariable String categorieId) {
        // Khasni ndir Get LCh7al kayn f la base u ndir 3lih Modification b dkchi li
        // ghadi nisft f JSON
        // So awl haja hiya get ldkchi li ayji mn la base
        ModelMapper modelMapper = new ModelMapper();
        CategoryDto categoryDto = categorieService.updateCategory(categorieId,categoryRequest);
        CategoryResponse categoryResponse = modelMapper.map(categoryDto, CategoryResponse.class);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.ACCEPTED);
    }
    @DeleteMapping(path = "/delete_category/{clientId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable String clientId) {
        categorieService.deletedArticle(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
