package com.ecomapi.ws.shared.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ecomapi.ws.response.CommandeDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDisplayDto implements Serializable {
    
    private static final long serialVersionUID = 6540152208691607844L;

    private long id;

    private String idClientCommande;

    private int qte;

    private String city;

    private String number;

    private boolean livraison;

    private String firstName;

    private String lastname;

    private String  idProducts;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCommande;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateLivraison;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateSucces;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateRetour;

    private boolean etatCommande;

    private CommandeDetailsResponse commandeDetails;
    private double total;
    private TypeCommandeDto typeCommande;
}
