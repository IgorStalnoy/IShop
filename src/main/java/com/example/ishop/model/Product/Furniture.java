package com.example.ishop.model.Product;

public class Furniture extends Product {
    private final String material;
    private final String color;

    public Furniture(int id, int categoryId, int price, int count, String model, String material, String color) {
        super(id, categoryId, price, count, model);
        this.material = material;
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "id: " + getId() +
                ", " + getCategoriesName().get(getCategoryId()) +
                ", " + getModel() +
                ", material: " + material +
                ", color:" + color +
                ", price: " + getPrice() + "$ " +
                ", count: " + getCount();

    }
}
