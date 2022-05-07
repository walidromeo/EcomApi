package com.ecomapi.ws.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoProductsId implements Serializable {

    private static final long serialVersionUID = 6540152202691607844L;
    private long id;
    private String idCategoryClient;
    private List<String> productsId;
    private String nomCategory;
}
