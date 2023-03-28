package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.CommandeItems;

public interface CommandeItemsService {
    ResponseDto addCommandeItem(CommandeItems commandeItem);
}
