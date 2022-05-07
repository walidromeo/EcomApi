package com.ecomapi.ws.shared.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto implements Serializable {

    private static final long serialVersionUID = 6510352208691607844L;

    private String titleArticle;

    private String descriptionArticle;

    private String idClientProducts;

    private double price;

    private long qte ;

    private CategoryDto categoryProduct;

    private int reduction;

    private ImageDto imageProduct;

    private String blogingArticle;

}
