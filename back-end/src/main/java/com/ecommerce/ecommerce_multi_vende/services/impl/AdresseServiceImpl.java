package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Adresse;
import com.ecommerce.ecommerce_multi_vende.repositories.AdresseRepository;
import com.ecommerce.ecommerce_multi_vende.services.AdresseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdresseServiceImpl implements AdresseService {
    private final AdresseRepository adresseRepository;

    public AdresseServiceImpl(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    @Override
    public ResponseDto addAdresse(Adresse adresse) {
       if (adresse == null || adresse == new Adresse()){
           return new ResponseDto("bad request","adresse est null o vide");
       }else {
           adresseRepository.save(adresse);
           return new ResponseDto("success","addresse ete ajouter",adresse);
       }
    }

    @Override
    public ResponseDto updateAdresse(Adresse adresse) {
        Optional<Adresse> adresseFound = adresseRepository.findById(adresse.getId());
        if (adresse == null || adresse == new Adresse()){
            return new ResponseDto("bad request","adresse est null o vide");
        }else {
            adresseFound.get().setAdresse(adresse.getAdresse());
            adresseFound.get().setCodePostal(adresse.getCodePostal());
            adresseFound.get().setPays(adresse.getPays());
            adresseFound.get().setVille(adresse.getVille());
            adresseRepository.save(adresse);
            return new ResponseDto("success","addresse ete modifier",adresseFound);
        }
    }

    @Override
    public ResponseDto findAdresseById(Long idAdresse) {
        return null;
    }
}
