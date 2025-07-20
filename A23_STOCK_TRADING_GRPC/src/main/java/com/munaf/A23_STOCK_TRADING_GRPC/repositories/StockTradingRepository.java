package com.munaf.A23_STOCK_TRADING_GRPC.repositories;

import com.munaf.A23_STOCK_TRADING_GRPC.entities.StockTrading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTradingRepository extends JpaRepository<StockTrading, Long> {
}
