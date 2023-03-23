package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.CommandeItems;
import com.ecommerce.ecommerce_multi_vende.services.CommandeItemsService;
import com.ecommerce.ecommerce_multi_vende.utiles.GenerateReference;
import org.springframework.stereotype.Service;

@Service
public class CommandeItemsServiceImpl implements CommandeItemsService {
    @Override
    public ResponseDto addCommandeItem(CommandeItems commandeItem) {
      if(commandeItem == null){
          return new ResponseDto("bad request","commande item est null");
      }else {
          commandeItem.setReference(GenerateReference.applyGenerateReference());
          return new ResponseDto("success","commande item est ajouter",commandeItem);
      }
    }
}
