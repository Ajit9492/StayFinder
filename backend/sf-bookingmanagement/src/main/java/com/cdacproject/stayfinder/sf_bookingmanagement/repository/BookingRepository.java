package com.cdacproject.stayfinder.sf_bookingmanagement.repository;

import com.cdacproject.stayfinder.sf_bookingmanagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByTenantId(Long tenantId);
}
