package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Category;

public interface CategoryService {
    public ResponseDto addCategory(Category category);
    public ResponseDto deleteCategory(Long idCategory);
    public ResponseDto findAllCategory();
}
