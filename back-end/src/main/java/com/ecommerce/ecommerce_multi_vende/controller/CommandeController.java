package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Commande;
import com.ecommerce.ecommerce_multi_vende.entities.StatusCommande;
import com.ecommerce.ecommerce_multi_vende.services.CommandeItemsService;
import com.ecommerce.ecommerce_multi_vende.services.CommandeService;
import com.ecommerce.ecommerce_multi_vende.services.FactureService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/commande")
public class CommandeController {
    CommandeService commandeService;
    private CommandeItemsService commandeItemsService;
    private FactureService factureService;

    public CommandeController(CommandeService commandeService, CommandeItemsService commandeItemsService, FactureService factureService) {
        this.commandeService = commandeService;
        this.commandeItemsService = commandeItemsService;
        this.factureService = factureService;
    }

    @PostMapping("/add")
    public ResponseDto addCommande(@RequestBody Commande commande){
        return commandeService.addCommande(commande);
    }
    @GetMapping("/one/{id}")
    public ResponseDto getOne(@PathVariable Long id){
        return commandeService.findCommandeById(id);
    }
   @GetMapping("/all")
   public ResponseDto allCommandes(){
      return factureService.findAll();
   }
    @GetMapping("/count")
    public Long count(){
        return commandeService.count();
    }
    @GetMapping("/count-of-vendeur")
    public Integer countVendeur(){
        return commandeItemsService.countByVendeur();
    }
    @PutMapping("/update-status/{id}/{status}")
    public ResponseDto updateStatus(@PathVariable Long id, @PathVariable StatusCommande status){
        return commandeService.updateStatusCommande(id,status);
    }
}
