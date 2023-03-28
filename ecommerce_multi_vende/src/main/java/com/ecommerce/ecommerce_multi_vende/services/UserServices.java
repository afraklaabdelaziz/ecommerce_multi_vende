package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserServices {
    ResponseDto addUser(UserApp userApp);
    UserDetails findByEmail(String email);
    ResponseDto findByTelephone(String telephone);
    ResponseDto devenirVendeur();
}
