package com.example.mysqljdbc;

import io.grpc.stub.StreamObserver;

import java.sql.*;

public class itemServiceImpl extends db_servicesGrpc.db_servicesImplBase{
    String connectionUrl = "jdbc:mysql://34.106.36.207:3306/product_db?verifyServerCertificate=false&useSSL=False";

    @Override
    public void saveItem(item input_item, StreamObserver<Empty> empty){
        String sql = "INSERT INTO product_db.items (item_name, item_category, quantity, sale_price, item_condition, keyword1, keyword2, keyword3, keyword4, keyword5, seller_id) VALUES ('grpc', 2, 3, 12, null, null, null, null, null, null, null)";
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "123123");
             ) {
            PreparedStatement ps = conn.prepareStatement(sql);
            int rs = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
