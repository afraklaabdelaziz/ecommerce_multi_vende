package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;

public interface DemmandeService {
    ResponseDto addDemmande(DemandeVendeur demandeVendeur);
    ResponseDto findAllDemmande();
    ResponseDto findDemmandeUser(String email);
    ResponseDto findDemmandeById(Long id);
}
