package com.ecomapi.ws.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ecomapi.ws.shared.dto.TypeCommandeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDisplayResponse implements Serializable {

    private static final long serialVersionUID = 6540152208691607844L;

    private long id;

    private String idClientCommande;
    private String city;
    private String number;
    private String firstName;
    private String lastname;
    private boolean livraison;

    private boolean etatCommande;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCommande;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateLivraison;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateSucces;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateRetour;

    private CommandeDetailsResponse commandeDetails;

    private double total;

    private TypeCommandeDto typeCommande;
}
