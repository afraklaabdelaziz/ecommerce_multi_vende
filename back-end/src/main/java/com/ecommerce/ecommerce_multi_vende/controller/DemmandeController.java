package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;
import com.ecommerce.ecommerce_multi_vende.services.DemmandeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/demmande_vendeur")
public class DemmandeController {
    private DemmandeService demmandeService;

    public DemmandeController(DemmandeService demmandeService) {
        this.demmandeService = demmandeService;
    }

    @PostMapping("/add")
    public ResponseDto addDemmande(@RequestBody DemandeVendeur demandeVendeur){
        return demmandeService.addDemmande(demandeVendeur);
    }
    @GetMapping("/all")
    public ResponseDto allDemmande(){
        return demmandeService.findAllDemmande();
    }
    @GetMapping("/demmande_user/{email}")
    public ResponseDto demmandeUser(@RequestParam String email){
        return demmandeService.findDemmandeUser(email);
    }
    @GetMapping("/count")
    public Long count(){
        return demmandeService.count();
    }
}
