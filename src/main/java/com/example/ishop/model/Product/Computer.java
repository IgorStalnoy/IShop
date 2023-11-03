package com.example.ishop.model.Product;

public class Computer extends Product {
    private final String brand;
    private final String type;
    private final int processorSpeed;
    private final int ramSize;
    private final int hddSize;

    public Computer(int id, int categoryId, int price, int count, String model, String brand, String type, int processorSpeed, int ramSize, int hddSize) {
        super(id, categoryId, price, count, model);
        this.brand = brand;
        this.type = type;
        this.processorSpeed = processorSpeed;
        this.ramSize = ramSize;
        this.hddSize = hddSize;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public int getProcessorSpeed() {
        return processorSpeed;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getHddSize() {
        return hddSize;
    }

    @Override
    public String toString() {
        return "id: " + getId() +
                ", " + getCategoriesName().get(getCategoryId()) +
                ", " + brand +
                ", " + getModel() +
                ", " + type +
                ", price: " + getPrice() + "$ " +
                ", count: " + getCount() +
                ", " + processorSpeed + "MHz." +
                ", " + ramSize + "MB" +
                ", " + hddSize + "MB";
    }

}
