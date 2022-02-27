package com.example.mysqljdbc.mysql_api.buyer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BuyerRepository extends JpaRepository<Buyers,Integer> {
    @Query(value = "SELECT buyers.* FROM buyers WHERE buyers.buyer_name=?1",nativeQuery=true)
    Buyers findByBuyer_name(String buyer_name);
}
