package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Commande;
import com.ecommerce.ecommerce_multi_vende.services.CommandeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/commande")
public class CommandeController {
    CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping("/add")
    public ResponseDto addCommande(@RequestBody Commande commande){
        return commandeService.addCommande(commande);
    }
}
