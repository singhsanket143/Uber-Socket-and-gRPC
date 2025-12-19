package com.example.ubersocket.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.ubersocket.client.GrpcClient;
import com.example.ubersocket.dtos.RideAcceptanceDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SocketController {

    private final GrpcClient grpcClient;
    

    @MessageMapping("/ride-acceptance")
    public void receiveRideAcceptance(RideAcceptanceDTO rideAcceptanceDTO) {

        try {
            boolean success = grpcClient.acceptRide(rideAcceptanceDTO.getDriverId(), rideAcceptanceDTO.getBookingId());
            if (success) {
                log.info("Ride acceptance successful: driverId={}, bookingId={}", 
                        rideAcceptanceDTO.getDriverId(), rideAcceptanceDTO.getBookingId());
            } else {
                log.error("Ride acceptance failed: driverId={}, bookingId={}", 
                        rideAcceptanceDTO.getDriverId(), rideAcceptanceDTO.getBookingId());
            }
        } catch (Exception e) {
            log.error("Error processing ride acceptance: driverId={}, bookingId={}", 
                    rideAcceptanceDTO.getDriverId(), rideAcceptanceDTO.getBookingId(), e);
        }

    }
}

// ubersocket -- grpc ---> uber booking service