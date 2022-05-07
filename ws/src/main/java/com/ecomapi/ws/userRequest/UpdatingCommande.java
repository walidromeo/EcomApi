package com.ecomapi.ws.userRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatingCommande {

    private long etatCommande;

    private Date dateLivraison;

    private Date dateSucces;

    private Date dateRetour;
}

