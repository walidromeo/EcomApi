package com.ecomapi.ws.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeProductsDto {

    private int id;


    private ProductsDto products;


    private CommandeProductsDto commande;

}
