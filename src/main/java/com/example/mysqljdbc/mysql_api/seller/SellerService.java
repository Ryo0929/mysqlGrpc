package com.example.mysqljdbc.mysql_api.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    public List<Sellers> listAllSeller() {
        return sellerRepository.findAll();
    }

    public void saveSeller(Sellers seller) {
        sellerRepository.save(seller);
    }

    public Sellers getSeller(Integer id) {
        return sellerRepository.findById(id).get();
    }

    public void deleteSeller(Integer id) {
        sellerRepository.deleteById(id);
    }

    public int getSellerRatingById(Integer id){return getSeller(id).getSeller_feedback();}
}
