package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Category;
import com.ecommerce.ecommerce_multi_vende.entities.Produit;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.repositories.CategoryRepository;
import com.ecommerce.ecommerce_multi_vende.repositories.ProduitRepository;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.ProduitService;
import com.ecommerce.ecommerce_multi_vende.utiles.GenerateReference;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.PageRequest.of;

@Service
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.produitRepository = produitRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
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
            Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = (User) principale;
            Optional<UserApp> userApp = userRepository.findByEmail(user.getUsername());
            produit.setVendeur(userApp.get());
            produit.setReference(GenerateReference.applyGenerateReference());
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
        Optional<Produit> produit = (Optional<Produit>) this.findProduitById(idProduit).getData();
        produitRepository.delete(produit.get());
        return new ResponseDto("success","produit est supprimer",produit);
    }

    @Override
    public ResponseDto allProduit() {
        return null;
    }

    @Override
    public Page<Produit> allProduit(int page, int size) {
       return produitRepository.findAll(of(page, size));
    }

    @Override
    public Page<Produit> allProduitOfVendeur(int page, int size) {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = (User) principale;
            Optional<UserApp> userApp = userRepository.findByEmail(user.getUsername());
            return produitRepository.findAllByVendeur(userApp.get(), of(page, size));
    }

    @Override
    public ResponseDto findProduitById(Long idProduit) {
        Optional<Produit> produit = produitRepository.findById(idProduit);
        if (!produit.isPresent()){
            return new ResponseDto("bad request","produit est introvable");
        }else {
            return new ResponseDto("success","produit trover",produit.get());
        }
    }

    @Override
    public Long countAll() {
        return produitRepository.count();
    }

    @Override
    public Integer countByVendeur() {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principale;
        Optional<UserApp> userApp = userRepository.findByEmail(user.getUsername());
        return produitRepository.countByVendeur(userApp.get());
    }
}
