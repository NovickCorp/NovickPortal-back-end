package com.novick.customers.menu;

import com.novick.customers.menu.entities.Meal;
import com.novick.customers.menu.service.MealsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MealsController {

    private MealsService mealsService;

    public MealsController(MealsService mealsService) {
        this.mealsService = mealsService;
    }

    @GetMapping("/meals")
    public List<Meal> meals() {
        return mealsService.findAll();
    }
}
