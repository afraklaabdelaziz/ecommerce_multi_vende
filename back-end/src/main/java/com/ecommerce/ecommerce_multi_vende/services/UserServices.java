package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;

public interface UserServices {
    ResponseDto addUser(UserApp userApp);
    ResponseDto findByEmail(String email);
    ResponseDto findByTelephone(String telephone);
    ResponseDto devenirVendeur();
}
