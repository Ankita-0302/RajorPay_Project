package com.Ankita.razorpay.operation.entity;

import jakarta.persistence.Embeddable;

import java.util.UUID;


@Embeddable
public class SettlementPaymentId {

    private UUID settlementId;

    private UUID paymentId;

}
