package com.ecomapi.ws.userRequest;


import com.ecomapi.ws.response.ProductsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsCommandeRequest {

    private String idProducts;

    private String firstName;

    private String lastname;

    private String city;

    private String number;

    private Date dateCommande;

    private long typeCommande;

    private List<ProductsResponse> productsRequest;
}
