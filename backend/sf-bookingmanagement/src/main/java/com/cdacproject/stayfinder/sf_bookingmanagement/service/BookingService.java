package com.cdacproject.stayfinder.sf_bookingmanagement.service;

import com.cdacproject.stayfinder.sf_bookingmanagement.dto.BookingResponse;
import com.cdacproject.stayfinder.sf_bookingmanagement.dto.CreateBookingRequest;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(CreateBookingRequest request, Long tenantId);
    void cancelBooking(Long bookingId, Long tenantId);
    List<BookingResponse> getBookingsByTenant(Long tenantId);

    BookingResponse getBookingById(Long bookingId);
}
