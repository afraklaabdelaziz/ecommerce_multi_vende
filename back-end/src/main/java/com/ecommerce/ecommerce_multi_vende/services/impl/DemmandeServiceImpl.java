package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.repositories.DemmandRepository;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.DemmandeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemmandeServiceImpl implements DemmandeService {
    private DemmandRepository demmandRepository;
    private UserRepository userRepository;


    public DemmandeServiceImpl(DemmandRepository demmandRepository,UserRepository userRepository) {
        this.demmandRepository = demmandRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDto addDemmande(DemandeVendeur demandeVendeur) {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (demandeVendeur == null) {
            return new ResponseDto("bad request", "demmande est null");
        } else if (principale instanceof User){
                User user = (User) principale;
               Optional<UserApp> userApp = userRepository.findByEmail(user.getUsername());
               demandeVendeur.setUserApp(userApp.get());
                demmandRepository.save(demandeVendeur);
                return new ResponseDto("success", "votre demmande est envoyer veuiller patienter");
            }else {
             return new ResponseDto("bad request","user non authentifier");
        }
    }
    @Override
    public ResponseDto findAllDemmande() {
        return new ResponseDto("success","demmandes",demmandRepository.findAll());
    }

    @Override
    public ResponseDto findDemmandeUser(String email) {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principale instanceof User){
            User user = (User) principale;
            Optional<UserApp> userApp = userRepository.findByEmail(email);
            return new ResponseDto("success","votre demmande",demmandRepository.findDemandeVendeurByUserApp(userApp.get()));
        }else {
            return new ResponseDto("bad request","user non authentifier");
        }

    }

    @Override
    public ResponseDto findDemmandeById(Long id) {
        Optional<DemandeVendeur> demandeVendeur = demmandRepository.findById(id);
        if (!demandeVendeur.isPresent()){
            return new ResponseDto("bad request","demmande n'exist pas");
        }else {
            return new ResponseDto("success","demmande est ",demandeVendeur);
        }
    }

    @Override
    public ResponseDto deleteDemmande(Long id) {
        Optional<DemandeVendeur> demande = demmandRepository.findById(id);
        if (!demande.isPresent()){
            return new ResponseDto("bad request","demmane n'exite pas");
        }else {
            demmandRepository.delete(demande.get());
            return new ResponseDto("success","demmande est supprimer",demande.get());
        }
    }

    @Override
    public Long count() {
        return demmandRepository.count();
    }
}
