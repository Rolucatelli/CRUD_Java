package projeto.crud_java.tables;


import projeto.crud_java.beans.Product;
import projeto.crud_java.dbConection.DataBaseUtility;

import java.sql.SQLException;
import java.util.LinkedList;

public class ProductController {

    public static void insert(Product product) throws SQLException {
        String pName = product.getName();
        double price = product.getListPrice();
        StringBuilder col = new StringBuilder("(");
        StringBuilder lin = new StringBuilder("(");

        col.append("id, productName");
        lin.append(product.getId());
        lin.append(", \"").append(pName).append("\"");

        if (!(product.getShortDescription().isEmpty() || product.getShortDescription() == (null))) {
            col.append(", shortDescription");
            lin.append(", \"").append(product.getShortDescription()).append("\"");
        }

        if (!(product.getBrand().isEmpty() || product.getBrand() == (null))) {
            col.append(", brand");
            lin.append(", \"").append(product.getBrand()).append("\"");
        }

        if (!(product.getCategory().isEmpty() || product.getCategory() == (null))) {
            col.append(", category");
            lin.append(", \"").append(product.getCategory()).append("\"");
        }

        col.append(", listPrice");
        lin.append(", ").append(price);

        if (product.getCost() >= 0.0) {
            col.append(", cost");
            lin.append(", ").append(product.getCost());
        }

        col.append(")");
        lin.append(")");
        DataBaseUtility.insert(col.toString(), lin.toString());
    }

    public static void update(Product oldP, Product newP) throws SQLException {

        boolean firstItem = true;
        StringBuilder sql = new StringBuilder();

        if (!oldP.getName().equals(newP.getName())) {
            sql.append("productName = \"").append(newP.getName()).append("\"");
            firstItem = false;
        }

        if (!((newP.getShortDescription() == null && oldP.getShortDescription() == null))) {
            if (oldP.getShortDescription() == null) {
                if (!(newP.getShortDescription().equals(oldP.getShortDescription()))) {
                    if (!firstItem) sql.append(", ");
                    sql.append("shortDescription = \"").append(newP.getShortDescription()).append("\"");
                    firstItem = false;
                }
            } else {
                if (!firstItem) sql.append(", ");
                sql.append("shortDescription = null");
                firstItem = false;
            }
        }

        if (!((newP.getBrand() == null && oldP.getBrand() == null))) {
            if (oldP.getBrand() == null) {
                if (!(newP.getBrand().equals(oldP.getBrand()))) {
                    if (!firstItem) sql.append(", ");
                    sql.append("brand = \"").append(newP.getBrand()).append("\"");
                    firstItem = false;
                }
            } else {
                if (!firstItem) sql.append(", ");
                sql.append("brand = null");
                firstItem = false;
            }
        }

        if (!(newP.getCategory() == null && oldP.getCategory() == null)) {
            if (oldP.getCategory() == null) {
                if (!(newP.getCategory().equals(oldP.getCategory()))) {
                    if (!firstItem) sql.append(", ");
                    sql.append("category = \"").append(newP.getCategory()).append("\"");
                    firstItem = false;
                }
            } else {
                if (!firstItem) sql.append(", ");
                sql.append("category = null");
                firstItem = false;
            }
        }

        if (oldP.getListPrice() != newP.getListPrice()) {
            if (!firstItem) sql.append(", ");
            sql.append("listPrice = ").append(newP.getListPrice());
            firstItem = false;
        }

        if (!(oldP.getCost() == newP.getCost())) {
            if (!firstItem) sql.append(", ");
            if (newP.getCost() < 0) {
                sql.append("cost = null");
            } else {
                sql.append("cost = ").append(newP.getCost());
            }
        }

        DataBaseUtility.update(sql.toString(), oldP.getId());

    }

    public static Product consult(int id) throws SQLException {
        return DataBaseUtility.getProduct(id);
    }

    public static void delete(int id) throws SQLException {
        Product temp = DataBaseUtility.getProduct(id);
        if (temp == null) {
            throw new NullPointerException("Product is null. It must be something");
        }
        DataBaseUtility.delete(id);
    }

    public static int getIdByProductName(String name) throws SQLException {
        return DataBaseUtility.getProductByName(name).getId();
    }

    public static LinkedList<Product> getAllProducts() throws SQLException {
        return DataBaseUtility.getAllProducts();
    }
}
