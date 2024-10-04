package com.novick.customers.menu;

import com.novick.customers.menu.entities.Category;
import com.novick.customers.menu.entities.CreditableTable;
import com.novick.customers.menu.entities.UnitOfMeasurement;
import com.novick.customers.menu.models.Credibility;
import com.novick.customers.menu.models.Item;
import com.novick.customers.menu.models.Options;
import com.novick.customers.menu.repositories.ServingSizeRepository;
import com.novick.customers.menu.entities.ServingSize;
import com.novick.customers.menu.repositories.UnitsOfMeasurementRepository;
import com.novick.customers.menu.service.CategoriesService;
import com.novick.customers.menu.service.CreditabilityTableService;
import com.novick.customers.menu.service.UnitsOfMeasurementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class MenuBuilderController {

    private final ServingSizeRepository servingSizeRepository;
    private final CategoriesService categoriesService;
    private final UnitsOfMeasurementService unitsOfMeasurementService;
    private final CreditabilityTableService creditabilityTableService;

    private Map<Integer, Category> categories;
    private Map<Integer, String> unitsOfMeasurement;

    public MenuBuilderController(ServingSizeRepository servingSizeRepository, CategoriesService categoriesService, UnitsOfMeasurementService unitsOfMeasurementService, CreditabilityTableService creditabilityTableService) {
        this.servingSizeRepository = servingSizeRepository;
        this.categoriesService = categoriesService;
        this.unitsOfMeasurementService = unitsOfMeasurementService;
        this.creditabilityTableService = creditabilityTableService;

        this.categories = categoriesService.categories();
        this.unitsOfMeasurement = unitsOfMeasurementService.unitsOfMeasurement();
    }

    record CategoryMealPattern(String category, Item item) {}

    @GetMapping("/menu-builder/{mealPattern}")
    public Map<String, List<Item>> servingSizes(@PathVariable String mealPattern) {

        var getCategoryId = getCategoryId(mealPattern);
        return servingSizeRepository.findAll()
                                    .stream()
                                    .filter(item -> Objects.nonNull(getCategoryId.apply(item)))
                                    .map(item -> {
                                        var categoryId = getCategoryId.apply(item);
                                        return new CategoryMealPattern(categories.get(categoryId).getName(), Item.from(item, unitsOfMeasurement.get(item.getCredibilityUomId()), Collections.emptyList()));
                                    })
                                    .collect(Collectors.groupingBy(
                                            CategoryMealPattern::category,
                                            Collectors.mapping(CategoryMealPattern::item, Collectors.toList())
                                    ));
    }

    @GetMapping("/menu-builder/{mealPattern}/{ageGroup}")
    public Map<String, List<Item>> servingSizes(@PathVariable String mealPattern, @PathVariable String ageGroup) {

        var getCategoryId = getCategoryId(mealPattern);

        return servingSizeRepository.findAll()
                .stream()
                .filter(item -> Objects.nonNull(getCategoryId.apply(item)) && getCategoryId.apply(item) != 4)
                .map(item -> {
                    var categoryId = getCategoryId.apply(item);
                    var targetCreditability = creditabilityTableService.getCreditabilityValue(categoryId, mealPattern, ageGroup);
                    return new CategoryMealPattern(categories.get(categoryId).getName(), Item.from(item, unitsOfMeasurement.get(item.getCredibilityUomId()), getOptions(item, categoryId, targetCreditability)));
                })
                .collect(Collectors.groupingBy(
                        CategoryMealPattern::category,
                        Collectors.mapping(CategoryMealPattern::item, Collectors.toList())
                ));
    }

    private List<Options> getOptions(ServingSize servingSize, int categoryId, double targetCreditability) {
        var value = servingSize.getMinimumViableServingSize();
        if (value == null) {
            return Collections.emptyList();
        }

        List<Options> options = new ArrayList<>();
        var count = 1;
        var altenate = servingSize.getAdditionalCredibility() ? new Credibility(categories.get(servingSize.getAdditionalCredibilityCategoryId()).getName(), servingSize.getAdditionalCredibilityValue()) : null;
        var start = new Options(count++, String.format("%f %s", value, unitsOfMeasurement.get(servingSize.getMinimumServingSizeUomId()), servingSize.getDescription()), new Credibility(categories.get(categoryId).getName(), servingSize.getCredibilityValue()), altenate);
        options.add(start);
        do {
            value += servingSize.getMinimumViableServingSize();
            altenate = servingSize.getAdditionalCredibility() ? new Credibility(categories.get(servingSize.getAdditionalCredibilityCategoryId()).getName(), servingSize.getAdditionalCredibilityValue()) : null;
            options.add(new Options(count++, String.format("%f %s", value, unitsOfMeasurement.get(servingSize.getMinimumServingSizeUomId()), servingSize.getDescription()), new Credibility(categories.get(categoryId).getName(), servingSize.getCredibilityValue()), altenate));
        } while (value <= 2.0);

        return options;
    }

    @GetMapping("/menu-builder")
    public List<ServingSize> servingSizes() {
        return servingSizeRepository.findAll();
    }

    private static Function<ServingSize, Integer> getCategoryId(String mealPattern) {
        return switch (mealPattern) {
            case "breakfast" -> ServingSize::getCategoryBreakfastId;
            case "lunch", "supper" -> ServingSize::getCategoryLunchSupperId;
            case "snack" -> ServingSize::getCategorySnackId;
            default -> throw new AssertionError();
        };
    }

}
