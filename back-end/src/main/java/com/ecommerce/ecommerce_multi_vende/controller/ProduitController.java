package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Produit;
import com.ecommerce.ecommerce_multi_vende.services.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.Optional;

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
//    @GetMapping("/all")
//    public ResponseDto allProduit(){
//        return produitService.allProduit();
//    }

    @GetMapping("/all")
    public Page<Produit> alllProduit(@RequestParam Optional<Integer> page , @RequestParam Optional<Integer> size ){
        return  produitService.allProduit(page.orElse(0), size.orElse(10) );
    }

    @GetMapping("/all-of-vendeur")
    public Page<Produit> alllProduitOfVendeur(@RequestParam Optional<Integer> page , @RequestParam Optional<Integer> size ){
        return  produitService.allProduitOfVendeur(page.orElse(0), size.orElse(10) );
    }

    @GetMapping("/one/{id}")
    public ResponseDto oneProduit(@PathVariable Long id){
        return produitService.findProduitById(id);
    }

    @PutMapping("/update")
    public ResponseDto updateProduit(@RequestBody Produit produit){
        return produitService.updateProduit(produit);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteProduit(@PathVariable Long id){
        return produitService.deleteProduit(id);
    }
    @GetMapping("/count-by-vendeur")
    public Integer countByVendeur(){
        return produitService.countByVendeur();
    }

    @GetMapping("/count")
    public Long count(){
        return produitService.countAll();
    }
}
