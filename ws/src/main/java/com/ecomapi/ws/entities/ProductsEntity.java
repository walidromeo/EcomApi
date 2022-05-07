package com.ecomapi.ws.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "products") @Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {

    @Id
    @GeneratedValue
    private long idProducts;

    @Column(nullable = false, length = 52)
    private String idClientProducts;

    @Column(nullable = false, length = 255)
    private String titleArticle;

    @Column(nullable = false, length = 52)
    private double price;

    @ManyToOne
    @JoinColumn(name="image_id")
    private ImageEntity imageProduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CommandeProducts> commandeProducts;

    @ManyToOne
    @JoinColumn(name="users_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategorieEntity categoryProduct;

}