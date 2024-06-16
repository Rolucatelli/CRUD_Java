package projeto.crud_java.beans;

import java.sql.*;

public class Product {

    private String name;
    private String shortDescription;
    private String brand;
    private String category;
    private double listPrice;
    private double cost;


    public Product(String name, String shortDescription, String brand, String category, double listPrice, double cost) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException("Name can't be null");
        if (listPrice < 0)
            throw new IllegalArgumentException("Name can't be null");

        this.name = name;
        this.listPrice = listPrice;
        this.shortDescription = (shortDescription == null) ? "None" : shortDescription;
        this.brand = (brand == null) ? "Unkown" : brand;
        this.category = (category == null) ? "Unkown" : category;
        this.cost = (cost < 0) ? -1 : cost;
    }

    public Product(String name, double listPrice) {
        this(name, null, null, null, listPrice, -1.0);
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
