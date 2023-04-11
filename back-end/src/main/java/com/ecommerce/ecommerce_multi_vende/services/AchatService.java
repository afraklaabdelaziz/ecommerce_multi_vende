package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Achat;

public interface AchatService {
    public ResponseDto addAchat(Achat achat);
    public ResponseDto findAllAchat();
    public ResponseDto findAchat(Long idAchat);
    Integer countByVendeur();
}
