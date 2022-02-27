package com.example.mysqljdbc.mysql_api.purchasedItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface purchasedItemRepository extends JpaRepository<purchasedItem, Integer>{

    @Query(value = "SELECT purchaseditem.* FROM purchaseditem WHERE purchaseditem.buyer_id=?1",nativeQuery=true)
    List<purchasedItem> findByBuyerId(int buyer_id);
}
