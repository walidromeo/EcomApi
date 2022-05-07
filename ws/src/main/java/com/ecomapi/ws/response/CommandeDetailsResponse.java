package com.ecomapi.ws.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDetailsResponse {

    private CommandeResponse commandeDetails;
    private List<ProductsWithQte> products;
}
