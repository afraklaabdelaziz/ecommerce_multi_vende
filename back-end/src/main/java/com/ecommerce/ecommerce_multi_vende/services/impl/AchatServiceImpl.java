package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Achat;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.repositories.AchatRepository;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.AchatService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AchatServiceImpl implements AchatService {
    private final AchatRepository achatRepository;
    private UserRepository userRepository;

    public AchatServiceImpl(AchatRepository achatRepository, UserRepository userRepository) {
        this.achatRepository = achatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDto addAchat(Achat achat) {
        return null;
    }

    @Override
    public ResponseDto findAllAchat() {
        return null;
    }

    @Override
    public ResponseDto findAchat(Long idAchat) {
        return null;
    }

    @Override
    public Integer countByVendeur() {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principale;
        Optional<UserApp> userApp = userRepository.findByEmail(user.getUsername());
        return achatRepository.countByProduitVendeur(userApp.get());
    }
}
