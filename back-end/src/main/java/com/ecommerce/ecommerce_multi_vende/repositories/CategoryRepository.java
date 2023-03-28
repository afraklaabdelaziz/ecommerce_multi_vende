package com.ecommerce.ecommerce_multi_vende.repositories;

import com.ecommerce.ecommerce_multi_vende.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
