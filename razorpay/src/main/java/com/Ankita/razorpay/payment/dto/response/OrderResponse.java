package com.Ankita.razorpay.payment.dto.response;


import com.Ankita.razorpay.common.entity.Money;
import com.Ankita.razorpay.common.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID merchantId,
        String receipt,
        Money amount,
        OrderStatus status,
        Integer attemps,
        Map<String,Object> notes,
        LocalDateTime expiresAt,
        LocalDateTime createdAt


) {
}
