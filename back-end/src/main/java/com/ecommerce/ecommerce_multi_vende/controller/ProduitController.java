package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Produit;
import com.ecommerce.ecommerce_multi_vende.services.ProduitService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/produit")
@CrossOrigin("http://localhost:62250")
public class ProduitController {
    ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }
    @PostMapping("/add")
    public ResponseDto addProduit(@Valid @RequestBody Produit produit){
        return produitService.addProduit(produit);
    }
    @GetMapping("/all")
    public ResponseDto allProduit(){
        return produitService.allProduit();
    }

    @GetMapping("/one/{id}")
    public ResponseDto oneProduit(@RequestParam Long id){
        return produitService.findProduitById(id);
    }

    @PutMapping("/update")
    public ResponseDto updateProduit(@RequestBody Produit produit){
        return produitService.updateProduit(produit);
    }
}
