package com.example.mysqljdbc;

import com.example.mysqljdbc.mysql_api.item.ItemService;
import com.example.mysqljdbc.mysql_api.item.Items;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

public class itemServiceImpl extends db_servicesGrpc.db_servicesImplBase{
    String connectionUrl = "jdbc:mysql://34.106.36.207:3306/product_db?verifyServerCertificate=false&useSSL=False";
    String user="root";
    String password="123123";
    @Autowired
    ItemService itemService;
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
        Empty emptyResponse=Empty.newBuilder().build();
        empty.onNext(emptyResponse);
        empty.onCompleted();
    }
    private Items itemAdapter(item input_item){
        Items out_item=new Items();
        out_item.setItem_category(input_item.getItemCategory());
        out_item.setItem_condition(input_item.getItemCondition());
        out_item.setItem_name(input_item.getItemName());
        out_item.setQuantity(input_item.getQuantity());
        out_item.setKeyword1(input_item.getKeyword1());
        return out_item;
    }
    public void deleteItem(item input_item, StreamObserver<Empty> empty){
        itemService.deleteItem(input_item.getItemId());
    }
    public void updateItem(item input_item, StreamObserver<Empty> empty){
        itemService.updateItem(itemAdapter(input_item));
    }
    public void getItem(item input_item, StreamObserver<Empty> empty){
        String sql = "SELECT FROM product_db.items (item_name, item_category, quantity, sale_price, item_condition, keyword1, keyword2, keyword3, keyword4, keyword5, seller_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
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
        Empty emptyResponse=Empty.newBuilder().build();
        empty.onNext(emptyResponse);
        empty.onCompleted();
    }
}
