package com.ecomapi.ws.response;

import com.ecomapi.ws.shared.dto.ProductsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsWithQte {

    private long qte;
    private ProductsDto product;

}
