package com.example.EStockMarketApplication.Repository;

import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.Long;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice,Long> {

}

