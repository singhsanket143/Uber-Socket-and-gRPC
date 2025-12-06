package com.example.ubersocket.services;

import org.springframework.stereotype.Service;

import com.example.ubersocket.RideNotificationRequest;
import com.example.ubersocket.RideNotificationResponse;
import com.example.ubersocket.RideNotificationServiceGrpc;
import com.example.ubersocket.dtos.RideRequestDTO;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideNotificationServiceImpl extends RideNotificationServiceGrpc.RideNotificationServiceImplBase {

    private final SocketService socketService;

    @Override
    public void notifyDriversForNewRide(RideNotificationRequest request, StreamObserver<RideNotificationResponse> responseObserver) {
        RideRequestDTO rideRequestDTO = RideRequestDTO.builder()
            .pickUpLocationLatitude(request.getPickUpLocationLatitude())
            .pickUpLocationLongitude(request.getPickUpLocationLongitude())
            .bookingId(request.getBookingId())
            .driverIds(request.getDriverIdsList())
            .build();
        socketService.notifyDriversForNewRide(rideRequestDTO);
        responseObserver.onNext(RideNotificationResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }
    
}
