package com.munaf.stock_tading_client.services;

import com.google.protobuf.Struct;
import com.munaf.A23_STOCK_TRADING_GRPC.EmptyRequest;
import com.munaf.A23_STOCK_TRADING_GRPC.StockRequest;
import com.munaf.A23_STOCK_TRADING_GRPC.StockTradingServiceGrpc;
import com.munaf.stock_tading_client.utils.ResponseModel;
import com.munaf.stock_tading_client.dtos.StockDto;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.munaf.stock_tading_client.utils.ResponseModel.convertStructToResponseModel;

@Service
public class StockTradingService {

    @GrpcClient("stockTradingService")
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub stockTradingServiceBlockingStub;

    public ResponseModel getStockById(Long stockId) {
        // get Request -> generate Response -> return reponse
        StockRequest stockRequest = StockRequest.newBuilder()
                .setId(stockId)
                .build();

        Struct struct = stockTradingServiceBlockingStub.getStockById(stockRequest);
        return convertStructToResponseModel(struct);
    }



    public ResponseModel getAllStocks() {
        EmptyRequest emptyRequest = EmptyRequest.newBuilder().build();

        Struct struct = stockTradingServiceBlockingStub.getAllStocks(emptyRequest);

        return convertStructToResponseModel(struct);
    }


}
