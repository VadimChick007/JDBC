package com.jdbc;


public class Product {
    private int id;

    private String name;

    private double price;

    private int shop_ID;

    public Product(){
    }

    public Product(int id, String name, double price, int shop_ID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shop_ID = shop_ID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", shop_ID=" + shop_ID +
                '}';
    }
}
