package com.novick.customers.menu.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "serving_sizes")
public class ServingSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String item;
    private String unit;
    private String size;
    private String brand;
    private String description;
    private String gtin;
    private Integer categoryBreakfastId;
    private Integer categoryLunchSupperId;
    private Integer categorySnackId;
    private Double minimumViableServingSize;
    private Integer minimumServingSizeUomId;
    private Double credibilityValue;
    private Integer credibilityUomId;
    private Boolean additionalCredibility;
    private Integer additionalCredibilityMealId;
    private Integer additionalCredibilityCategoryId;
    private Double additionalCredibilityValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public Integer getCategoryBreakfastId() {
        return categoryBreakfastId;
    }

    public void setCategoryBreakfastId(Integer categoryBreakfastId) {
        this.categoryBreakfastId = categoryBreakfastId;
    }

    public Integer getCategoryLunchSupperId() {
        return categoryLunchSupperId;
    }

    public void setCategoryLunchSupperId(Integer categoryLunchSupperId) {
        this.categoryLunchSupperId = categoryLunchSupperId;
    }

    public Integer getCategorySnackId() {
        return categorySnackId;
    }

    public void setCategorySnackId(Integer categorySnackId) {
        this.categorySnackId = categorySnackId;
    }

    public Double getMinimumViableServingSize() {
        return minimumViableServingSize;
    }

    public void setMinimumViableServingSize(Double minimumViableServingSize) {
        this.minimumViableServingSize = minimumViableServingSize;
    }

    public Integer getMinimumServingSizeUomId() {
        return minimumServingSizeUomId;
    }

    public void setMinimumServingSizeUomId(Integer maximumServingSizeUomId) {
        this.minimumServingSizeUomId = maximumServingSizeUomId;
    }

    public Double getCredibilityValue() {
        return credibilityValue;
    }

    public void setCredibilityValue(Double credibilityValue) {
        this.credibilityValue = credibilityValue;
    }

    public Integer getCredibilityUomId() {
        return credibilityUomId;
    }

    public void setCredibilityUomId(Integer credibilityUomId) {
        this.credibilityUomId = credibilityUomId;
    }

    public Boolean getAdditionalCredibility() {
        return Objects.nonNull(additionalCredibility) && additionalCredibility;
    }

    public void setAdditionalCredibility(Boolean additionalCredibility) {
        this.additionalCredibility = additionalCredibility;
    }

    public Integer getAdditionalCredibilityMealId() {
        return additionalCredibilityMealId;
    }

    public void setAdditionalCredibilityMealId(Integer additionalCredibilityMealId) {
        this.additionalCredibilityMealId = additionalCredibilityMealId;
    }

    public Integer getAdditionalCredibilityCategoryId() {
        return additionalCredibilityCategoryId;
    }

    public void setAdditionalCredibilityCategoryId(Integer additionalCredibilityCategoryId) {
        this.additionalCredibilityCategoryId = additionalCredibilityCategoryId;
    }

    public Double getAdditionalCredibilityValue() {
        return additionalCredibilityValue;
    }

    public void setAdditionalCredibilityValue(Double additionalCredibilityValue) {
        this.additionalCredibilityValue = additionalCredibilityValue;
    }
}
