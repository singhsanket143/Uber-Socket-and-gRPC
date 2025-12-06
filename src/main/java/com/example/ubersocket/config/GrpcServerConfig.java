package com.example.ubersocket.config;

import org.springframework.context.annotation.Configuration;

import com.example.ubersocket.services.RideNotificationServiceImpl;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import io.grpc.Server;
import jakarta.annotation.PostConstruct;
import io.grpc.ServerBuilder;


@Configuration
@RequiredArgsConstructor
public class GrpcServerConfig {
    
    @Value("${grpc.server.port:9091}")
    private int grpcServerPort;

    private final RideNotificationServiceImpl rideNotificationServiceImpl;
    private Server server;
    
    @PostConstruct
    public void startGrpcServer() throws IOException {
        server = ServerBuilder
                    .forPort(grpcServerPort)
                    .addService(rideNotificationServiceImpl)
                    .build()
                    .start();

        System.out.println("gRPC Server started on port " + grpcServerPort);


        new Thread(() -> {
            try {
                if( server != null ) {
                    server.awaitTermination();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("gRPC Server interrupted");
            }
        }).start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC Server...");
            if( server != null ) {
                server.shutdown();
            }
        }));

    }
}
