package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Category;
import com.ecommerce.ecommerce_multi_vende.services.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categorie")
public class CategorieController {
    private CategoryService categoryService;

    public CategorieController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/add")
    public ResponseDto addCategorie(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @GetMapping("/all")
    public ResponseDto allCategories(){
        return categoryService.findAllCategory();
    }
}
