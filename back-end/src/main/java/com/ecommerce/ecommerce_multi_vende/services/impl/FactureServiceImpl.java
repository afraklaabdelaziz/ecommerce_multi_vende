package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Facture;
import com.ecommerce.ecommerce_multi_vende.repositories.FactureRepository;
import com.ecommerce.ecommerce_multi_vende.services.FactureService;
import org.springframework.stereotype.Service;

@Service
public class FactureServiceImpl implements FactureService {
    private FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public ResponseDto addFacture(Facture facture) {
        if(facture == null){
            return new ResponseDto("bad request","facture est null");
        }else {
           factureRepository.save(facture);
           return new ResponseDto("success","facture ajouter",facture);
        }
    }
}
