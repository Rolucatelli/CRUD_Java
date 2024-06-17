package projeto.crud_java.dbConection;

import projeto.crud_java.beans.Product;

import java.sql.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class DataBaseUtility {
    //TODO: Criar métodos que executam querys SQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "Rodrigo", "123456");
    }


    public static void insert(String col, String val) throws SQLException {
        // INSERT INTO products ? VALUES ?
        String sql = "INSERT INTO products " + col + " VALUES " + val;
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);

        conn.close();
    }

    public static void update(String col, String name) throws SQLException {
        // UPDATE products SET shortDescription = "...", brand = "..." WHERE productName = "..."
        String sql = "UPDATE products SET " + col + " WHERE productName = " + name;

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);

        conn.close();
    }

    public static void delete(String name) throws SQLException {
        // DELETE FROM products WHERE productName = "..."
        String sql = "DELETE FROM products WHERE productName = " + name;

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);

        conn.close();
    }

    public static Product getProduct(String name) throws SQLException {
        String sql = "SELECT * FROM products WHERE productName = ?";
        ResultSet rs = null;
        Product product = null;

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        rs = stmt.executeQuery();
        if (rs.next()) {
            double temp = (rs.getDouble("cost") == 0) ? -1 : rs.getDouble("cost");
            product = new Product(rs.getString("productName"),
                    rs.getString("shortDescription"), rs.getString("brand"),
                    rs.getString("category"), rs.getDouble("listPrice"), temp);
        }
        rs.close();
        conn.close();
        return product;
    }

    public static LinkedList<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM products";
        ResultSet rs = null;
        LinkedList<Product> list = new LinkedList<>();

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            double temp = (rs.getDouble("cost") == 0) ? -1 : rs.getDouble("cost");
            list.add(new Product(rs.getString("productName"),
                    rs.getString("shortDescription"), rs.getString("brand"),
                    rs.getString("category"), rs.getDouble("listPrice"), temp));
        }
        rs.close();
        conn.close();
        System.out.println(list);
        return list;
    }
}
