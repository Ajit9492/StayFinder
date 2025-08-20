package com.cdacproject.stayfinder.sf_paymentmanagement.repository;

import com.cdacproject.stayfinder.sf_paymentmanagement.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTenantId(Long tenantId);
    List<Payment> findByOwnerId(Long ownerId);
}
