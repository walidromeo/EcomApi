package com.ecomapi.ws.serviceImp;


import com.ecomapi.ws.entities.CommandeEntity;
import com.ecomapi.ws.entities.CommandeProducts;
import com.ecomapi.ws.entities.TypeCommandeEntity;
import com.ecomapi.ws.repositories.CommandeProductsRepo;
import com.ecomapi.ws.repositories.CommandeRepo;
import com.ecomapi.ws.repositories.ProductsRepo;
import com.ecomapi.ws.repositories.TypeCommandeRepo;
import com.ecomapi.ws.response.*;
import com.ecomapi.ws.services.CommandeService;
import com.ecomapi.ws.shared.Utils;
import com.ecomapi.ws.shared.dto.CommandeDisplayDto;
import com.ecomapi.ws.shared.dto.CommandeDto;
import com.ecomapi.ws.shared.dto.ProductsDto;
import com.ecomapi.ws.userRequest.UpdatingCommande;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CommandeServiceImp implements CommandeService {
    private final CommandeRepo commandeRepo;
    private final ProductsRepo productsRepo;
    private final Utils utils;
    private final TypeCommandeRepo typeCommandeRepo;
    private final CommandeProductsRepo commandeProductsRepo;
    @Override
    public HashMap<String,String> addCommande(CommandeDto commandeDto) {
        /*
            AWAL HAJA KHASNI NSAWAB WAHD COMMANDE Y3NI NZID COMMANDE HIYA LAWLA
            MOURAHA NAKHUD ID DIALHA
         */
        TypeCommandeEntity tce= typeCommandeRepo.findById(1);
        CommandeEntity createCommande = new CommandeEntity();
        createCommande.setFirstName(commandeDto.getFirstName());
        createCommande.setLastname(commandeDto.getLastname());
        createCommande.setDateCommande(commandeDto.getDateCommande());
        createCommande.setIdClientCommande(utils.generateStringId(16));
        createCommande.setLivraison(false);
        createCommande.setNumber(commandeDto.getNumber());
        createCommande.setCity(commandeDto.getCity());
        createCommande.setTypeCommande(tce);
        commandeRepo.save(createCommande);


        for (ProductsResponse productsResponse: commandeDto.getProductsRequest()) {
            CommandeProducts commandeProducts = new CommandeProducts();
            commandeProducts.setCommande(createCommande);
            commandeProducts.setProduct(productsRepo.findByIdClientProducts(productsResponse.getIdClientProducts()));
            commandeProducts.setQte(productsResponse.getQte());
            commandeProductsRepo.save(commandeProducts);
        }

        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd");
        String idToDisplay = myDateObj.format(myFormatObj)+myDateObj.format(myFormatObj1)+myDateObj.format(myFormatObj2)+createCommande.getIdCommande();
        HashMap<String,String> messageSucces = new HashMap<String,String>();
        messageSucces.put("IdCommande",idToDisplay);
        return messageSucces;
    }

    @Override
    public CommandeDto updatedLivraison(String commandeId,UpdatingCommande updatingCommande) {
        ModelMapper modelMapper = new ModelMapper();
        TypeCommandeEntity tce= typeCommandeRepo.findById(updatingCommande.getEtatCommande());
        CommandeEntity commandeEntityToUpdateLivraison  = commandeRepo.findByIdClientCommande(commandeId);
        commandeEntityToUpdateLivraison.setLivraison(true);
        commandeEntityToUpdateLivraison.setTypeCommande(tce);
        if(updatingCommande.getEtatCommande()==2) commandeEntityToUpdateLivraison.setDateLivraison(updatingCommande.getDateLivraison());
        if(updatingCommande.getEtatCommande()==3) commandeEntityToUpdateLivraison.setDateSucces(updatingCommande.getDateSucces());
        if(updatingCommande.getEtatCommande()==4) commandeEntityToUpdateLivraison.setDateRetour(updatingCommande.getDateRetour());
        commandeRepo.save(commandeEntityToUpdateLivraison);
        CommandeDto commandeDto = modelMapper.map(commandeEntityToUpdateLivraison,CommandeDto.class);
        return commandeDto;
    }

    @Override
    public CommandeDto updatedCommande(String commandeId) {
        ModelMapper modelMapper = new ModelMapper();
        CommandeEntity commandeEntityToUpdateLivraison  = commandeRepo.findByIdClientCommande(commandeId);
        log.info("**************************************************************************************************");
        log.info("commandeEntityToUpdateLivraison.isLivraison() {}",commandeEntityToUpdateLivraison.isLivraison());
        log.info("**************************************************************************************************");
        if (commandeEntityToUpdateLivraison.isLivraison()!=true) throw new RuntimeException(StockErrorMessage.MISSING_REQUIRED_FILED.getErrorMessage());
        commandeEntityToUpdateLivraison.setEtatCommande(true);
        commandeRepo.save(commandeEntityToUpdateLivraison);
        CommandeDto commandeDto = modelMapper.map(commandeEntityToUpdateLivraison,CommandeDto.class);
        return commandeDto;
    }
    @Override
    public HashMap<String,List<CommandeDisplayResponse>> getCommandeInfo() {
        ModelMapper modelMapper = new ModelMapper();
        HashMap<String,List<CommandeDisplayResponse>> listHashMap = new HashMap<String,List<CommandeDisplayResponse>>();
        // GET NOT SHIPPED
        long typeCI= 1;
        long typeCL= 2;
        long typeCS= 3;
        long typeCR= 4;
        TypeCommandeEntity ci= typeCommandeRepo.findById(typeCI);
        TypeCommandeEntity cl= typeCommandeRepo.findById(typeCL);
        TypeCommandeEntity cs= typeCommandeRepo.findById(typeCS);
        TypeCommandeEntity cr= typeCommandeRepo.findById(typeCR);
        List<CommandeEntity> tceIntilial= commandeRepo.findByTypeCommande(ci);
        List<CommandeEntity> tceLivraison = commandeRepo.findByTypeCommande(cl);
        List<CommandeEntity> tceSucces = commandeRepo.findByTypeCommande(cs);
        List<CommandeEntity> tceRetour = commandeRepo.findByTypeCommande(cr);

        // Convert to   DTO
        List<CommandeDisplayDto> commandeDtos = new ArrayList<>();

        for (CommandeEntity commandeEntity: tceIntilial) {
            CommandeDetailsResponse commandeDetails = getInfoCommandeByIdCommande(commandeEntity.getIdClientCommande());
            double total = 0;
            for (ProductsWithQte getProductAndQte:commandeDetails.getProducts()) {
                total += getProductAndQte.getProduct().getPrice() * getProductAndQte.getQte();
            }
            CommandeDisplayDto commandeDto = modelMapper.map(commandeEntity,CommandeDisplayDto.class);
            commandeDto.setCommandeDetails(commandeDetails);
            commandeDto.setTotal(total);
            commandeDtos.add(commandeDto);
        }

        // CONVERT TO RESPONSE
        List<CommandeDisplayResponse> commandeResponses = new ArrayList<>();
        for (CommandeDisplayDto commandeDto: commandeDtos) {
            CommandeDisplayResponse commandeResponse = modelMapper.map(commandeDto,CommandeDisplayResponse.class);
            commandeResponses.add(commandeResponse);
        }

        // Convert to   DTO
        List<CommandeDisplayDto> commandeDtosucces = new ArrayList<>();

        for (CommandeEntity commandeEntity: tceLivraison) {
            CommandeDetailsResponse commandeDetails = getInfoCommandeByIdCommande(commandeEntity.getIdClientCommande());
            double total = 0;
            for (ProductsWithQte getProductAndQte:commandeDetails.getProducts()) {
                total += getProductAndQte.getProduct().getPrice() * getProductAndQte.getQte();
            }
            CommandeDisplayDto commandeDto = modelMapper.map(commandeEntity,CommandeDisplayDto.class);
            commandeDto.setCommandeDetails(commandeDetails);
            commandeDto.setTotal(total);
            commandeDtosucces.add(commandeDto);
        }
        // CONVERT TO RESPONSE

        List<CommandeDisplayResponse> commandeResponsesuccesLivraison = new ArrayList<>();
        for (CommandeDisplayDto commandeDto: commandeDtosucces) {
            CommandeDisplayResponse commandeResponse = modelMapper.map(commandeDto,CommandeDisplayResponse.class);
            commandeResponsesuccesLivraison.add(commandeResponse);
        }

        //GET RETOUR
        List<CommandeDisplayDto> commandeDtoRetour = new ArrayList<>();

        for (CommandeEntity commandeEntity: tceRetour) {
            CommandeDetailsResponse commandeDetails = getInfoCommandeByIdCommande(commandeEntity.getIdClientCommande());
            double total = 0;
            for (ProductsWithQte getProductAndQte:commandeDetails.getProducts()) {
                total += getProductAndQte.getProduct().getPrice() * getProductAndQte.getQte();
            }

            CommandeDisplayDto commandeDto = modelMapper.map(commandeEntity,CommandeDisplayDto.class);
            commandeDto.setCommandeDetails(commandeDetails);
            commandeDto.setTotal(total);
            commandeDtoRetour.add(commandeDto);
        }
        // CONVERT TO RESPONSE

        List<CommandeDisplayResponse> commandeResponseRetour = new ArrayList<>();
        for (CommandeDisplayDto commandeDto: commandeDtoRetour) {
            CommandeDisplayResponse commandeResponse = modelMapper.map(commandeDto,CommandeDisplayResponse.class);
            commandeResponseRetour.add(commandeResponse);
        }

        //GET RETOUR
        List<CommandeDisplayDto> commandeSucces = new ArrayList<>();

        for (CommandeEntity commandeEntity: tceSucces) {
            CommandeDetailsResponse commandeDetails = getInfoCommandeByIdCommande(commandeEntity.getIdClientCommande());
            double total = 0;
            for (ProductsWithQte getProductAndQte:commandeDetails.getProducts()) {
                total += getProductAndQte.getProduct().getPrice() * getProductAndQte.getQte();
            }
            CommandeDisplayDto commandeDto = modelMapper.map(commandeEntity,CommandeDisplayDto.class);
            commandeDto.setCommandeDetails(commandeDetails);
            commandeDto.setTotal(total);
            commandeSucces.add(commandeDto);
        }
        // CONVERT TO RESPONSE

        List<CommandeDisplayResponse> commandeResponseSucces = new ArrayList<>();
        for (CommandeDisplayDto commandeDto: commandeSucces) {
            CommandeDisplayResponse commandeResponse = modelMapper.map(commandeDto,CommandeDisplayResponse.class);
            commandeResponseSucces.add(commandeResponse);
        }

        listHashMap.put("commandeInitial",commandeResponses);
        listHashMap.put("commandeLivraison",commandeResponsesuccesLivraison);
        listHashMap.put("commandeSucces",commandeResponseSucces);
        listHashMap.put("commandeRetour",commandeResponseRetour);
        return listHashMap;
    }

    @Override
    public HashMap<String, Double> getInfoToDb(String dateD, String dateF) {
        HashMap<String,Double> hashMap = new  HashMap<String,Double>();
        Double nmbrCI = 0.0;
        Double nmbrCL= 0.0;
        Double nmbrCR=0.0;
        if (commandeRepo.commandeEnmodeInitial(dateD,dateF)!=null){
            nmbrCI=commandeRepo.commandeEnmodeInitial(dateD,dateF);
        }

        if (commandeRepo.commandeEnmodeLivraison(dateD,dateF)!=null){
            nmbrCL=commandeRepo.commandeEnmodeLivraison(dateD,dateF);
        }

        if (commandeRepo.commandeEnmodeRetour(dateD,dateF)!=null){
            nmbrCR=commandeRepo.commandeEnmodeRetour(dateD,dateF);
        }

        Double SumCI = 0.0;
        Double SumCL = 0.0;
        Double SumCR = 0.0;
        Double SumCS = 0.0;
        if(commandeRepo.sumCommandeEnmodeInitial(dateD,dateF)!=null){
            SumCI= commandeRepo.sumCommandeEnmodeInitial(dateD,dateF);
        }
        if(commandeRepo.sumCommandeEnmodeLivraison(dateD,dateF)!=null){
            SumCL= commandeRepo.sumCommandeEnmodeLivraison(dateD,dateF);
        }
        if(commandeRepo.sumCommandeEnmodeRetour(dateD,dateF)!=null){
            SumCR= commandeRepo.sumCommandeEnmodeRetour(dateD,dateF);
        }
        if(commandeRepo.sumCommandeEnmodeSucces(dateD,dateF)!=null){
            SumCS= commandeRepo.sumCommandeEnmodeSucces(dateD,dateF);
        }
        hashMap.put("commandeInitial",nmbrCI);
        hashMap.put("commandeLivraison",nmbrCL);
        hashMap.put("commandeRetour",nmbrCR);
        hashMap.put("caCI",SumCI);
        hashMap.put("caCl",SumCL);
        hashMap.put("caCR",SumCR);
        hashMap.put("caCS",SumCS);
        return hashMap;
    }

    @Override
    public CommandeDetailsResponse getInfoCommandeByIdCommande(String idCommande) {
    List<CommandeProducts> commandeProducts = commandeProductsRepo.findByCommande(commandeRepo.findByIdClientCommande(idCommande));
        List<ProductsDto> productsDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
       List<ProductsWithQte> productsWithQteList = new ArrayList<>();

        for (CommandeProducts commandeProducts1:commandeProducts) {
            ProductsWithQte productsWithQte = new ProductsWithQte();
            ProductsDto productsDto = modelMapper.map(commandeProducts1.getProduct(),ProductsDto.class);
            productsDtos.add(productsDto);
            productsWithQte.setProduct(productsDto);
            productsWithQte.setQte(commandeProducts1.getQte());
            productsWithQteList.add(productsWithQte);
        }

        CommandeDto commandeDto = modelMapper.map(commandeRepo.findByIdClientCommande(idCommande),CommandeDto.class);
        CommandeResponse commandeResponse = modelMapper.map(commandeDto,CommandeResponse.class);
        CommandeDetailsResponse commandeDetailsResponse = new CommandeDetailsResponse(commandeResponse,productsWithQteList);
        return commandeDetailsResponse;
    }
}