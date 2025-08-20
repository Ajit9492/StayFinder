package com.cdacproject.stayfinder.sf_bookingmanagement.dto;

import lombok.Data;

@Data
public class RoomDto {
    private Long id;
    private Long pgId;
    private String name;
    private boolean available;
}
