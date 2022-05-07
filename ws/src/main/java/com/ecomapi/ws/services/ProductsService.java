package com.ecomapi.ws.services;

import com.ecomapi.ws.entities.ProductsEntity;
import com.ecomapi.ws.shared.dto.*;

import java.util.List;

public interface ProductsService {

	ProductsDto addProductsTodb(ProductsDto productsDto,String email,String idClientPhoto,String idCategoryClient);

	List<ProductsDto> getArticle(int page, int limit, String article);
	ProductsEntity getArticleFromDb(String idClient);

	ProductsDto updatedProducts(String clientId, ProductsEntity productsDto, ProductsDto newProductsRequest, String name,String categorie);

	void deletedArticle(String clientId);

    List<ProductsDto> getArticleByCategory(String clientId);

	List<CategoryDto>  getProductByCategory();

    List<CategoryDtoProductsId> getProductIdByCategory();
}