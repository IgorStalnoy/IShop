package com.example.ishop.model.Product;

public class HouseholdChemicals extends Product {
    private final String brand;
    private final String appointment;
    private final String smell;

    public HouseholdChemicals(int id, int categoryId, int price, int count, String model, String brand, String appointment, String smell) {
        super(id, categoryId, price, count, model);
        this.brand = brand;
        this.appointment = appointment;
        this.smell = smell;
    }

    public String getAppointment() {
        return appointment;
    }

    public String getSmell() {
        return smell;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "id: " + getId() +
                ", " + getCategoriesName().get(getCategoryId()) +
                ", " + brand +
                ", " + getModel() +
                ", " + appointment +
                ", smell:" + smell +
                ", price: " + getPrice() + "$ " +
                ", count: " + getCount();
    }

}
