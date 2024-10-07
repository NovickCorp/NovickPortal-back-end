package com.novick.customers.menu;

import com.novick.customers.menu.entities.Category;
import com.novick.customers.menu.models.Credibility;
import com.novick.customers.menu.models.Item;
import com.novick.customers.menu.models.Options;
import com.novick.customers.menu.entities.ServingSize;
import com.novick.customers.menu.service.CategoriesService;
import com.novick.customers.menu.service.CreditabilityTableService;
import com.novick.customers.menu.service.ServingSizeService;
import com.novick.customers.menu.service.UnitsOfMeasurementService;
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

    private Map<Integer, Category> categories;
    private Map<Integer, String> unitsOfMeasurement;

    public MenuBuilderController(ServingSizeService servingSizeService, CategoriesService categoriesService, UnitsOfMeasurementService unitsOfMeasurementService, CreditabilityTableService creditabilityTableService) {
        this.servingSizeService = servingSizeService;
        this.categoriesService = categoriesService;
        this.unitsOfMeasurementService = unitsOfMeasurementService;
        this.creditabilityTableService = creditabilityTableService;

        this.categories = categoriesService.categories();
        this.unitsOfMeasurement = unitsOfMeasurementService.unitsOfMeasurement();
    }

    @GetMapping("/menu-builder")
    public List<ServingSize> allServingSizes() {
        return servingSizeService.findAll();
    }

    @GetMapping("/menu-builder/{mealPattern}")
    public Map<String, List<Item>> servingSizes(@PathVariable String mealPattern) {

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
        } else if ("snack".equals(mealPattern)) {
            return Objects.nonNull(servingSize.getCategorySnackId());
        }
        throw new IllegalArgumentException("Invalid meal pattern: " + mealPattern);
    }

    private Map<String, List<ServingSize>> groupByCategory(String mealPattern) {
        var servingSizeByCategory = new TreeMap<String, List<ServingSize>>();
        allServingSizes().forEach(servingSize -> {
            if (!hasCategory(servingSize, mealPattern)) {
                return;
            }

            var category = categories.get(servingSize.getCategory(mealPattern)).getName();
            if (!category.equals("Fruit/Vegetable")) {
                var list = servingSizeByCategory.computeIfAbsent(category, k -> new ArrayList<>());
                list.add(servingSize);
            }
            servingSizeByCategory.computeIfAbsent("Fruit", k -> new ArrayList<>()).add(servingSize);
            servingSizeByCategory.computeIfAbsent("Vegetable", k -> new ArrayList<>()).add(servingSize);
        });

        return servingSizeByCategory;
    }

    @GetMapping("/menu-builder/{mealPattern}/{ageGroup}")
    public Map<String, List<Item>> servingSizes(@PathVariable String mealPattern, @PathVariable String ageGroup) {

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

    private List<Options> getOptions(ServingSize servingSize, String category, String mealPattern, String ageGroup) {
        var size = servingSize.getMinimumViableServingSize();
        var uomId = servingSize.getMinimumServingSizeUomId();
        var value = servingSize.getCredibilityValue();

        var categoryId = categories.entrySet().stream().filter(entry -> entry.getValue().getName().equals(category)).map(Map.Entry::getKey).findFirst().orElse(-1);
        var target = creditabilityTableService.getCreditabilityValue(categoryId, mealPattern, ageGroup);

        var count = 1;
        var list = new ArrayList<Options>();

        while (size <= 2.0) {
            System.out.println("value: " + value + " target: " + target + " score " + (value * 100.0 / target));
            var option = new Options(count++, String.format("%s %s", Arithmetic.toFractionString(size), unitsOfMeasurement.get(uomId)), new Credibility(category, size, target), null);
            list.add(option);
            size += servingSize.getMinimumViableServingSize();
        }

        return list;
    }

}
