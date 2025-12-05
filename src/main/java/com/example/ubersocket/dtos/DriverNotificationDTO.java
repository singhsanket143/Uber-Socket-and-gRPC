package com.example.ubersocket.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverNotificationDTO {
    private String pickUpLocationLatitude;
    private String pickUpLocationLongitude;
    private Integer bookingId;
 }
