package com.ecomapi.ws.userRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeRequest {

   private String idProducts;

   private String firstName;

   private String lastname;

   private String city;

   private String number;

   private Date dateCommande;

   private long typeCommande;

   private List<ProductsRequest> productsRequest;
}

   
