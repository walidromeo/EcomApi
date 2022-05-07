package com.ecomapi.ws.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 6540152202691607844L;

    private long id;
    private String idCategoryClient;
    private  List<ProductsDto> products;
    private String nomCategory;
}
