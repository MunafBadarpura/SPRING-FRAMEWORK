package com.munaf.A23_STOCK_TRADING_GRPC.services;

import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.munaf.A23_STOCK_TRADING_GRPC.EmptyRequest;
import com.munaf.A23_STOCK_TRADING_GRPC.StockTradingServiceGrpc;
import com.munaf.A23_STOCK_TRADING_GRPC.dto.StockDTO;
import com.munaf.A23_STOCK_TRADING_GRPC.entities.StockTrading;
import com.munaf.A23_STOCK_TRADING_GRPC.exceptions.ResourceNotFoundException;
import com.munaf.A23_STOCK_TRADING_GRPC.repositories.StockTradingRepository;
import com.munaf.A23_STOCK_TRADING_GRPC.StockRequest;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.munaf.A23_STOCK_TRADING_GRPC.utils.ResponseModel.createResponse;

@GrpcService
public class StockTradingGrpcService extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    private final StockTradingRepository stockTradingRepository;

    public StockTradingGrpcService(StockTradingRepository stockTradingRepository) {
        this.stockTradingRepository = stockTradingRepository;
    }

    public Timestamp convertDateToTimestamp(Date date) {
        Instant instant = date.toInstant();
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

//    @Override
//    public void getStockById(StockRequest request, StreamObserver<Struct> responseObserver) {
//        // get stockId -> fetch from db -> map response -> return
//
//        Long stockId = request.getId();
//        StockTrading stockTrading = stockTradingRepository.findById(stockId).orElse(null);
//
//        if (stockTrading == null) {
//            responseObserver.onError(
//                    Status.NOT_FOUND
//                            .withDescription("Stock not found with id : " + stockId)
//                            .withCause(new ResourceNotFoundException("Stock not found with id : " + stockId))
//                            .asRuntimeException()
//            );
//            return;
//        }
//
//        StockDTO stockDTO = new StockDTO();
//        stockDTO.setId(stockTrading.getId());
//        stockDTO.setName(stockTrading.getName());
//        stockDTO.setPrice(stockTrading.getPrice());
//        stockDTO.setStockDate(stockTrading.getStockDate());
//
//        Struct struct = createResponse(HttpStatus.OK, stockDTO);
//
//        responseObserver.onNext(struct);
//        responseObserver.onCompleted();
//    }


    @Override
    public void getStockById(StockRequest request, StreamObserver<Struct> responseObserver) {
        // get stockId -> fetch from db -> map response -> return

        Long stockId = request.getId();
        StockTrading stockTrading = stockTradingRepository.findById(stockId).orElse(null);

        if (stockTrading == null) {
            Struct struct = createResponse(HttpStatus.NOT_FOUND, "Stock Not Found With Id : " + stockId);
            responseObserver.onNext(struct);
            responseObserver.onCompleted();
            return;
        }

        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stockTrading.getId());
        stockDTO.setName(stockTrading.getName());
        stockDTO.setPrice(stockTrading.getPrice());
        stockDTO.setStockDate(stockTrading.getStockDate());

        Struct struct = createResponse(HttpStatus.OK, stockDTO);

        responseObserver.onNext(struct);
        responseObserver.onCompleted();
    }


    @Override
    public void getAllStocks(EmptyRequest request, StreamObserver<Struct> responseObserver) {
        List<StockTrading> stockTradingList = stockTradingRepository.findAll();

        Struct structResponse = createResponse(HttpStatus.OK, stockTradingList);

        responseObserver.onNext(structResponse);
        responseObserver.onCompleted();
    }




}
