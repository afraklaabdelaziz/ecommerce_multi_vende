package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Facture;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.repositories.FactureRepository;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.FactureService;
import com.ecommerce.ecommerce_multi_vende.services.UserServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactureServiceImpl implements FactureService {
    private FactureRepository factureRepository;
    private UserRepository userRepository;

    public FactureServiceImpl(FactureRepository factureRepository, UserRepository userRepository) {
        this.factureRepository = factureRepository;
        this.userRepository = userRepository;
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

    @Override
    public ResponseDto findAllFactureClient() {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principale instanceof User) {
            User user = (User) principale;
            Optional<UserApp> userApp = userRepository.findByEmail(user.getUsername());
            return new ResponseDto("success","List des facture",factureRepository.findAllByClient(userApp.get()));
        }else {
            return new ResponseDto("bad request","user no authentifier");
        }

    }

    @Override
    public ResponseDto findAll() {
        return new ResponseDto("success","all facture",factureRepository.findAll());
    }
}
