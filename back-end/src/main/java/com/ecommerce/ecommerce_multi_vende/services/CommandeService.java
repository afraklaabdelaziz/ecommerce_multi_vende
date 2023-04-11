package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Commande;
import com.ecommerce.ecommerce_multi_vende.entities.StatusCommande;

public interface CommandeService {
    public ResponseDto addCommande(Commande commande);
    public ResponseDto updateCommande(Commande commande);
    public ResponseDto findAllCommande();
    public ResponseDto deleteCommande(Long idCommande);
    public ResponseDto findCommandeById(Long idCommande);
    Long count();
    ResponseDto updateStatusCommande(Long id, StatusCommande statusCommande);
}
