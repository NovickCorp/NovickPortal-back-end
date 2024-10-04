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
        List<CreditableTable> creditableTables = new ArrayList<>();

        var vegetable = new CreditableTable();
        vegetable.setCategoryId(3);

        vegetable.setBreakfastAgesOneToTwo(0.25);
        vegetable.setBreakfastAgesThreeToFive(0.5);
        vegetable.setBreakfastAgesSixToEighteen(0.5);
        vegetable.setBreakfastAdult(0.5);

        vegetable.setLunchSupperAgesOneToTwo(0.125);
        vegetable.setLunchSupperAgesThreeToFive(0.25);
        vegetable.setLunchSupperAgesSixToEighteen(0.5);
        vegetable.setLunchSupperAdult(0.5);

        vegetable.setSnackAgesOneToTwo(0.5);
        vegetable.setSnackAgesThreeToFive(0.5);
        vegetable.setSnackAgesSixToEighteen(0.75);
        vegetable.setSnackAdult(0.5);

        creditableTables.add(vegetable);

        var fruit = new CreditableTable();
        fruit.setCategoryId(2);

        fruit.setBreakfastAgesOneToTwo(0.25);
        fruit.setBreakfastAgesThreeToFive(0.5);
        fruit.setBreakfastAgesSixToEighteen(0.5);
        fruit.setBreakfastAdult(0.5);

        fruit.setLunchSupperAgesOneToTwo(0.125);
        fruit.setLunchSupperAgesThreeToFive(0.25);
        fruit.setLunchSupperAgesSixToEighteen(0.25);
        fruit.setLunchSupperAdult(0.5);

        fruit.setSnackAgesOneToTwo(0.5);
        fruit.setSnackAgesThreeToFive(0.5);
        fruit.setSnackAgesSixToEighteen(0.75);
        fruit.setSnackAdult(0.5);

        creditableTables.add(fruit);

        var milk = new CreditableTable();
        milk.setCategoryId(7);

        milk.setBreakfastAgesOneToTwo(0.5);
        milk.setBreakfastAgesThreeToFive(0.75);
        milk.setBreakfastAgesSixToEighteen(1.0);
        milk.setBreakfastAdult(1.0);

        milk.setLunchSupperAgesOneToTwo(0.5);
        milk.setLunchSupperAgesThreeToFive(0.75);
        milk.setLunchSupperAgesSixToEighteen(1.0);
        milk.setLunchSupperAdult(1.0);

        milk.setSnackAgesOneToTwo(0.5);
        milk.setSnackAgesThreeToFive(0.5);
        milk.setSnackAgesSixToEighteen(1.0);
        milk.setSnackAdult(1.0);

        creditableTables.add(milk);

        var grains = new CreditableTable();
        grains.setCategoryId(6);

        grains.setBreakfastAgesOneToTwo(0.5);
        grains.setBreakfastAgesThreeToFive(0.5);
        grains.setBreakfastAgesSixToEighteen(1.0);
        grains.setBreakfastAdult(2.0);

        grains.setLunchSupperAgesOneToTwo(0.5);
        grains.setLunchSupperAgesThreeToFive(0.5);
        grains.setLunchSupperAgesSixToEighteen(1.0);
        grains.setLunchSupperAdult(2.0);

        grains.setSnackAgesOneToTwo(0.5);
        grains.setSnackAgesThreeToFive(0.5);
        grains.setSnackAgesSixToEighteen(1.0);
        grains.setSnackAdult(1.0);

        creditableTables.add(grains);

        var mma = new CreditableTable();
        mma.setCategoryId(5);

        mma.setBreakfastAgesOneToTwo(0);
        mma.setBreakfastAgesThreeToFive(0);
        mma.setBreakfastAgesSixToEighteen(0);
        mma.setBreakfastAdult(0);

        mma.setLunchSupperAgesOneToTwo(1.0);
        mma.setLunchSupperAgesThreeToFive(1.5);
        mma.setLunchSupperAgesSixToEighteen(2.0);
        mma.setLunchSupperAdult(2.0);

        mma.setSnackAgesOneToTwo(0.5);
        mma.setSnackAgesThreeToFive(0.5);
        mma.setSnackAgesSixToEighteen(1.0);
        mma.setSnackAdult(1.0);

        creditableTables.add(mma);

        var extra = new CreditableTable();
        extra.setCategoryId(1);

        extra.setBreakfastAgesOneToTwo(0.0);
        extra.setBreakfastAgesThreeToFive(0.0);
        extra.setBreakfastAgesSixToEighteen(0.0);
        extra.setBreakfastAdult(0.0);

        extra.setLunchSupperAgesOneToTwo(1.0);
        extra.setLunchSupperAgesThreeToFive(1.5);
        extra.setLunchSupperAgesSixToEighteen(2.0);
        extra.setLunchSupperAdult(2.0);

        extra.setSnackAgesOneToTwo(0.5);
        extra.setSnackAgesThreeToFive(0.5);
        extra.setSnackAgesSixToEighteen(1.0);
        extra.setSnackAdult(1.0);

        creditableTables.add(extra);

        return creditableTables;
        //return creditableTableRepository.findAll();
    }

    public double getCreditabilityValue(int categoryId, String mealPatterns, String ageGroup) {
        var all = creditableTable();
        var table = all.stream().filter(category -> category.getCategoryId() == categoryId).findFirst();
        if (table.isEmpty()) {
            throw new IllegalArgumentException("No credit table found for id " + categoryId);
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
        } else if ("snack".equals(mealPatterns)) {
            return switch (ageGroup) {
                case "ages-1-2" -> values.getSnackAgesOneToTwo();
                case "ages-3-5" -> values.getSnackAgesThreeToFive();
                case "ages-6-18" -> values.getSnackAgesSixToEighteen();
                case "adult" -> values.getSnackAdult();
                default -> throw new IllegalArgumentException("Invalid age group " + ageGroup);
            };
        }

        throw new IllegalArgumentException("No credit table found for id " + categoryId);
    }
}
