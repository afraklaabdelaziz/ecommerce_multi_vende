package com.ecommerce.ecommerce_multi_vende.repositories;

import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemmandRepository extends JpaRepository<DemandeVendeur,Long> {
    DemandeVendeur findDemandeVendeurByUserApp(UserApp userApp);
}
