package com.ecomapi.ws.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeProductsResponse {

    private int id;


    private ProductsResponse products;


    private CommandeResponse commande;

}
