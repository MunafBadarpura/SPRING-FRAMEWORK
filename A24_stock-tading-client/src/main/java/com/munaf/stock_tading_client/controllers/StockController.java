package com.munaf.stock_tading_client.controllers;

import com.munaf.stock_tading_client.utils.ResponseModel;
import com.munaf.stock_tading_client.dtos.StockDto;
import com.munaf.stock_tading_client.services.StockTradingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockTradingService stockTradingService;

    public StockController(StockTradingService stockTradingService) {
        this.stockTradingService = stockTradingService;
    }


    @GetMapping("/{stockId}")
    public ResponseModel getStockById(@PathVariable Long stockId) {
        System.out.println("StockId: " + stockId);
        return stockTradingService.getStockById(stockId);
    }



    @GetMapping
    public ResponseModel getAllStocks() {
        return stockTradingService.getAllStocks();
    }

}
