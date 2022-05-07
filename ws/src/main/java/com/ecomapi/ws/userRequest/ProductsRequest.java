package com.ecomapi.ws.userRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsRequest {

    private String titleArticle;

    private String descriptionArticle;

    private double price;

    private String idCategoryClient;

    private int reduction;

    private String blogingArticle;

    // FOR ORDERS COMMANDE
    private long qte;

    private long idClientProducts;
}
