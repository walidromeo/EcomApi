package com.ecomapi.ws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "typeCommande") @Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeCommandeEntity {

    @Id
    private long id;

    @Column(nullable = false, length = 52)
    private String idClientCommande;

    @Column(nullable = false, length = 52)
    private String signification;

    @OneToMany(mappedBy="typeCommande",cascade=CascadeType.ALL)
    private List<CommandeEntity> commande;
}
