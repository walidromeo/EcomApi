package com.ecomapi.ws.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "commande") @Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeEntity {

    @Id
    @GeneratedValue
    private long idCommande;

    @Column(nullable = false, length = 52)
    private String idClientCommande;

    @Column(nullable = false)
    private boolean livraison;

    @Column(nullable = true)
    private boolean etatCommande ;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String number;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date dateCommande;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = true)
    private Date dateLivraison;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = true)
    private Date dateSucces;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = true)
    private Date dateRetour;

    // ONE COMMANDE HAS ONE PRODUCTS OR MANY
    // products has many commande or one

    @ManyToOne
    @JoinColumn(name="commande_id")
    private TypeCommandeEntity typeCommande;

}
