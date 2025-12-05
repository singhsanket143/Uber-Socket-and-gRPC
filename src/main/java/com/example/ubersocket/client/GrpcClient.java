package com.example.ubersocket.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.ubersocket.RideServiceGrpc;
import com.example.ubersocket.RideAcceptanceRequest;
import com.example.ubersocket.RideAcceptanceResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;

@Component
public class GrpcClient {
    
    @Value("${grpc.server.port:9090}")
    private int grpcServerPort;

    @Value("${grpc.server.host:localhost}")
    private String grpcServerHost;

    private ManagedChannel channel;
    private RideServiceGrpc.RideServiceBlockingStub rideServiceStub;


    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress(grpcServerHost, grpcServerPort)
        .usePlaintext()
        .build();

        rideServiceStub = RideServiceGrpc.newBlockingStub(channel);
    }

    public boolean acceptRide(Integer driverId, Integer bookingId) {
        RideAcceptanceRequest request = RideAcceptanceRequest.newBuilder().setDriverId(driverId).setBookingId(bookingId).build();

        RideAcceptanceResponse response = rideServiceStub.acceptRide(request); // make the grpcCall

        return response.getSuccess();

    }
    
    
}
