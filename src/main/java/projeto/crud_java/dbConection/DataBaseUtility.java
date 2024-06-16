package projeto.crud_java.dbConection;

import projeto.crud_java.beans.Product;

import java.sql.*;
import java.util.concurrent.ExecutionException;

public class DataBaseUtility {
    //TODO: Criar métodos que executam querys SQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "Rodrigo", "123456");
    }


    public static void insert(String s) throws Exception {
        String sql = "INSERT INTO products " + s;

        Connection conn = getConnection();
        Statement stmt = conn.prepareStatement(sql);

        conn.close();
    }

    public static void update(String s) throws Exception {
        String sql = "INSERT INTO products " + s;

        Connection conn = getConnection();
        Statement stmt = conn.prepareStatement(sql);

        conn.close();
    }

    public static void delete(String s) throws Exception {
        String sql = "DELETE INTO products " + s;

        Connection conn = getConnection();
        Statement stmt = conn.prepareStatement(sql);

        conn.close();
    }

    public static Product getProduct(String name) throws Exception {
        String sql = "SELECT * FROM products WHERE productName = ?";
        ResultSet rs = null;

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        rs = stmt.executeQuery();
        if (rs.next()) {
            Product product = new Product(rs.getString("productName"), rs.getDouble("listPrice"));



            conn.close();
            return product;
        }


        conn.close();
        return null;
    }
}
