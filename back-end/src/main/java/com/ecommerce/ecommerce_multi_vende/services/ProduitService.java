package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Produit;

public interface ProduitService {
    public ResponseDto addProduit(Produit produit);
    public ResponseDto updateProduit(Produit produit);
    public ResponseDto deleteProduit(Long idProduit);
    public ResponseDto allProduit();
    public ResponseDto findProduitById(Long idProduit);
}
