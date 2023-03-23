package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Adresse;

public interface AdresseService {
    public ResponseDto addAdresse(Adresse adresse);
    public ResponseDto updateAdresse(Adresse adresse);
    public ResponseDto findAdresseById(Long idAdresse);
}
