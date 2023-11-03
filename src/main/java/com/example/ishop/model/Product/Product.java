package com.example.ishop.model.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class Product {
    private final Map<Integer, String> categoriesName = new HashMap<>();
    private final int id;
    private final int categoryId;
    private final int price;
    private final int count;
    private String model;

    public Product(int id, int categoryId, int price, int count, String model) {
        this.id = id;
        this.categoryId = categoryId;
        this.price = price;
        this.count = count;
        this.model = model;
        categoriesName.put(1, "Computer");
        categoriesName.put(2, "Furniture");
        categoriesName.put(3, "Household chemicals");
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public Map<Integer, String> getCategoriesName() {
        return categoriesName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", category: " + categoriesName.get(categoryId) +
                ", price:" + price + "$" +
                ", count:" + count +
                " " + model;
    }

}
