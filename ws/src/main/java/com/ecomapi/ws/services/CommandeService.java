package com.ecomapi.ws.services;

import com.ecomapi.ws.response.CommandeDetailsResponse;
import com.ecomapi.ws.response.CommandeDisplayResponse;
import com.ecomapi.ws.shared.dto.CommandeDto;
import com.ecomapi.ws.userRequest.UpdatingCommande;

import java.util.HashMap;
import java.util.List;

public interface CommandeService {
    HashMap<String,String> addCommande(CommandeDto commandeDto);

    CommandeDto updatedLivraison(String commandeId,UpdatingCommande updatingCommande);

    CommandeDto updatedCommande(String commandeId);

    HashMap<String,List<CommandeDisplayResponse>> getCommandeInfo();

    HashMap<String, Double> getInfoToDb(String dateD, String dateF);

    CommandeDetailsResponse getInfoCommandeByIdCommande(String idCommande);
}
