package com.cdacproject.stayfinder.sf_paymentmanagement.dto;

import lombok.Data;

@Data
public class PgResponse {
    private Long id;
    private Long ownerId;
    private String name;
    private String city;
}
