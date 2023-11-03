package com.example.ishop.model;

import com.example.ishop.model.Product.Product;

import java.util.List;

public class Order {

    private final List<Product> productsList;
    private final String status;
    private final boolean isPaid;
    private final String transactionId;
    private final String deliveryAddress;
    private final Customer customer;

    public Order(List<Product> productsList, String status, boolean isPaid, String transactionId, String deliveryAddress, Customer customer) {
        this.productsList = productsList;
        this.status = status;
        this.isPaid = isPaid;
        this.transactionId = transactionId;
        this.deliveryAddress = deliveryAddress;
        this.customer = customer;
    }
}
