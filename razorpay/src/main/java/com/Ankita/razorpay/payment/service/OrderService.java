package com.Ankita.razorpay.payment.service;

import com.Ankita.razorpay.payment.dto.request.CreateOrderRequest;
import com.Ankita.razorpay.payment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {

    OrderResponse create(UUID merchantId, CreateOrderRequest request);
}
