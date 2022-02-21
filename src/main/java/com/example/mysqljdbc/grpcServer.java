package com.example.mysqljdbc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

public class grpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8050).addService(new itemServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }


}
