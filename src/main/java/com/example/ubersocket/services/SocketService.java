package com.example.ubersocket.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.ubersocket.dtos.DriverNotificationDTO;
import com.example.ubersocket.dtos.RideRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocketService {
    
    private final SimpMessagingTemplate messagingTemplate;

    public void notifyDriversForNewRide(RideRequestDTO rideRequestDTO) {
        DriverNotificationDTO driverNotificationDTO = DriverNotificationDTO.builder()
            .pickUpLocationLatitude(rideRequestDTO.getPickUpLocationLatitude())
            .pickUpLocationLongitude(rideRequestDTO.getPickUpLocationLongitude())
            .bookingId(rideRequestDTO.getBookingId())
            .build();
        for(Integer driverId : rideRequestDTO.getDriverIds()) {
            messagingTemplate.convertAndSend("/topic/new-ride/"+driverId, driverNotificationDTO);
        }
    }


}
