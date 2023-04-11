package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Commande;
import com.ecommerce.ecommerce_multi_vende.entities.CommandeItems;
import com.ecommerce.ecommerce_multi_vende.entities.Produit;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.repositories.CommandeItemsRepository;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.CommandeItemsService;
import com.ecommerce.ecommerce_multi_vende.services.CommandeService;
import com.ecommerce.ecommerce_multi_vende.services.ProduitService;
import com.ecommerce.ecommerce_multi_vende.utiles.GenerateReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandeItemsServiceImpl implements CommandeItemsService {
    private CommandeItemsRepository commandeItemsRepository;
    private UserRepository userRepository;

    public CommandeItemsServiceImpl(CommandeItemsRepository commandeItemsRepository, UserRepository userRepository) {
        this.commandeItemsRepository = commandeItemsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDto addCommandeItem(CommandeItems commandeItem) {
      if(commandeItem == null){
          return new ResponseDto("bad request","commande item est null");
      } else if (commandeItem.getQuantity() < 1 ) {
          return new ResponseDto("bad request","commande Item quantity est inferieur 1");
      } else {
          commandeItemsRepository.save(commandeItem);
          return new ResponseDto("success","commande item est ajouter",commandeItem);
      }
    }

    @Override
    public Integer countByVendeur() {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principale;
        Optional<UserApp> userApp = userRepository.findByEmail(user.getUsername());
        return commandeItemsRepository.countByProduitVendeur(userApp.get());
    }
}
