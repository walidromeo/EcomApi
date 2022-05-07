package com.ecomapi.ws.repositories;

import com.ecomapi.ws.entities.CommandeEntity;
import com.ecomapi.ws.entities.CommandeProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeProductsRepo extends JpaRepository<CommandeProducts, Long> {


    List<CommandeProducts> findByCommande(CommandeEntity commande);
}
