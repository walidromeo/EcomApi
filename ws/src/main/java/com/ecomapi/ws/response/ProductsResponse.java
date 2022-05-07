package com.ecomapi.ws.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponse {

    private String titleArticle;

    private String idClientProducts;

    private double price;

    private ImageResponse imageProduct;

    private long qte;
    private CategoryResponse categoryProduct;

}
