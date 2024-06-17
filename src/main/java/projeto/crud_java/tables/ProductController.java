package projeto.crud_java.tables;

import javafx.scene.chart.PieChart;
import projeto.crud_java.beans.Product;
import projeto.crud_java.dbConection.DataBaseUtility;

import java.sql.SQLException;


public class ProductController {
    //TODO: Implementar os métodos de atualizar, consultar, cadastrar e remover produtos do BD

    public static void insert(Product product) throws SQLException {
        String pName = product.getName();
        double price = product.getListPrice();
        StringBuilder col = new StringBuilder("(");
        StringBuilder lin = new StringBuilder("(");

        col.append("productName");
        lin.append("\"" + pName + "\"");

        if (!(product.getShortDescription().equals("None") || product.getShortDescription().equals(null))) {
            col.append(", shortDescription");
            lin.append(", \"" + product.getShortDescription() + "\"");
        }

        if (!(product.getBrand().equals("Unknow") || product.getBrand().equals(null))) {
            col.append(", brand");
            lin.append(", \"" + product.getBrand() + "\"");
        }

        if (!(product.getCategory().equals("Unknow") || product.getCategory().equals(null))) {
            col.append(", category");
            lin.append(", \"" + product.getCategory() + "\"");
        }

        col.append(", listPrice");
        lin.append(", " + price);

        if (!(product.getCost() == -1.0)) {
            col.append(", cost");
            lin.append(", " + product.getCost());
        }

        col.append(")");
        lin.append(")");

        DataBaseUtility.insert(col.toString(), lin.toString());
    }

    public static void update(Product product) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("productName = " + "\"" + product.getName() + "\"");

        if (!(product.getShortDescription().equals("None") || product.getShortDescription().equals(null))) {
            sql.append(", shortDescription = " + "\"" + product.getShortDescription() + "\"");
        }

        if (!(product.getBrand().equals("Unknow") || product.getBrand().equals(null))) {
            sql.append(", brand = " + "\"" + product.getBrand() + "\"");
        }

        if (!(product.getCategory().equals("Unknow") || product.getCategory().equals(null))) {
            sql.append(", category = " + "\"" + product.getCategory() + "\"");
        }

        sql.append(", listPrice = " + product.getListPrice());


        if (!(product.getCost() == -1.0)) {
            sql.append(", cost = " + product.getCost());
        }

        System.out.println(sql);

        DataBaseUtility.update(sql.toString(), product.getName());

    }

    public static Product consult(String pName) throws SQLException {
        return DataBaseUtility.getProduct(pName);
    }

    public static void delete(String pName) throws SQLException {
        if (DataBaseUtility.getProduct(pName) == null) {
            throw new NullPointerException("Product is null. It must be something");
        }
        DataBaseUtility.delete(pName);
    }
}
