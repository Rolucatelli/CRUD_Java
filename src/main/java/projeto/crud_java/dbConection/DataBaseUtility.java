package projeto.crud_java.dbConection;

import projeto.crud_java.beans.Product;

import java.sql.*;
import java.util.LinkedList;


public class DataBaseUtility {

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

    public static void update(String col, int id) throws SQLException {
        // UPDATE products SET shortDescription = "...", brand = "..." WHERE productName = "..."
        String sql = "UPDATE products SET " + col + " WHERE id = " + id;

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);

        conn.close();
    }

    public static void delete(int id) throws SQLException {
        // DELETE FROM products WHERE productName = "..."
        String sql = "DELETE FROM products WHERE id = " + id;

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);

        conn.close();
    }

    public static Product getProduct(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        return getProductFromStatement(conn, stmt);
    }

    public static Product getProductByName(String name) throws SQLException {
        String sql = "SELECT * FROM products WHERE productName = ?";

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        return getProductFromStatement(conn, stmt);
    }

    private static Product getProductFromStatement(Connection conn, PreparedStatement stmt) throws SQLException {
        Product product = null;
        ResultSet rs;
        rs = stmt.executeQuery();
        if (rs.next()) {
            if (rs.getDouble("cost") == 0) {
                product = new Product(rs.getInt("id"), rs.getString("productName"),
                        rs.getString("shortDescription"), rs.getString("brand"),
                        rs.getString("category"), rs.getDouble("listPrice"),
                        null);
            } else {
                product = new Product(rs.getInt("id"), rs.getString("productName"),
                        rs.getString("shortDescription"), rs.getString("brand"),
                        rs.getString("category"), rs.getDouble("listPrice"),
                        rs.getDouble("cost"));
            }
        }
        rs.close();
        conn.close();
        return product;
    }

    public static LinkedList<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM products";
        ResultSet rs;
        LinkedList<Product> list = new LinkedList<>();

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            if (rs.getDouble("cost") == 0) {
                list.add(new Product(rs.getInt("id"), rs.getString("productName"),
                        rs.getString("shortDescription"), rs.getString("brand"),
                        rs.getString("category"), rs.getDouble("listPrice"),
                        null));
            } else {
                list.add(new Product(rs.getInt("id"), rs.getString("productName"),
                        rs.getString("shortDescription"), rs.getString("brand"),
                        rs.getString("category"), rs.getDouble("listPrice"),
                        rs.getDouble("cost")));
            }
        }
        rs.close();
        conn.close();
        return list;
    }


}
