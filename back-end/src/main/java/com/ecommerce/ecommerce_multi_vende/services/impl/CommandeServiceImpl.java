package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.*;
import com.ecommerce.ecommerce_multi_vende.repositories.CommandeRepository;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.*;
import com.ecommerce.ecommerce_multi_vende.utiles.GenerateReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {
    private CommandeRepository commandeRepository;
    private CommandeItemsService commandeItemsService;
    private FactureService factureService;
    private UserRepository userRepository;
    private ProduitService produitService;

    public CommandeServiceImpl(CommandeRepository commandeRepository, CommandeItemsService commandeItemsService, FactureService factureService, UserRepository userRepository, ProduitService produitService) {
        this.commandeRepository = commandeRepository;
        this.commandeItemsService = commandeItemsService;
        this.factureService = factureService;
        this.userRepository = userRepository;
        this.produitService = produitService;
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
           Facture facture = new Facture();
           facture.setCommande(commande);
           facture.setDate(LocalDate.now());

           Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           if (principale instanceof User) {
               User user = (User) principale;
               Optional<UserApp> userApp = userRepository.findByEmail(user.getUsername());
               facture.setClient(userApp.get());
           }
           factureService.addFacture(facture);
           System.out.println(commande.getCommandeItemsList());
           commande.getCommandeItemsList().forEach((commandeItem -> {
              Produit produit = (Produit) produitService.findProduitById(commandeItem.getId()).getData();
               commandeItem.setCommande(commande);
               commandeItem.setReference(GenerateReference.applyGenerateReference());
               commandeItem.setProduit(produit);
               commandeItemsService.addCommandeItem(commandeItem);
           }));

           return new ResponseDto("success","commande est effectuer avec success",commande);
       }
    }

    @Override
    public ResponseDto updateCommande(Commande commande) {
        return null;
    }

    @Override
    public ResponseDto findAllCommande() {
        return new ResponseDto("success","commandes",commandeRepository.findAll());
    }

    @Override
    public ResponseDto deleteCommande(Long idCommande) {
        return null;
    }

    @Override
    public ResponseDto findCommandeById(Long idCommande) {
        Optional<Commande> commande = commandeRepository.findById(idCommande);
        if(!commande.isPresent()){
            return new ResponseDto("bad request","commande n'exist pas");
        }else {
            return new ResponseDto("success","commande",commande);
        }
    }

    @Override
    public Long count() {
        return commandeRepository.count();
    }

    @Override
    public ResponseDto updateStatusCommande(Long id, StatusCommande statusCommande) {
        Optional<Commande> commande = commandeRepository.findById(id);
        if (!commande.isPresent()){
            return new ResponseDto("bad request","commande n'exist pas");
        }else {
        commande.get().setStatusCommande(statusCommande);
        commandeRepository.save(commande.get());
        return new ResponseDto("success","status est modifier",commande);
    }}
}
