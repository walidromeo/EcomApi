package com.ecomapi.ws.controllers;

import com.ecomapi.ws.response.*;
import com.ecomapi.ws.services.CommandeService;
import com.ecomapi.ws.shared.dto.CommandeDto;
import com.ecomapi.ws.userRequest.ProductsCommandeRequest;
import com.ecomapi.ws.userRequest.UpdatingCommande;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/commande") // localhost:8084/api/products
@RequiredArgsConstructor
public class CommandeControllers {

    private final CommandeService commandeService;

    @PostMapping(path = "/add_commande",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<HashMap<String,String>> addCommande(@RequestBody ProductsCommandeRequest commandeRequest) throws Exception {
        if (commandeRequest.getFirstName().isEmpty() || commandeRequest.getLastname().isEmpty() || commandeRequest.getNumber().isEmpty()
                || commandeRequest.getProductsRequest().size()<=0)
            throw new Exception(StockErrorMessage.MISSING_REQUIRED_FILED.getErrorMessage());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        CommandeDto commandeDto = modelMapper.map(commandeRequest,CommandeDto.class);
        HashMap<String,String> addToDb = commandeService.addCommande(commandeDto);
        return new ResponseEntity<HashMap<String,String>>(addToDb, HttpStatus.CREATED);
    }

    @PutMapping(path = "/livraison/{commandeId}",produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CommandeResponse> updatedLivraison(@RequestBody UpdatingCommande updatingCommande, @PathVariable String commandeId) {
        // Khasni ndir Get LCh7al kayn f la base u ndir 3lih Modification b dkchi li
        // ghadi nisft f JSON
        // So awl haja hiya get ldkchi li ayji mn la base
        ModelMapper modelMapper = new ModelMapper();
        CommandeDto stockDtoUpdate = commandeService.updatedLivraison(commandeId,updatingCommande);
        CommandeResponse productsResponse = modelMapper.map(stockDtoUpdate, CommandeResponse.class);
        return new ResponseEntity<CommandeResponse>(productsResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/etat_commande/{commandeId}",produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CommandeResponse> updatedEtatCommande(@PathVariable String commandeId) {
        // Khasni ndir Get LCh7al kayn f la base u ndir 3lih Modification b dkchi li
        // ghadi nisft f JSON
        // So awl haja hiya get ldkchi li ayji mn la base
        ModelMapper modelMapper = new ModelMapper();
        CommandeDto stockDtoUpdate = commandeService.updatedCommande(commandeId);
        CommandeResponse productsResponse = modelMapper.map(stockDtoUpdate, CommandeResponse.class);
        return new ResponseEntity<CommandeResponse>(productsResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/info_commande", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity< HashMap<String,List<CommandeDisplayResponse>>> commandeInfo() {
        // an9albu 3lih
        HashMap<String,List<CommandeDisplayResponse>> getCommandeInfo = commandeService.getCommandeInfo();

        return new ResponseEntity<HashMap<String,List<CommandeDisplayResponse>>>(getCommandeInfo, HttpStatus.OK);
    }

    @GetMapping(path="/mainInfo/{dateD}/{dateF}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public  ResponseEntity<HashMap<String,Double>> getInfoToMain(@PathVariable String dateD, @PathVariable String dateF){
        HashMap<String,Double>  getInfoFromDb = commandeService.getInfoToDb(dateD,dateF);
        return new  ResponseEntity<HashMap<String,Double>>(getInfoFromDb,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/commandeInfo/{idCommande}", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CommandeDetailsResponse> getInfoCommandeByIdCommande(@PathVariable String idCommande){
        CommandeDetailsResponse commandeProductsResponse = commandeService.getInfoCommandeByIdCommande(idCommande);
        return new  ResponseEntity<CommandeDetailsResponse>(commandeProductsResponse,HttpStatus.ACCEPTED);
    }

}
