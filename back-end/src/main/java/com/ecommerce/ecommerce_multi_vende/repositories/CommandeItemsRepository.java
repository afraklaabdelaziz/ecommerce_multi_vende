package com.ecommerce.ecommerce_multi_vende.repositories;

import com.ecommerce.ecommerce_multi_vende.entities.CommandeItems;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeItemsRepository extends JpaRepository<CommandeItems,Long> {
    Integer countByProduitVendeur(UserApp userApp);
}
