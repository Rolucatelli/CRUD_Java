package projeto.crud_java;

import java.sql.*;

public class Product extends Object{

    private String name;
    private String shortDescription;
    private String brand;
    private String category;
    private double listPrice;
    private double cost;


    public Product(String name, String shortDescription, String brand, String category, double listPrice, double cost) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException("Name can't be null");

        this.name = name;
        this.shortDescription = shortDescription;
        this.brand = brand;
        this.category = category;
        this.listPrice = listPrice;
        this.cost = cost;
    }

    public Product(String name, double listPrice) {
        this(name, "None", "Unknown", "Unknown", listPrice, -1.0);
    }

    public Product(String name, String shortDescription, double listPrice) {
        this(name, shortDescription, "Unknown", "Unknown", listPrice, -1.0);
    }

    public Product(String name, String shortDescription, String brand, double listPrice) {
        this(name, shortDescription, brand, "Unknown", listPrice, -1.0);
    }

    public Product(String name, String shortDescription, String brand, String category, double listPrice) {
        this(name, shortDescription, brand, category, listPrice, -1.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
