package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Commande;
import com.ecommerce.ecommerce_multi_vende.entities.CommandeItems;
import com.ecommerce.ecommerce_multi_vende.entities.StatusCommande;
import com.ecommerce.ecommerce_multi_vende.repositories.CommandeRepository;
import com.ecommerce.ecommerce_multi_vende.services.CommandeItemsService;
import com.ecommerce.ecommerce_multi_vende.services.CommandeService;
import com.ecommerce.ecommerce_multi_vende.utiles.GenerateReference;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommandeServiceImpl implements CommandeService {
    private CommandeRepository commandeRepository;
    private CommandeItemsService commandeItemsService;

    public CommandeServiceImpl(CommandeRepository commandeRepository, CommandeItemsService commandeItemsService) {
        this.commandeRepository = commandeRepository;
        this.commandeItemsService = commandeItemsService;
    }

    @Override
    public ResponseDto addCommande(Commande commande) {
       if (commande == null){
           return new ResponseDto("bad request","commande est null");
       }  else {
           commande.setReference(GenerateReference.applyGenerateReference());
           commande.setStatusCommande(StatusCommande.EN_COURS);
           commande.setDate(LocalDate.now());
           commandeRepository.save(commande);
           CommandeItems commandeItem = new CommandeItems();
           commandeItem.setCommande(commande);
           commandeItem.setQuantity(2);
           commandeItemsService.addCommandeItem(commandeItem);
           return new ResponseDto("success","commande est effectuer avec success",commande);
       }
    }

    @Override
    public ResponseDto updateCommande(Commande commande) {
        return null;
    }

    @Override
    public ResponseDto findAllCommande() {
        return null;
    }

    @Override
    public ResponseDto deleteCommande(Long idCommande) {
        return null;
    }

    @Override
    public ResponseDto findCommandeById(Long idCommande) {
        return null;
    }
}
