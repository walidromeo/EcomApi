package com.ecomapi.ws.repositories;

import com.ecomapi.ws.entities.CommandeEntity;
import com.ecomapi.ws.entities.TypeCommandeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeRepo extends CrudRepository<CommandeEntity, Long> {

    CommandeEntity findByIdClientCommande(String commandeId);

    List<CommandeEntity> findByLivraison(boolean b);

    List<CommandeEntity> findByTypeCommande(TypeCommandeEntity b);


    /*
            NATIVE QUERY
     */
    @Query(value="SELECT COUNT(id_commande) FROM commande WHERE commande_id=1 AND date_commande BETWEEN :dateD AND :dateF",nativeQuery=true)
    Double commandeEnmodeInitial(@Param("dateD") String dateD, @Param("dateF") String dateF);

    @Query(value = "SELECT COUNT(id_commande) FROM commande WHERE commande_id=2 AND date_livraison BETWEEN :dateD AND :dateF", nativeQuery = true)
    Double commandeEnmodeLivraison(@Param("dateD") String dateD, @Param("dateF") String dateF);

    @Query(value = "SELECT COUNT(id_commande) FROM commande WHERE commande_id=4 AND date_retour BETWEEN :dateD AND :dateF", nativeQuery = true)
    Double commandeEnmodeRetour(@Param("dateD") String dateD, @Param("dateF") String dateF);

    @Query(value = "SELECT SUM(pr.price) FROM products AS pr INNER JOIN commande AS cmd ON pr.id_products=cmd.product_id AND cmd.commande_id=1 AND cmd.date_commande BETWEEN :dateD AND :dateF ", nativeQuery = true)
    Double sumCommandeEnmodeInitial(@Param("dateD") String dateD, @Param("dateF") String dateF);

    @Query(value = "SELECT SUM(pr.price) FROM products AS pr INNER JOIN commande AS cmd ON pr.id_products=cmd.product_id AND cmd.commande_id=2 AND cmd.date_livraison BETWEEN :dateD AND :dateF ", nativeQuery = true)
    Double sumCommandeEnmodeLivraison(@Param("dateD") String dateD, @Param("dateF") String dateF);

    @Query(value = "SELECT SUM(pr.price) FROM products AS pr INNER JOIN commande AS cmd ON pr.id_products=cmd.product_id AND cmd.commande_id=4 AND cmd.date_retour BETWEEN :dateD AND :dateF ", nativeQuery = true)
    Double sumCommandeEnmodeRetour(@Param("dateD") String dateD, @Param("dateF") String dateF);

    @Query(value = "SELECT SUM(cmd.qte*pr.pu) FROM products AS pr INNER JOIN commande AS cmd ON pr.id_products=cmd.product_id AND cmd.commande_id=3 AND cmd.date_succes BETWEEN :dateD AND :dateF ", nativeQuery = true)
    Double sumCommandeEnmodeSucces(@Param("dateD") String dateD, @Param("dateF") String dateF);
}
