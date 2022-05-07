package com.ecomapi.ws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "image") @Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue
    private long idPhoto;

    @Column(nullable = false, length = 52)
    private String idBrowserPhoto;

    @Column(nullable = false, length = 52)
    private String imagePath;

    @Column(nullable = false, length = 52)
    private String imageFileName;

    @OneToMany(mappedBy="imageProduct",cascade=CascadeType.PERSIST)
    private List<ProductsEntity> productsEntities;
}
