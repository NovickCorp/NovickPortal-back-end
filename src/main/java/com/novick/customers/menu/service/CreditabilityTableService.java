package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.CreditableTable;
import com.novick.customers.menu.repositories.CreditableTableRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditabilityTableService {

    private CreditableTableRepository creditableTableRepository;
    private CategoriesService categoriesService;

    public CreditabilityTableService(CreditableTableRepository creditableTableRepository, CategoriesService categoriesService) {
        this.creditableTableRepository = creditableTableRepository;
        this.categoriesService = categoriesService;
    }

    private List<CreditableTable> creditableTable() {
        return creditableTableRepository.findAll();
    }

    @Cacheable("creditability")
    public double getCreditabilityValue(int categoryId, String mealPatterns, String ageGroup) {
        var all = creditableTable();
        var table = all.stream().filter(category -> category.getCategoryId() == categoryId).findFirst();
        if (table.isEmpty()) {
            throw new IllegalArgumentException("No credit table found for item " + categoryId);
        }

        var values = table.get();
        if ("breakfast".equals(mealPatterns)) {
            return switch (ageGroup) {
              case "ages-1-2" -> values.getBreakfastAgesOneToTwo();
              case "ages-3-5" -> values.getBreakfastAgesThreeToFive();
              case "ages-6-18" -> values.getBreakfastAgesSixToEighteen();
              case "adult" -> values.getBreakfastAdult();
              default -> throw new IllegalArgumentException("Invalid age group " + ageGroup);
            };
        } else if ("lunch".equals(mealPatterns) || "supper".equals(mealPatterns)) {
            return switch (ageGroup) {
                case "ages-1-2" -> values.getLunchSupperAgesOneToTwo();
                case "ages-3-5" -> values.getLunchSupperAgesThreeToFive();
                case "ages-6-18" -> values.getLunchSupperAgesSixToEighteen();
                case "adult" -> values.getLunchSupperAdult();
                default -> throw new IllegalArgumentException("Invalid age group " + ageGroup);
            };
        } else if ("snack".equals(mealPatterns) || "am-snack".equals(mealPatterns) || "pm-snack".equals(mealPatterns)) {
            return switch (ageGroup) {
                case "ages-1-2" -> values.getSnackAgesOneToTwo();
                case "ages-3-5" -> values.getSnackAgesThreeToFive();
                case "ages-6-18" -> values.getSnackAgesSixToEighteen();
                case "adult" -> values.getSnackAdult();
                default -> throw new IllegalArgumentException("Invalid age group " + ageGroup);
            };
        }

        throw new IllegalArgumentException("No credit table found for item " + categoryId);
    }
}
