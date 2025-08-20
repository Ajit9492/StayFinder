package com.cdacproject.stayfinder.sf_paymentmanagement.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RazorpayOrderRequest {
    private Long bookingId;
    private Double amount;
}
