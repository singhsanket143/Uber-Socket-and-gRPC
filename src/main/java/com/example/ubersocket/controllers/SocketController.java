package com.example.ubersocket.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.ubersocket.dtos.RideAcceptanceDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SocketController {
    

    @MessageMapping("/ride-acceptance")
    public void receiveRideAcceptance(RideAcceptanceDTO rideAcceptanceDTO) {

        // TODO: Send an api to booking service to update the booking status

    }
}

// ubersocket -- grpc ---> uber booking service