package com.novick.customers.menu.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private List<Item> vegetables;
    private List<Item> fruits;
    private List<Item> milks;
    private List<Item> grains;
    private List<Item> mmas;
    private List<Item> extras;

    private Category() {
        vegetables = new ArrayList<>();
        fruits = new ArrayList<>();
        milks = new ArrayList<>();
        grains = new ArrayList<>();
        mmas = new ArrayList<>();
        extras = new ArrayList<>();
    }

    public void addToVegetables(Item item) {
        vegetables.add(item);
    }

    public void addToFruits(Item item) {
        fruits.add(item);
    }

    public void addToMilks(Item item) {
        milks.add(item);
    }

    public void addToGrains(Item item) {
        grains.add(item);
    }

    public void addToMmas(Item item) {
        mmas.add(item);
    }

    public void addToExtras(Item item) {
        extras.add(item);
    }
}
