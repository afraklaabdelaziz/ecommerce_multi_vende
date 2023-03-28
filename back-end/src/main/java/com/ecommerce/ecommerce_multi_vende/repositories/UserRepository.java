package com.ecommerce.ecommerce_multi_vende.repositories;

import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp,Long> {
    Optional<UserApp> findByEmail(String email);
    Optional<UserApp> findByTelephone(String telephone);
}
