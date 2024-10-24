package com.novick.customers.menu;

import com.novick.customers.menu.entities.Category;
import com.novick.customers.menu.models.Creditability;
import com.novick.customers.menu.models.Item;
import com.novick.customers.menu.models.Options;
import com.novick.customers.menu.entities.ServingSize;
import com.novick.customers.menu.service.*;
import com.novick.customers.util.Arithmetic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MenuBuilderController {

    private final ServingSizeService servingSizeService;
    private final CategoriesService categoriesService;
    private final UnitsOfMeasurementService unitsOfMeasurementService;
    private final CreditabilityTableService creditabilityTableService;
    private final MealsService mealsService;

    private Map<Integer, Category> categories;
    private Map<String, Integer> categoriesIds;
    private Map<Integer, String> unitsOfMeasurement;

    public MenuBuilderController(ServingSizeService servingSizeService, CategoriesService categoriesService, UnitsOfMeasurementService unitsOfMeasurementService, CreditabilityTableService creditabilityTableService, MealsService mealsService) {
        this.servingSizeService = servingSizeService;
        this.categoriesService = categoriesService;
        this.unitsOfMeasurementService = unitsOfMeasurementService;
        this.creditabilityTableService = creditabilityTableService;

        this.categories = categoriesService.categories();
        this.categoriesIds = categories.entrySet().stream().collect(Collectors.toMap(entry -> entry.getValue().getName(), Map.Entry::getKey));
        this.unitsOfMeasurement = unitsOfMeasurementService.unitsOfMeasurement();
        this.mealsService = mealsService;
    }

    @GetMapping("/menu-builder")
    public List<ServingSize> allServingSizes() {
        return servingSizeService.findAll();
    }

    @GetMapping("/menu-builder/{mealPattern}")
    public Map<Integer, List<Item>> servingSizes(@PathVariable String mealPattern) {

        var byCategory = groupByCategory(mealPattern);
        return byCategory.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue()
                                        .stream()
                                        .filter(item -> hasCategory(item, mealPattern))
                                        .map(item -> new Item(item.getId(), item.getItem(), item.getDescription(), Collections.emptyList())).toList()
                        )
                );
    }

    private boolean hasCategory(ServingSize servingSize, String mealPattern) {
        if ("breakfast".equals(mealPattern)) {
            return Objects.nonNull(servingSize.getCategoryBreakfastId());
        } else if ("lunch".equals(mealPattern) || "supper".equals(mealPattern)) {
            return Objects.nonNull(servingSize.getCategoryLunchSupperId());
        } else if ("snack".equals(mealPattern) || "am-snack".equals(mealPattern) || "pm-snack".equals(mealPattern)) {
            return Objects.nonNull(servingSize.getCategorySnackId());
        }
        throw new IllegalArgumentException("Invalid meal pattern: " + mealPattern);
    }

    private Map<Integer, List<ServingSize>> groupByCategory(String mealPattern) {
        var servingSizeByCategory = new TreeMap<Integer, List<ServingSize>>();
        allServingSizes().forEach(servingSize -> {
            if (!hasCategory(servingSize, mealPattern)) {
                return;
            }

            var category = categories.get(servingSize.getCategory(mealPattern));
            if (!category.getName().equals("Fruit/Vegetable")) {
                var list = servingSizeByCategory.computeIfAbsent(category.getId(), k -> new ArrayList<>());
                list.add(servingSize);
            } else {
                servingSizeByCategory.computeIfAbsent(categoriesIds.get("Fruit"), k -> new ArrayList<>()).add(servingSize);
                servingSizeByCategory.computeIfAbsent(categoriesIds.get("Vegetable"), k -> new ArrayList<>()).add(servingSize);
            }
        });

        return servingSizeByCategory;
    }

    @GetMapping("/menu-builder/{mealPattern}/{ageGroup}")
    public Map<Integer, List<Item>> servingSizes(@PathVariable String mealPattern, @PathVariable String ageGroup) {
        var byCategory = groupByCategory(mealPattern);
        return byCategory.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue()
                                        .stream()
                                        .filter(item -> hasCategory(item, mealPattern) && Objects.nonNull(item.getMinimumViableServingSize()))
                                        .map(item -> new Item(item.getId(), item.getItem(), item.getDescription(), getOptions(item, entry.getKey(), mealPattern, ageGroup))).toList()
                        )
                );
    }

    private List<Options> getOptions(ServingSize ingredient, int categoryId, String mealPattern, String ageGroup) {
        var initialSize = ingredient.getMinimumViableServingSize();
        var uomId = ingredient.getMinimumServingSizeUomId();
        var value = ingredient.getCredibilityValue();

        var target = creditabilityTableService.getCreditabilityValue(categoryId, mealPattern, ageGroup);

        var count = 1;
        var list = new ArrayList<Options>();

        var score = (target == 0.0) ? 1.0 : (value / target);
        var alternativeScore = getAdditionalCredibility(ingredient, mealPattern, ageGroup);

        var servingSize = 0.0;
        while (servingSize < 2.0) {
            servingSize += initialSize;
            var option = new Options(count, String.format("%s %s", Arithmetic.toFractionString(servingSize), unitsOfMeasurement.get(uomId)), getCreditability(categoryId, score, count), getCreditability(ingredient.getAdditionalCredibilityCategoryId(), alternativeScore, count));
            list.add(option);
            ++count;
        }

        return list;
    }

    private Creditability getCreditability(Integer categoryId, Double baseScore, int count) {
        if (baseScore == null || categories.get(categoryId).getName().equals("Extra")) {
            return null;
        }

        return new Creditability(categoryId, baseScore * count);
    }

    private Double getAdditionalCredibility(ServingSize servingSize, String mealPattern, String ageGroup) {
        if (!servingSize.getAdditionalCredibility() || !mealsService.mealMap().get(servingSize.getAdditionalCredibilityMealId()).getParameterName().equals(mealPattern)) {
            return null;
        }

        var value = servingSize.getAdditionalCredibilityValue();
        var target = creditabilityTableService.getCreditabilityValue(servingSize.getAdditionalCredibilityCategoryId(), mealPattern, ageGroup);
        return value / target;
    }
}
