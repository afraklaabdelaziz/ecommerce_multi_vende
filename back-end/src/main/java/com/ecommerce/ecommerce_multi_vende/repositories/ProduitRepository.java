package com.ecommerce.ecommerce_multi_vende.repositories;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Produit;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
    Page<Produit> findAllByVendeur(UserApp userApp,Pageable pageable);
    Integer countByVendeur(UserApp userApp);
}
