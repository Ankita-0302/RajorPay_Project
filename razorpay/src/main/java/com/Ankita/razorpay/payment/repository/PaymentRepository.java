package com.Ankita.razorpay.payment.repository;

import com.Ankita.razorpay.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
