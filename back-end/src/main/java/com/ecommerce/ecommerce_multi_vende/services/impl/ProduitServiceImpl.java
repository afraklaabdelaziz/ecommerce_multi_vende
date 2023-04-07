package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Category;
import com.ecommerce.ecommerce_multi_vende.entities.Produit;
import com.ecommerce.ecommerce_multi_vende.repositories.CategoryRepository;
import com.ecommerce.ecommerce_multi_vende.repositories.ProduitRepository;
import com.ecommerce.ecommerce_multi_vende.services.CategoryService;
import com.ecommerce.ecommerce_multi_vende.services.ProduitService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;
    private CategoryRepository categoryRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository, CategoryRepository categoryRepository) {
        this.produitRepository = produitRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseDto addProduit(Produit produit) {
        if (produit == null || produit == new Produit()){
            return new ResponseDto("bad request","produit est null ou vide");
        }else if (produit.getPrix() <= 0){
            return new ResponseDto("bad request","prix est inferieur ou egale a 0");
        }else {
           Optional<Category> category = categoryRepository.findById(produit.getCategory().getId());
            produit.setCategory(category.get());
            System.out.println(produit);
            produitRepository.save(produit);
            return new ResponseDto("success","produit a été ajouter avec success",produit);
        }
    }
    @Override
    public ResponseDto updateProduit(Produit produit) {
        if (produit == null || produit == new Produit()){
            return  new ResponseDto("bad request","produit est null ou vide");
        } else if (produit.getPrix() <= 0 ) {
            return new ResponseDto("bad request","prix est inferieur ou egale a 0");
        }else {
            Produit produitFound = (Produit) this.findProduitById(produit.getId()).getData();
            produitFound.setNom(produit.getNom());
            produitFound.setPrix(produit.getPrix());
            produitFound.setCategory(produit.getCategory());
            produitFound.setQuantity(produit.getQuantity());
            produitRepository.save(produitFound);
            return new ResponseDto("success","produit est modifier",produitFound);
        }

    }

    @Override
    public ResponseDto deleteProduit(Long idProduit) {
        Produit produit = (Produit) this.findProduitById(idProduit).getData();
        produitRepository.delete(produit);
        return new ResponseDto("success","produit est supprimer",produit);
    }

    @Override
    public ResponseDto allProduit() {
        return new ResponseDto("success","list des produits",produitRepository.findAll());
    }

    @Override
    public ResponseDto findProduitById(Long idProduit) {
        Optional<Produit> produit = produitRepository.findById(idProduit);
        if (produit.isEmpty()){
            return new ResponseDto("bad request","produit est introvable");
        }else {
            return new ResponseDto("success","produit trover",produit);
        }
    }
}
