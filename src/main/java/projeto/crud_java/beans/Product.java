package projeto.crud_java.beans;

import projeto.crud_java.tables.ProductController;

import java.sql.*;
import java.util.*;

public class Product {

    private static HashSet<Integer> idSet = new HashSet<>();
    private int id;
    private String name;
    private String shortDescription;
    private String brand;
    private String category;
    private double listPrice;
    private double cost;
    private String costString;

    public Product(int id, String name, String shortDescription, String brand, String category, double listPrice, double cost) throws IllegalArgumentException {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name can't be null", new Throwable("name == null"));
        if (listPrice < 0)
            throw new IllegalArgumentException("List Price can't be negative", new Throwable("listPrice < 0"));

        this.id = id;
        this.name = name;
        this.listPrice = listPrice;
        this.shortDescription = shortDescription;
        this.brand = brand;
        this.category = category;
        this.cost = cost;
        this.costString = "" + cost;
    }

    public Product(int id, String name, String shortDescription, String brand, String category, double listPrice, String cost) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name can't be null", new Throwable("name == null"));
        if (listPrice < 0)
            throw new IllegalArgumentException("List Price can't be negative", new Throwable("listPrice < 0"));

        this.id = id;
        this.name = name;
        this.listPrice = listPrice;
        this.shortDescription = shortDescription;
        this.brand = brand;
        this.category = category;
        this.costString = cost;
    }

    public Product(int id, String name, String shortDescription, String brand, String category, double listPrice) {
        this(id, name, shortDescription, brand, category, listPrice, -1.0);
    }

    public Product(int id, String name, String shortDescription, String brand, double listPrice) {
        this(id, name, shortDescription, brand, null, listPrice);
    }

    public Product(int id, String name, String shortDescription, double listPrice) {
        this(id, name, shortDescription, null, listPrice);
    }

    public Product(int id, String name, double listPrice) {
        this(id, name, null, listPrice);
    }

    public static int findNextId() {
        int i = 1;
        while (!idSet.add(i)) {
            i++;
        }
        return i;
    }

    public static void removeId(int id) {
        idSet.remove(id);
    }

    public static void readIds() throws SQLException {
        LinkedList<Product> linkedList = ProductController.getAllProducts();
        for (int i = 0; i < linkedList.size(); i++) {
            idSet.add(linkedList.get(i).getId());
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public double getListPrice() {
        return listPrice;
    }

    public double getCost() {
        return cost;
    }

    public String getCostString() {
        return costString;
    }
}
