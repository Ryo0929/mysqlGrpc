package com.example.mysqljdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

public class MysqlJdbcApplication {

    public static void main(String[] args) {
        //SpringApplication.run(MysqlJdbcApplication.class, args);
        String sqlSelectAllPersons = "SELECT * FROM items";
        String connectionUrl = "jdbc:mysql://34.106.36.207:3306/product_db?verifyServerCertificate=false&useSSL=False";
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "123123");
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("item_name");
                String lastName = rs.getString("item_id");
                System.out.println(name);
                System.out.println(lastName);
                // do something with the extracted data...
            }
        } catch (SQLException e) {
            // handle the exception
        }
    }

}
