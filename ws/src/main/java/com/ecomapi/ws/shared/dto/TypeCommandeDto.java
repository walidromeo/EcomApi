package com.ecomapi.ws.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeCommandeDto {

    private long id;

    private String idClientCommande;

    private String signification;
}
