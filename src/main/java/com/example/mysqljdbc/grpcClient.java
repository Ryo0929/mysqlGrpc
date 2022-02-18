package com.example.mysqljdbc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
//34.106.36.207
public class grpcClient {
    public static void main(String[] args){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("34.106.36.207",8050).usePlaintext().build();
        db_servicesGrpc.db_servicesBlockingStub stub=db_servicesGrpc.newBlockingStub(channel);
        item i = item.newBuilder().setItemName("grpcTest3").setQuantity(3).setSellerId(1).build();
        stub.saveItem(i);
        channel.shutdown();
    }
}
