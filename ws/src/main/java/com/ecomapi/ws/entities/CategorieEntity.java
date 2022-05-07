package com.ecomapi.ws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity(name = "category") @Data @NoArgsConstructor @AllArgsConstructor
public class CategorieEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 52)
    private String idCategoryClient;

    @Column(nullable = false, length = 52)
    private String nomCategory;

    @OneToMany(mappedBy="categoryProduct",cascade=CascadeType.ALL)
    private List<ProductsEntity> produit;
}
