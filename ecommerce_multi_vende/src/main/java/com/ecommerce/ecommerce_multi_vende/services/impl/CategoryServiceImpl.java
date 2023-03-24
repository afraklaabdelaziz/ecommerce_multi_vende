package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Category;
import com.ecommerce.ecommerce_multi_vende.repositories.CategoryRepository;
import com.ecommerce.ecommerce_multi_vende.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseDto addCategory(Category category) {
        if (category == null){
            return new ResponseDto("bad request","category est null");
        }else{
            categoryRepository.save(category);
            return new ResponseDto("success","categorie a ete ajouter");
        }
    }

    @Override
    public ResponseDto deleteCategory(Long idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if (!category.isPresent()){
            return new ResponseDto("bad request","categorie n'existe pas");
        }else {
            categoryRepository.deleteById(idCategory);
            return new ResponseDto("success","categorie est supprimer");
        }
    }

    @Override
    public ResponseDto findAllCategory() {
       return new ResponseDto("success","tout les categories",categoryRepository.findAll());
    }
}
