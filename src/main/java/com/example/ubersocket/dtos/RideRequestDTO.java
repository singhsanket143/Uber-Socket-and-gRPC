package com.example.ubersocket.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RideRequestDTO {
    private String pickUpLocationLatitude;
    private String pickUpLocationLongitude;
    private Integer bookingId;
    private List<Integer> driverIds;
 }
