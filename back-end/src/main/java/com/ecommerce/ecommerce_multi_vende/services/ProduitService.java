package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Produit;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import org.springframework.data.domain.Page;

public interface ProduitService {
    public ResponseDto addProduit(Produit produit);
    public ResponseDto updateProduit(Produit produit);
    public ResponseDto deleteProduit(Long idProduit);
    public ResponseDto allProduit();

    Page<Produit> allProduit(int page, int size);
    Page<Produit> allProduitOfVendeur(int page, int size);

    public ResponseDto findProduitById(Long idProduit);

    public Long countAll();
    public Integer countByVendeur();
}
