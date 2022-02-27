package com.example.mysqljdbc.mysql_api.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;
    public void saveBuyer(Buyers buyers){
        buyerRepository.save(buyers);
    }
}
