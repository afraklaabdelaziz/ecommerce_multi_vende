package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;

public interface UserServices {
    ResponseDto addUser(UserApp userApp);
    ResponseDto findByEmail(String email);
    ResponseDto findByTelephone(String telephone);

    ResponseDto findUserAppByEmail(String email);

    ResponseDto devenirVendeur(DemandeVendeur demandeVendeur);
    ResponseDto responseDemmandeVendeur(Boolean response,Long idDemmande);
}
