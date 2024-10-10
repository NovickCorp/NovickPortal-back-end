package com.novick.customers.menu;

import com.novick.customers.menu.entities.Category;
import com.novick.customers.menu.service.CategoriesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/categories")
    public List<Category> categories() {
        return categoriesService.findAll();
    }
}
