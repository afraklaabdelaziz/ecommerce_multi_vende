package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;

import javax.mail.MessagingException;

public interface UserServices {
    ResponseDto addUser(UserApp userApp);
    ResponseDto findByEmail(String email);
    ResponseDto findByTelephone(String telephone);
    ResponseDto findAll();
    ResponseDto findUserAppByEmail(String email);
    ResponseDto updatUser(UserApp userApp);
    ResponseDto devenirVendeur(DemandeVendeur demandeVendeur);
    ResponseDto responseDemmandeVendeur(Boolean response,Long idDemmande);
    Long count();
}
