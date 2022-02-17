package com.example.mysqljdbc;

import io.grpc.stub.StreamObserver;

import java.sql.*;

public class itemServiceImpl extends db_servicesGrpc.db_servicesImplBase{
    String connectionUrl = "jdbc:mysql://34.106.36.207:3306/product_db?verifyServerCertificate=false&useSSL=False";
    String user="root";
    String password="123123";
    @Override
    public void saveItem(item input_item, StreamObserver<Empty> empty){
        String sql = "INSERT INTO product_db.items (item_name, item_category, quantity, sale_price, item_condition, keyword1, keyword2, keyword3, keyword4, keyword5, seller_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, "123123");
             ) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, input_item.getItemName());
            ps.setInt(2, input_item.getItemCategory());
            ps.setInt(3, input_item.getQuantity());
            ps.setDouble(4, input_item.getSalePrice());
            ps.setString(5, input_item.getItemCondition());
            ps.setString(6, input_item.getKeyword1());
            ps.setString(7, input_item.getKeyword2());
            ps.setString(8, input_item.getKeyword3());
            ps.setString(9, input_item.getKeyword4());
            ps.setString(10, input_item.getKeyword5());
            ps.setInt(11, input_item.getSellerId());
            int rs = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
