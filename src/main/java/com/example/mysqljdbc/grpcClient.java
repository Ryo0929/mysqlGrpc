package com.example.mysqljdbc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class grpcClient {
    public static void main(String[] args){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8050).usePlaintext().build();
        db_servicesGrpc.db_servicesBlockingStub stub=db_servicesGrpc.newBlockingStub(channel);
        item i = item.newBuilder().setItemName("grpcTest").setQuantity(3).build();
        stub.saveItem(i);

        channel.shutdown();
    }
}
